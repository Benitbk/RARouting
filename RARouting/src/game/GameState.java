package game;

import graph.Edge;
import graph.Segment;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import agent.Agent;
import agent.AgentRoute;

/**
 * Created by benitbk on 28/04/2016.
 */
public class GameState {
    public Game game;
    Map<Agent, AgentRoute> agentRoutes;
    Map<Edge, Integer> numberOfAgentsPerEdge;

    public GameState(Game game) {
        this.game = game;
        this.agentRoutes = new HashMap<Agent, AgentRoute>();
       this.numberOfAgentsPerEdge = new HashMap<Edge, Integer>();
    }

    public void UpdateAgentRouting(Agent agent, AgentRoute newRoute) {
        if (agentRoutes.containsKey(agent)) {
            AgentRoute oldRoute = agentRoutes.get(agent);
            for (Edge e : oldRoute.edges) {
                numberOfAgentsPerEdge.put(e, getNumberOfAgentsOnEdge(e) - 1);
            }
        }
        agentRoutes.put(agent, newRoute);
        for (Edge e : newRoute.edges) {
            numberOfAgentsPerEdge.put(e, getNumberOfAgentsOnEdge(e) + 1);
        }
    }

    public AgentRoute getAgentRoute(Agent agent) {
        return agentRoutes.get(agent);
    }

    public Integer getNumberOfAgentsOnEdge(Edge edge) {
        if (numberOfAgentsPerEdge.containsKey(edge))
            return numberOfAgentsPerEdge.get(edge);
        return 0;
    }

    public boolean canAgentImprove(Agent agent) {
        AgentRoute currentRoute = this.getAgentRoute(agent);
        if (currentRoute == null)
            return true;

        Edge currentEdge = null;
        Iterator<Edge> routeEdgesIterator = currentRoute.edges.iterator();

        Segment currentSegment = agent.source.leaving.get(0);

        // for each segment on the path, check if there is a better edge for
        // that agent
        while (currentSegment.s != agent.destination) {
            currentEdge = routeEdgesIterator.next();

            // The cost the agent currently pay on this segment
            double currentCost = currentEdge.cost
                    / this.getNumberOfAgentsOnEdge(currentEdge);

            for (Edge edge : currentSegment.edges) {
                if (edge == currentEdge)
                    continue;

                Integer edgeLoad = this.getNumberOfAgentsOnEdge(edge);

                // The cost the agent will pay if he/she moves to that edge
                double sharedCost = edge.cost / (edgeLoad + 1);

                if (sharedCost < currentCost) {
                    return true;
                }
            }

            if (currentSegment.t.leaving.size() == 0)
                break;
            currentSegment = currentSegment.t.leaving.get(0);
        }
        return false;

    }

    public Double getAgenCost(Agent agent) {

        Double totalCost = 0.0;
        for (Edge edge : agentRoutes.get(agent).edges) {
            totalCost += edge.cost / numberOfAgentsPerEdge.get(edge);
        }

        return totalCost;
    }

    public Double getSocialCost() {
        Double totalCost = 0.0;
        for (Entry<Edge, Integer> edgeUsage : numberOfAgentsPerEdge.entrySet()) {
            if (edgeUsage.getValue() > 0)
                totalCost += edgeUsage.getKey().cost;
        }
        return totalCost;
    }
}

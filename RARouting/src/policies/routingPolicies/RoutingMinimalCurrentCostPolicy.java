package policies.routingPolicies;

import agent.Agent;
import agent.AgentRoute;
import agent.AgentRoutingState;
import game.Game;
import graph.Edge;
import graph.Segment;
import interfaces.RoutingPolicy;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Created by benitbk on 28/04/2016.
 */
public class RoutingMinimalCurrentCostPolicy extends RoutingPolicy {
    public RoutingMinimalCurrentCostPolicy(Game game) {
        super(game);
    }

    @Override
    public AgentRoute getAgentImprovedRoute(Agent agent) {
        Segment currentSegment = agent.source.leaving.get(0);
        AgentRoute newRoute = new AgentRoute();

        AgentRoute currentRoute = routingState.getAgentRoute(agent);
        Edge currentEdge = null;
        Iterator<Edge> routeIterator = null;
        if (currentRoute != null) {
            routeIterator = currentRoute.edges.iterator();
            currentEdge = routeIterator.next();
        }
        while (currentSegment != null && currentSegment.s != agent.destination) {
            double minCost = Double.MAX_VALUE;
            Edge minEdge = null;
            for (Edge edge : currentSegment.edges) {
                        Integer edgeLoad = routingState.getNumberOfAgentsOnEdge(edge);
                Double sharedCost;
                if (edge == currentEdge)
                    sharedCost = edge.cost / edgeLoad;
                else
                    sharedCost = edge.cost / (edgeLoad + 1);

                if(sharedCost < minCost){
                    minCost = sharedCost;
                    minEdge = edge;
                }

            }

            newRoute.edges.add(minEdge);

            if (routeIterator != null) {
                currentEdge = routeIterator.next();
            }
            currentSegment = currentSegment.t.leaving.get(0);
        }
        return newRoute;
    }
}


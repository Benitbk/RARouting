package agent;

import graph.Edge;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by benitbk on 28/04/2016.
 */
public class AgentRoutingState {
    Map<Agent, AgentRoute> agentRoutes;
    Map<Edge, Integer> numberOfAgentsPerEdge;

    public AgentRoutingState()
    {
        this.agentRoutes = new HashMap<Agent, AgentRoute>();
        this.numberOfAgentsPerEdge = new HashMap<Edge, Integer>();
    }

    public void UpdateAgentRouting(Agent agent, AgentRoute newRoute)
    {
        if(agentRoutes.containsKey(agent)) {
            AgentRoute oldRoute = agentRoutes.get(agent);
            for(Edge e : oldRoute.edges)
            {
                numberOfAgentsPerEdge.put(e, numberOfAgentsPerEdge.get(e) - 1);
            }
        }
        agentRoutes.put(agent, newRoute);
        for(Edge e : newRoute.edges)
        {
            numberOfAgentsPerEdge.put(e, numberOfAgentsPerEdge.get(e) + 1);
        }
    }
}

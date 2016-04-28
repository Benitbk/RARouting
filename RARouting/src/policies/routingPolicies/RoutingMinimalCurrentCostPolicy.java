package policies.routingPolicies;

import agent.Agent;
import agent.AgentRoute;
import agent.AgentRoutingState;
import game.Game;
import graph.Segment;
import interfaces.RoutingPolicy;

import java.util.List;

/**
 * Created by benitbk on 28/04/2016.
 */
public class RoutingMinimalCurrentCostPolicy extends RoutingPolicy
{
    public RoutingMinimalCurrentCostPolicy(Game game, AgentRoutingState routingState)
    {
        super(game, routingState);
    }
    @Override
    public AgentRoute getAgentImprovedRoute(Agent agent) {
        Segment currentSegment = agent.source.leaving.get(0);
        AgentRoute newRoute = new AgentRoute();
        while(currentSegment != null && currentSegment.s != agent.destination)
        {


            currentSegment = currentSegment.t.leaving.get(0);
        }
        return new AgentRoute();
    }
}

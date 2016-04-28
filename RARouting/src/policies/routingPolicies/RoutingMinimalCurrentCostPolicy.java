package policies.routingPolicies;

import agent.Agent;
import game.Game;
import interfaces.RoutingPolicy;

/**
 * Created by benitbk on 28/04/2016.
 */
public class RoutingMinimalCurrentCostPolicy extends RoutingPolicy
{
    public RoutingMinimalCurrentCostPolicy(Game game)
    {
        super(game);
    }
    @Override
    public void improveAgentPath(Agent agent) {
        //not implemented
    }
}

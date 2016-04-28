package interfaces;

import agent.Agent;
import agent.AgentRoute;
import agent.AgentRoutingState;
import game.Game;

/**
 * Created by benitbk on 11/04/2016.
 */
public abstract class RoutingPolicy {

    protected final Game game;
    public AgentRoutingState routingState;

    public RoutingPolicy(Game game)
    {
        this.game = game;
    }

    public abstract AgentRoute getAgentImprovedRoute(Agent agent);

}

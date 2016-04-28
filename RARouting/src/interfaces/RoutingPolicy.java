package interfaces;

import agent.Agent;
import agent.AgentRoute;
import game.GameState;
import game.Game;

/**
 * Created by benitbk on 11/04/2016.
 */
public abstract class RoutingPolicy {

    protected final GameState gameState;

    public RoutingPolicy(GameState gameState)
    {
        this.gameState = gameState;
    }

    public abstract AgentRoute getAgentImprovedRoute(Agent agent);

}

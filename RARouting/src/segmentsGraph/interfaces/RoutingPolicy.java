package segmentsGraph.interfaces;

import segmentsGraph.agent.Agent;
import segmentsGraph.agent.AgentRoute;
import segmentsGraph.game.GameState;

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

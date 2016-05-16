package segmentsGraph.interfaces;

import segmentsGraph.agent.Agent;
import segmentsGraph.game.GameState;

/**
 * Created by benitbk on 11/04/2016.
 */
public abstract class AgentPolicy {

    protected final GameState gameState;

    public AgentPolicy(GameState gameState)
    {
        this.gameState = gameState;
    }
    public abstract Agent getNextAgent();
}

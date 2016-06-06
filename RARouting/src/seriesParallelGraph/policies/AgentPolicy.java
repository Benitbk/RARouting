package seriesParallelGraph.policies;

import seriesParallelGraph.agent.Agent;
import seriesParallelGraph.game.GameState;
import seriesParallelGraph.graph.edge.EdgeKind;

/**
 * Created by benitbk on 11/04/2016.
 */
public abstract class AgentPolicy {

    protected final GameState gameState;
    protected final EdgeKind edgeKind;

    public AgentPolicy(GameState gameState, EdgeKind edgeKind)
    {
        this.gameState = gameState;
        this.edgeKind = edgeKind;

    }
    public abstract Agent getNextAgent();
}

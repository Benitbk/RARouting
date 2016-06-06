package seriesParallelGraph.policies;

import seriesParallelGraph.agent.Agent;
import seriesParallelGraph.game.GameState;
import seriesParallelGraph.graph.Route;
import seriesParallelGraph.graph.edge.EdgeKind;

import java.util.List;

/**
 * Created by Guy Aloni on 06/06/2016.
 */
public class AgentMinPathPolicy extends AgentPolicy {

    public AgentMinPathPolicy(GameState gameState, EdgeKind edgeKind) {
        super(gameState, edgeKind);
    }

    @Override
    public Agent getNextAgent() {
        if (edgeKind == EdgeKind.CostSharing) {
            List<Agent> agents = this.gameState.game.agents;

            double minPath = Integer.MAX_VALUE;
            int nextAgentToPlay=0;

            for (int i=0; i<agents.size();i++)
            {
                Agent agent = agents.get(i);

                Route improvedRoute = this.gameState.getImprovedRoute(agent);
                if (improvedRoute != null)
                {
                    double improvedRouteCost = improvedRoute.cost();
                    if (improvedRouteCost < minPath)
                    {
                        nextAgentToPlay=i;
                        minPath = improvedRouteCost;
                    }
                }
            }

            if (minPath == Integer.MAX_VALUE)
            {
                return null;
            }
            else
            {
                return agents.get(nextAgentToPlay);
            }
        }

        return null;
    }
}
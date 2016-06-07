package seriesParallelGraph.policies;

import seriesParallelGraph.agent.Agent;
import seriesParallelGraph.game.GameState;
import seriesParallelGraph.graph.Route;
import seriesParallelGraph.graph.edge.CostSharingEdge;
import seriesParallelGraph.graph.edge.Edge;
import seriesParallelGraph.graph.edge.EdgeKind;

import java.util.List;

/**
 * Created by Guy Aloni on 06/06/2016.
 */
public class AgentMinPathPolicy extends AgentPolicy {

    private EdgeKind edgeKind;

	public AgentMinPathPolicy(GameState gameState, EdgeKind edgeKind) {
        super(gameState);
    this.edgeKind = edgeKind;
    }

    @Override
    public Agent getNextAgent() {
        if (edgeKind == EdgeKind.CostSharing) {
            List<Agent> agents = this.gameState.game.agents;

            double minPath = Double.MAX_VALUE;

            Agent nextAgentToPlay=null;

            for (int i=0; i<agents.size();i++)
            {
                Agent agent = agents.get(i);

                Route improvedRoute = this.gameState.getImprovedRoute(agent);
                if (improvedRoute != null)
                {
                    // Todo: get the cost
                    double improvedRouteCost = GetCostOfRoute(improvedRoute);

                    if (improvedRouteCost < minPath)
                    {
                        nextAgentToPlay=agent;
                        minPath = improvedRouteCost;
                    }
                }
            }

            return nextAgentToPlay;
        }

        return null;
    }

    private double GetCostOfRoute(Route route)
    {
        double cost =0;

        if (edgeKind == EdgeKind.CostSharing) {

            for (Edge edge : route.edges) {
                cost += ((CostSharingEdge) edge).cost;
            }

        }

        return cost;
    }
}
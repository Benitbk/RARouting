package seriesParallelGraph.policies;

import seriesParallelGraph.agent.Agent;
import seriesParallelGraph.game.GameState;
import seriesParallelGraph.graph.Route;

import java.util.List;

/**
 * Created by Guy Aloni on 06/06/2016.
 */
public class AgentMaximalCostPolicy extends AgentPolicy {

    public AgentMaximalCostPolicy(GameState gameState) {
        super(gameState);    }

    @Override
    public Agent getNextAgent() {
        List<Agent> agents = this.gameState.game.agents;

        double maxCost = -1;
        int nextAgentToPlay=0;

        for (int i=0; i<agents.size();i++)
        {
            Agent agent = agents.get(i);

            Route improvedRoute = this.gameState.getImprovedRoute(agent);
            if (improvedRoute != null)
            {
                double currAgentRouteCost = agent.getRoute().cost();
                if (currAgentRouteCost > maxCost)
                {
                    nextAgentToPlay=i;
                    maxCost = currAgentRouteCost;
                }
            }
        }

        if (maxCost == -1)
        {
            return null;
        }
        else
        {
            return agents.get(nextAgentToPlay);
        }
    }
}
package seriesParallelGraph.policies;

import seriesParallelGraph.agent.Agent;
import seriesParallelGraph.game.GameState;
import seriesParallelGraph.graph.Route;
import seriesParallelGraph.graph.edge.EdgeKind;

/**
 * Created by benitbk on 06/06/2016.
 */
public class AgentGreatestImprovePolicy extends AgentPolicy {

    public AgentGreatestImprovePolicy(GameState gameState, EdgeKind edgeKind) {
        super(gameState, edgeKind);
    }

    @Override
    public Agent getNextAgent() {
        Agent currentAgent = null;
        double currentImprovement = 0;
        for(Agent agent : this.gameState.game.agents) {
            Route improvedRoute = this.gameState.getImprovedRoute(agent);
            if(improvedRoute == null)
                continue;
            double improvement =  agent.getRoute().cost() - improvedRoute.cost();
            if(improvement > currentImprovement) {
                currentAgent = agent;
                currentImprovement = improvement;
            }
        }
        return currentAgent;
    }
}

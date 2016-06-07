package seriesParallelGraph.policies;

import seriesParallelGraph.agent.Agent;
import seriesParallelGraph.game.GameState;
import seriesParallelGraph.graph.Route;

/**
 * Created by benitbk on 06/06/2016.
 */
public class AgentGreatestImprovePolicy extends AgentPolicy {

    public AgentGreatestImprovePolicy(GameState gameState) {
        super(gameState);
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
//            System.out.printf("\tAgent %d can improve by %f\n", agent.id, improvement);
            if(improvement > currentImprovement) {
                currentAgent = agent;
                currentImprovement = improvement;
            }
        }
        return currentAgent;
    }
}

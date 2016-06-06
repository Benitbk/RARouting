package seriesParallelGraph.game;

import seriesParallelGraph.agent.Agent;
import seriesParallelGraph.game.results.AgentStep;
import seriesParallelGraph.game.results.PolicyResult;
import seriesParallelGraph.graph.Route;
import seriesParallelGraph.policies.AgentPolicy;

/**
 * Created by benitbk on 11/04/2016.
 */
public class GamePlayer {

	private GameState gameState;
	private AgentPolicy policy;

	public GamePlayer(GameState gameState, AgentPolicy policy) {
		this.gameState = gameState;
		this.policy = policy;

	}

	public void start(PolicyResult policyResult)
    {
		while(true){
			Agent nextAgent = this.policy.getNextAgent();
			if(nextAgent == null)
			{
				return;
			}
			else
			{
				Route improvedRoute = this.gameState.getImprovedRoute(nextAgent);
                double oldCost = nextAgent.getRoute().cost();
				nextAgent.setRoute(improvedRoute);
                policyResult.steps.add(new AgentStep(nextAgent, oldCost, this.gameState.game.getSocialCost()));
			}
		}


	}

}

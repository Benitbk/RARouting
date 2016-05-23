package seriesParallelGraph.game;

import seriesParallelGraph.agent.Agent;
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

	public void start()
    {
		System.out.println("Agent\tRoute\tCost\tSocial Cost");
		//double socialCost = 0;

		while(true){
			Agent nextAgent = this.policy.getNextAgent();
			if(nextAgent == null)
			{
				return;
			}
			else
			{
				Route improvedRoute = this.gameState.getImprovedRoute(nextAgent);
				nextAgent.setRoute(improvedRoute);
                System.out.println();
			}
		}


	}

}

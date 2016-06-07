package seriesParallelGraph.game;

import java.util.Map.Entry;

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

	public void simulate(PolicyResult policyResult) {

		for (Entry<Agent, Route> entry : gameState.game.initialRoutes
				.entrySet()) {
			Agent agent = entry.getKey();
			Route route = entry.getValue();
			agent.setRoute(route);
		}

		while (true) {
			Agent nextAgent = this.policy.getNextAgent();
			if (nextAgent == null) {
				return;
			} else {
				Route oldRoute = nextAgent.getRoute();
				Route improvedRoute = this.gameState
						.getImprovedRoute(nextAgent);

				double oldCost = oldRoute.cost();
				nextAgent.setRoute(improvedRoute);
				policyResult.steps
						.add(new AgentStep(nextAgent, oldCost, this.gameState
								.getSocialCost(), oldRoute, improvedRoute));
			}
		}

	}

}

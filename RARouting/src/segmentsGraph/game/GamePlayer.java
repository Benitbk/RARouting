package segmentsGraph.game;

import segmentsGraph.agent.Agent;
import segmentsGraph.agent.AgentRoute;
import segmentsGraph.interfaces.GamePolicy;

/**
 * Created by benitbk on 11/04/2016.
 */
public class GamePlayer {

	private final GamePolicy gamePolicy;
	private GameState gameState;

	public GamePlayer(GameState gameState, GamePolicy gamePolicy) {
		this.gamePolicy = gamePolicy;
		this.gameState = gameState;
	}

	public void start()
    {
		Agent agent = gamePolicy.agentPolicy.getNextAgent();

		System.out
				.println("Iteration\tAgent\tRoute\tAgent Cost\tSocial Cost\t");

		for (int i = 0; agent != null; i++) {
			System.out.print(i + "\t" + agent.id + "\t");

			AgentRoute newRoute = gamePolicy.routingPolicy
					.getAgentImprovedRoute(agent);
			this.gameState.UpdateAgentRouting(agent, newRoute);

			System.out.print(gameState.getAgentRoute(agent) + "\t");

			System.out.printf("%.3f\t%.3f\t",gameState.getAgenCost(agent), gameState.getSocialCost());

			agent = gamePolicy.agentPolicy.getNextAgent();

			System.out.println();
		}
		System.out.println("Done!");

	}

}

package segmentsGraph;

import segmentsGraph.agent.Agent;
import segmentsGraph.game.GameState;
import segmentsGraph.game.Game;
import segmentsGraph.game.GamePlayer;
import segmentsGraph.graph.Graph;
import segmentsGraph.graph.Vertex;
import segmentsGraph.interfaces.GamePolicy;
import segmentsGraph.policies.agentPolicies.AgentIncreasingPolicy;
import segmentsGraph.policies.routingPolicies.RoutingMinimalCurrentCostPolicy;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by benitbk on 11/04/2016.
 */
public class Main {
	final static int NUMBER_OF_VERTEX = 3;
	final static int NUMBER_OF_PLAYERS = 10;

	public static void main(String[] args) {
		Graph graph = new Graph(NUMBER_OF_VERTEX);

		ArrayList<Agent> agents = new ArrayList<Agent>();

		Random randomGenerator = new Random();

		for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
			int index1 = 0, index2 = 0;

			while (!(index1 < index2)) {
				index1 = randomGenerator.nextInt(graph.vertices.size());
				index2 = randomGenerator.nextInt(graph.vertices.size());
			}

			Vertex source = graph.vertices.get(index1);
			Vertex dest = graph.vertices.get(index2);

			agents.add(new Agent(i, source, dest));
		}

		Game game = new Game(graph, agents);
		System.out.print(game);
		
		GameState gameState = new GameState(game);

		GamePlayer gamePlayer = new GamePlayer(gameState, new GamePolicy(
				new AgentIncreasingPolicy(gameState),
				new RoutingMinimalCurrentCostPolicy(gameState)));
		
		gamePlayer.start();
	}
}

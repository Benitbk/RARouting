package seriesParallelGraph.policies;

import seriesParallelGraph.agent.Agent;
import seriesParallelGraph.game.GameState;
import seriesParallelGraph.graph.Route;
import seriesParallelGraph.graph.edge.EdgeKind;

import java.util.List;

/**
 * Created by benitbk on 28/04/2016.
 */
public class AgentIncreasingPolicy extends AgentPolicy {
	private int nextAgentToPlay;

	public AgentIncreasingPolicy(GameState gameState, EdgeKind edgeKind) {
		super(gameState, edgeKind);
		nextAgentToPlay = 0;
	}

	@Override
	public Agent getNextAgent() {
		List<Agent> agents = this.gameState.game.agents;
		int i = nextAgentToPlay;
		do {
			Agent agent = agents.get(i);
			i = (i + 1) % agents.size();
			Route improvedRoute = this.gameState.getImprovedRoute(agent);
			if (improvedRoute != null) {
				nextAgentToPlay = i;
				return agent;
			}
		} while (i != nextAgentToPlay);

		//non of the load can improve
		return null;
	}
}

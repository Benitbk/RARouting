package segmentsGraph.policies.agentPolicies;

import java.util.List;

import segmentsGraph.agent.Agent;
import segmentsGraph.game.GameState;
import segmentsGraph.interfaces.AgentPolicy;

/**
 * Created by benitbk on 28/04/2016.
 */
public class AgentIncreasingPolicy extends AgentPolicy {
	private int nextAgentToPlay;

	public AgentIncreasingPolicy(GameState gameState) {
		super(gameState);
		nextAgentToPlay = 0;
	}

	@Override
	public Agent getNextAgent() {
		List<Agent> agents = this.gameState.game.agents;
		int i = nextAgentToPlay;
		do {
			Agent agent = agents.get(i);
			i = (i + 1) % agents.size();
			if (gameState.canAgentImprove(agent)) {
				nextAgentToPlay = i;
				return agent;
			}
		} while (i != nextAgentToPlay);

		//non of the load can improve
		return null;
	}
}

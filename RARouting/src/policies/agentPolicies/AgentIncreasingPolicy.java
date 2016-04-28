package policies.agentPolicies;

import agent.Agent;
import game.Game;
import interfaces.AgentPolicy;

/**
 * Created by benitbk on 28/04/2016.
 */
public class AgentIncreasingPolicy extends AgentPolicy{
    private int currentAgentIndex;
    public AgentIncreasingPolicy(Game game)
    {
        super(game);
        currentAgentIndex = 0;
    }

    @Override
    public Agent getNextAgent() {
        return this.game.agents.get(currentAgentIndex);//agents[currentAgentIndex++];
    }
}

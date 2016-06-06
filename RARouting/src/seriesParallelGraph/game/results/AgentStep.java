package seriesParallelGraph.game.results;

import seriesParallelGraph.agent.Agent;

/**
 * Created by benitbk on 06/06/2016.
 */
public class AgentStep {
    public Agent agent;
    public double oldCost;
    public double socialCost;
    public AgentStep(Agent agent, double oldCost, double socialCost) {
        this.agent = (Agent)DeepCopy.copy(agent);
        this.oldCost = oldCost;
        this.socialCost = socialCost;
    }
}

package seriesParallelGraph.game.results;

import seriesParallelGraph.agent.Agent;
import seriesParallelGraph.graph.Route;

/**
 * Created by benitbk on 06/06/2016.
 */
public class AgentStep {
	public Agent agent;
	public double oldCost;
	public double socialCost;
	public Route oldRoute;
	public Route newRoute;

	public AgentStep(Agent agent, double oldCost, double socialCost,
			Route oldRoute, Route newRoute) {
		this.agent = agent;
		this.oldCost = oldCost;
		this.socialCost = socialCost;
		this.oldRoute = oldRoute;
		this.newRoute = newRoute;
	}
}

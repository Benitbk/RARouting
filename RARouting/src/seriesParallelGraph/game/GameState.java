package seriesParallelGraph.game;

import seriesParallelGraph.agent.Agent;
import seriesParallelGraph.graph.Route;

/**
 * Created by benitbk on 23/05/2016.
 */
public class GameState {
	public Game game;

	public GameState(Game game) {
		this.game = game;
	}

	public Route getImprovedRoute(Agent agent) {
		Route oldRoute = agent.getRoute();
		double oldCost = 0;
		if (oldRoute != null)
			oldCost = oldRoute.cost();
		agent.setRoute(null);
		Route newRoute = this.game.graph.generateSubGraphFromVertices(
				agent.source, agent.destination).findBestSTPath();
		double newCost = newRoute.forecastedCost();
		if (oldRoute != null && oldCost <= newCost) {
			assert (oldCost == newCost);
			newRoute = null;
		}
		agent.setRoute(oldRoute);
		return newRoute;
	}

	public double getSocialCost() {
		int socialCost = 0;
		for (Agent agent : this.game.agents) {
			socialCost += agent.getRoute().cost();
		}
		return socialCost;
	}

}

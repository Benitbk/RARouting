package segmentsGraph.policies.routingPolicies;

import segmentsGraph.agent.Agent;
import segmentsGraph.agent.AgentRoute;
import segmentsGraph.game.GameState;
import segmentsGraph.graph.Edge;
import segmentsGraph.graph.Segment;
import segmentsGraph.interfaces.RoutingPolicy;

import java.util.Iterator;

/**
 * Created by benitbk on 28/04/2016.
 */
public class RoutingMinimalCurrentCostPolicy extends RoutingPolicy {
	public RoutingMinimalCurrentCostPolicy(GameState gameState) {
		super(gameState);
	}

	@Override
	public AgentRoute getAgentImprovedRoute(Agent agent) {
		Segment currentSegment = agent.source.leaving.get(0);
		AgentRoute newRoute = new AgentRoute();

		AgentRoute currentRoute = gameState.getAgentRoute(agent);
		Edge currentEdge = null;
		Iterator<Edge> routeEdgesIterator = null;
		if (currentRoute != null)
			routeEdgesIterator = currentRoute.edges.iterator();

		while (currentSegment.s != agent.destination) {

			if (routeEdgesIterator != null)
				currentEdge = routeEdgesIterator.next();

			double minCost = Double.MAX_VALUE;
			Edge minEdge = null;
			for (Edge edge : currentSegment.edges) {
				Integer edgeLoad = gameState.getNumberOfAgentsOnEdge(edge);
				Double sharedCost;
				if (edge == currentEdge)
					sharedCost = edge.cost / edgeLoad;
				else
					sharedCost = edge.cost / (edgeLoad + 1);

				if (sharedCost < minCost) {
					minCost = sharedCost;
					minEdge = edge;
				}

			}

			newRoute.edges.add(minEdge);

			if(currentSegment.t.leaving.size() == 0)
				break;
			
			currentSegment = currentSegment.t.leaving.get(0);
		}
		return newRoute;
	}
}

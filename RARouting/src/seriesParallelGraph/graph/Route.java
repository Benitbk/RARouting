package seriesParallelGraph.graph;

import seriesParallelGraph.graph.edge.Edge;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Route implements Serializable {
    private static final long serialVersionUID = 2228953572123708337L;

	public List<Edge> edges;

	public Route(Edge edge) {
		this();
		edges.add(edge);
	}

	public Route() {
		this.edges = new ArrayList<>();
	}

	public double cost() {
		double cost = 0;
		for (Edge edge : edges)
			cost += edge.getCostForSingleAgent();
		return cost;
	}

	/**
	 * @return The cost an agent will pay if it chooses this route
	 */
	public double forecastedCost() {
		double cost = 0;
		for (Edge edge : edges)
			cost += edge.getForecastedCostForSingleAgent();
		return cost;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Edge edge : edges) {
			sb.append(edge).append("->");
		}
		return sb.toString();
	}
}

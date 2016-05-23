package seriesParallelGraph.graph;

import seriesParallelGraph.graph.edge.Edge;

import java.util.ArrayList;
import java.util.List;

public class Route {

	public List<Edge> edges;

	public Route(Edge edge) {
		this();
		edges.add(edge);
	}

	public Route() {
		this.edges = new ArrayList<>();
	}

	public double costForSingleAgent() {
		double cost = 0;
		for (Edge edge : edges)
			cost += edge.getCostForSingleAgent();
		return cost;
	}

    public double expectedCostForSingleAgent() {
        double cost = 0;
        for (Edge edge : edges)
            cost += edge.getExpectedCostForSingleAgent();
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

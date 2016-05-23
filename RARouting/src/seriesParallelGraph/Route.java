package seriesParallelGraph;

import java.util.ArrayList;
import java.util.List;

public class Route {

	List<Edge> edges;

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
			cost += edge.cost / edge.agents;

		return cost;
	}

	public double newCost() {
		double cost = 0;
		for (Edge edge : edges)
			cost += edge.cost / (edge.agents + 1);

		return cost;
	}

	public double socailCost() {
		double cost = 0;
		for (Edge edge : edges)
			cost += edge.cost;

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

package seriesParallelGraph;

import java.util.ArrayList;
import java.util.List;

public class Path {

	List<Edge> edges;

	public Path(Edge edge) {
		this();
		edges.add(edge);
	}

	public Path() {
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
}

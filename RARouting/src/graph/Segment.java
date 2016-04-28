package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Segment {

	public List<Edge> edges;

	public Vertex s;
	public Vertex t;

	static int nextId = 0;
	public int id;

	public Segment(List<Edge> edges, Vertex s, Vertex t) {
		super();
		this.id = nextId++;
		this.edges = edges;
		this.s = s;
		this.t = t;
	}

	public Segment(Vertex s, Vertex t) {
		this.id = nextId++;
		this.s = s;
		this.t = t;

		// randomize number of edges
		int numOfEdges = (int) (Math.random() * 5) + 1;

		// randomize costs
		edges = new ArrayList<Edge>();
		for (int i = 0; i < numOfEdges; i++) {
			Edge edge = new Edge(this, Math.random() * 50);
			edges.add(edge);
		}

		Collections.sort(edges);

		int i = 0;
		for (Edge edge : edges) {
			edge.index = i++;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(s.toString() + "-" + t.toString() + "\n");
		sb.append("Edges Costs: ");
		for (Edge edge : edges) {
			sb.append(String.format("%.3f", edge.cost) + ", ");
		}

		return sb.toString();
	}
}

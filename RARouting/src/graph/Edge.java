package graph;

public class Edge implements Comparable<Edge>{

	Segment segment;
	Double cost;

	public Edge(Segment segment, double cost) {
		super();
		this.segment = segment;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge e) {
		return cost.compareTo(e.cost);
	}

}

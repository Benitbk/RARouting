package segmentsGraph.graph;

public class Edge implements Comparable<Edge> {

	public int index;
	public Segment segment;
	public Double cost;

	public Edge(Segment segment, double cost) {
		super();
		this.segment = segment;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge e) {
		return cost.compareTo(e.cost);
	}

	@Override
	public String toString() {
		return "" + index + "@" + segment.id;
	}

}

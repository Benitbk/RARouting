package graph;

import java.util.List;

public class Segment {
	
	List<Double> edgeCosts;
	
	Vertex s;
	Vertex t;
	
	public Segment(List<Double> edgeCosts, Vertex s, Vertex t) {
		super();
		this.edgeCosts = edgeCosts;
		this.s = s;
		this.t = t;
	}
}

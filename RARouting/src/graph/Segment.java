package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Segment {

	List<Edge> edgeCosts;

	Vertex s;
	Vertex t;

	public Segment(List<Edge> edgeCosts, Vertex s, Vertex t) {
		super();
		this.edgeCosts = edgeCosts;
		this.s = s;
		this.t = t;
	}

	public Segment(Vertex s, Vertex t) {
		this.s = s;
		this.t = t;
		
		//randomize number of edges
		int numOfEdges = (int) (Math.random() * 5) + 1;
		
		//randomize costs
		edgeCosts = new ArrayList<Edge>();
		for (int i = 0; i < numOfEdges; i++) {
			Edge edge = new Edge(this, Math.random()*50);
			edgeCosts.add(edge);
		}
		
		Collections.sort(edgeCosts);
	}
}

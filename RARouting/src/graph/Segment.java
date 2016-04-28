package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Segment {

	List<Edge> edges;

	public Vertex s;
	public Vertex t;

	public Segment(List<Edge> edges, Vertex s, Vertex t) {
		super();
		this.edges = edges;
		this.s = s;
		this.t = t;
	}

	public Segment(Vertex s, Vertex t) {
		this.s = s;
		this.t = t;
		
		//randomize number of edges
		int numOfEdges = (int) (Math.random() * 5) + 1;
		
		//randomize costs
		edges = new ArrayList<Edge>();
		for (int i = 0; i < numOfEdges; i++) {
			Edge edge = new Edge(this, Math.random()*50);
			edges.add(edge);
		}
		
		Collections.sort(edges);
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("source " + s.toString() + " to dest " + t.toString() + "\n");
		sb.append("The Edges of the segment: \n");

		for (int i=0; i<edges.size(); i++)
		{
			sb.append("Edge "+i+ " With cost " + edges.get(i).toString() +"\n");
		}

		return sb.toString();
	}
}

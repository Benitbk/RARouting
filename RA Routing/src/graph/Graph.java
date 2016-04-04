package graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {

	int m = 4;

	Vertex s;
	Vertex t;
	List<Vertex> vertices;
	List<Segment> segments;

	public Graph(int m) {
		super();
		this.m = m;
		
		t = new Vertex(0);
		vertices.add(t);

		vertices = new ArrayList<Vertex>();
		Segment prevSegment = null;
		Vertex prevVertex = t;
		for (int i = 1; i < m; i++) {
			Vertex v = new Vertex(i);
			if (prevSegment != null)
				v.entering.add(prevSegment);

			vertices.add(v);

			Segment segment = new Segment(prevVertex, v);
			segments.add(segment);
			v.leaving.add(segment);

			prevSegment = segment;
			prevVertex = v;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

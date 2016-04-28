package graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {

	int length;

	public List<Vertex> vertices;
	public List<Segment> segments;

	public Graph(int length) {
		this.length = length;

		Vertex first = new Vertex(0);
		vertices = new ArrayList<Vertex>();
        segments = new ArrayList<Segment>();
		vertices.add(first);

		Segment prevSegment = null;
		Vertex prevVertex = first;
		for (int i = 1; i < length; i++) {
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

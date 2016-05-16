package segmentsGraph.graph;

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

		Vertex prevVertex = first;
		for (int i = 1; i < length; i++) {
			Vertex v = new Vertex(i);
			vertices.add(v);

			Segment segment = new Segment(prevVertex, v);
			segments.add(segment);
			prevVertex.leaving.add(segment);
			v.entering.add(segment);

			prevVertex = v;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < segments.size(); i++) {
			sb.append("Segment " + i + ": " + segments.get(i).toString()
					+ "\n");
		}

		return sb.toString();
	}

}

package graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
	public int id;

	public List<Segment> entering;
	public List<Segment> leaving;

	public Vertex(int id, List<Segment> entering, List<Segment> leaving) {
		super();
		this.id = id;
		this.entering = entering;
		this.leaving = leaving;
	}

	public Vertex(int id) {
		super();
		this.id = id;

		this.entering = new ArrayList<Segment>();
		this.leaving = new ArrayList<Segment>();
	}

}

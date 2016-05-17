package seriesParallelGraph;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Edge extends SPGraph {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8702323950488821703L;

	int id;

	static int nextId = 0;

	public Edge(Vertex s, Vertex t, double cost) {
		super();
		this.id = nextId++;
		this.s = s;
		this.t = t;
		this.cost = cost;

		s.leaving.add(this);
		t.entering.add(this);
	}

	double cost;
	int agents;

	@Override
	public Route solve() {
		return new Route(this);
	}

	@Override
	public SubSPGraph generateSubGraphFromVerticesRecursive(Vertex s, Vertex t) {
		return new SubSPGraph(this, this.s == s, this.t == t);
	}

	@Override
	public float getLength() {
		return 1;
	}

	@Override
	public float getWidth() {
		return 0.6f;
	}

	@Override
	protected STPair locateRecursive(Map<Vertex, Point> vertsLoc, float x,
			float y, float length, float width) {
		return new STPair(s, t);
	}

	@Override
	public Set<Vertex> getVerticesRecursive() {
		Set<Vertex> set = new HashSet<Vertex>();
		set.add(this.s);
		set.add(this.t);
		return set;
	}

	@Override
	public String toString() {
		return "(" + s.toString() + "," + t.toString() + ")";
	}

}

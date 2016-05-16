package seriesParallelGraph;

import java.util.Map;

public class Edge extends SPGraph {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8702323950488821703L;
	
	int id;

	public Edge(int id, Vertex s, Vertex t, double cost) {
		super();
		this.id = id;
		this.s = s;
		this.t = t;
		this.cost = cost;

		s.leaving.add(this);
		t.entering.add(this);
	}

	double cost;
	int agents;

	@Override
	public Path solve() {
		return new Path(this);
	}

	@Override
	public SubSPGraph GenerateSubGraphFromVerticesRecursive(Vertex s, Vertex t) {
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
	public String toString() {
		return s.toString() + ">" + t.toString();
	}

}

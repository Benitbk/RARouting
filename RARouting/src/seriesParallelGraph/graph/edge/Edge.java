package seriesParallelGraph.graph.edge;

import seriesParallelGraph.game.gui.Point;
import seriesParallelGraph.graph.*;

import java.util.*;

public abstract class Edge extends SPGraph {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8702323950488821703L;

	public int id;

	static int nextId = 0;

	public Edge(Vertex s, Vertex t) {
		super();
		this.id = nextId++;
		this.s = s;
		this.t = t;

		s.leaving.add(this);
		t.entering.add(this);
	}

	public int load;
	
	public double averageLoad = 0;

	@Override
	public Route findBestSTPath() {
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
	public List<Edge> getEdgesRecursive() {
		List<Edge> edges = new ArrayList<Edge>();
		edges.add(this);
		return edges;
	}

	@Override
	public Route generateRandomRoute() {
		return new Route(this);
	}

	@Override
	public String toString() {
		return "(" + s.toString() + "," + t.toString() + ")";
	}

	public abstract double getCostForSingleAgent();

	public double getForecastedCostForSingleAgent() {
		this.load++;
		double cost = this.getCostForSingleAgent();
		this.load--;
		return cost;
	}

	public String getLabel() {
		return "" + load;
	}

	@Override
	public void refresh() {
	}

	@Override
	public List<Route> getStrategies() {
		ArrayList<Route> strategies = new ArrayList<Route>();
		strategies.add(new Route(this));
		return strategies;
	}

}

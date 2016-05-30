package seriesParallelGraph.graph;

import seriesParallelGraph.graph.edge.Edge;
import seriesParallelGraph.graph.panel.Point;

import java.security.InvalidParameterException;
import java.util.*;

public class SeriesGraph extends SPGraph {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1067598364719934208L;

	SPGraph g1;
	SPGraph g2;

	float width;
	float length;

	public SeriesGraph(SPGraph g1, SPGraph g2) {
		this(g1, g2, false);
	}

	public SeriesGraph(SPGraph g1, SPGraph g2, boolean connect) {
		this.g1 = g1;
		this.g2 = g2;

		if (connect) {
			if (g1.t != g2.s) {
				g1.t.merge(g2.s);
			}
		}

		this.s = g1.s;
		this.t = g2.t;

		calcSize();
	}

	public void calcSize() {
		this.length = g1.getLength() + g2.getLength();
		if (g1.getWidth() + g2.getWidth() < 1)
			this.width = 1;
		else
			this.width = Math.max(g1.getWidth(), g2.getWidth());
	}

	@Override
	public Route solve() {
		Route route = g1.solve();
		route.edges.addAll(g2.solve().edges);
		return route;
	}

	@Override
	public Route generateRandomRoute()
	{
		Route route1 = g1.generateRandomRoute();
		Route route2 = g2.generateRandomRoute();
		route1.edges.addAll(route2.edges);
		return route1;
	}

	@Override
	public SubSPGraph generateSubGraphFromVerticesRecursive(Vertex s, Vertex t) {
		SubSPGraph subG1Graph = g1.generateSubGraphFromVerticesRecursive(s, t);
		SubSPGraph subG2Graph = g2.generateSubGraphFromVerticesRecursive(s, t);

		if (subG1Graph.tExists && subG2Graph.sExists) {
			throw new InvalidParameterException("no path from s to t");
		}

		if (subG1Graph.sExists && subG1Graph.tExists) {
			return subG1Graph;
		}
		if (subG2Graph.sExists && subG2Graph.tExists) {
			return subG2Graph;
		}

		if (subG1Graph.sExists) {
			if (subG2Graph.tExists) {
				return new SubSPGraph(new SeriesGraph(subG1Graph.graph,
						subG2Graph.graph, false), true, true);
			}
			return new SubSPGraph(new SeriesGraph(subG1Graph.graph, g2, false),
					true, false);
		}

		if (subG2Graph.tExists) {
			return new SubSPGraph(new SeriesGraph(g1, subG2Graph.graph, false),
					false, true);
		}
		if (subG1Graph.tExists) {
			return subG1Graph;
		}
		if (subG2Graph.sExists) {
			return subG2Graph;
		}
		return new SubSPGraph(this, false, false);
	}

	@Override
	public float getLength() {
		return this.length;
	}

	@Override
	public float getWidth() {
		return this.width;
	}

	@Override
	protected STPair locateRecursive(Map<Vertex, Point> vertsLoc, float x,
                                     float y, float length, float width) {
		// ratio between the length given for drawing the graph and the length
		// that is needed
		float r = length / (float) this.getLength();
		Point commonVertLoc = new Point(x + r * g1.getLength(), y + width / 2);

//		STPair stPair = g1.locateRecursive(vertsLoc, x,
//				y + (width - g1.getWidth()) / 2, r * g1.getLength(), g1.getWidth());
		STPair stPair = g1.locateRecursive(vertsLoc, x,
				y , r * g1.getLength(), width);

		// Point commonVertLoc = new Point(x + g1.getLength() - 1, y + width /
		// 2);
		vertsLoc.put(stPair.t, commonVertLoc);

		stPair.t = g2.locateRecursive(vertsLoc, x + r * g1.getLength(), y, r
				* g2.getLength(), width).t;
		return stPair;
	}

    @Override
    public Set<Vertex> getVerticesRecursive() {
        Set<Vertex> set = new HashSet<Vertex>();
        set.addAll(this.g1.getVerticesRecursive());
        set.addAll(this.g2.getVerticesRecursive());
        return set;
    }

    @Override
    public List<Edge> getEdgesRecursive() {
        List<Edge> edges = new ArrayList<Edge>();
        edges.addAll(this.g1.getEdgesRecursive());
        edges.addAll(this.g2.getEdgesRecursive());
        return edges;
    }

	@Override
	public void refresh() {
		g1.refresh();
		g2.refresh();
		
		this.s = g1.s;
		this.t = g2.t;

		calcSize();
	}
}

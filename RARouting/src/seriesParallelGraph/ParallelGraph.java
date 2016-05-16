package seriesParallelGraph;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ParallelGraph extends SPGraph {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3057604233501120367L;

	SPGraph g1;
	SPGraph g2;

	float width;
	float length;

	public ParallelGraph(SPGraph g1, SPGraph g2) {
		this(g1, g2, false);
	}

	public ParallelGraph(SPGraph g1, SPGraph g2, boolean connect) {
		super();

		// if (g1.getWidth() > g2.getWidth()) {
		// SPGraph tmp = g1;
		// g1 = g2;
		// g2 = tmp;
		// }

		this.g1 = g1;
		this.g2 = g2;

		if (connect) {
			if (g1.s != g2.s)
				g1.s.merge(g2.s);

			if (g1.t != g2.t)
				g1.t.merge(g2.t);
		}

		this.s = g1.s;
		this.t = g1.t;

		this.length = Math.max(g1.getLength(), g2.getLength());
		this.width = g1.getWidth() + g2.getWidth();
	}

	public SPGraph getG1() {
		return g1;
	}

	public SPGraph getG2() {
		return g2;
	}

	@Override
	public Route solve() {
		Route route1 = g1.solve();
		Route route2 = g2.solve();

		if (route1.newCost() < route2.newCost())
			return route1;
		return route2;
	}

	@Override
	public SubSPGraph generateSubGraphFromVerticesRecursive(Vertex s, Vertex t) {
		SubSPGraph subG1Graph = g1.generateSubGraphFromVerticesRecursive(s, t);
		SubSPGraph subG2Graph = g2.generateSubGraphFromVerticesRecursive(s, t);

		// if (subG1Graph.sExists && subG1Graph.tExists) {
		// if (subG2Graph.sExists && subG2Graph.tExists) {
		// return new SubSPGraph(this, true, true);
		// }
		//
		// return subG1Graph;
		//
		// }
		// if (subG2Graph.sExists && subG2Graph.tExists) {
		// return subG2Graph;
		// }
		//
		// if (subG1Graph.sExists && subG2Graph.sExists) {
		// return new SubSPGraph(this, true, false);
		// }
		// if (subG1Graph.tExists && subG2Graph.tExists) {
		// return new SubSPGraph(this, false, true);
		// }

		// simplify all the conditions above
		if (subG1Graph.sExists == subG2Graph.sExists
				&& subG1Graph.tExists == subG2Graph.tExists) {
			return new SubSPGraph(this, subG1Graph.sExists, subG1Graph.tExists);
		}
		if (subG1Graph.sExists && subG1Graph.tExists) {
			return subG1Graph;
		}
		if (subG2Graph.sExists && subG2Graph.tExists) {
			return subG2Graph;
		}
		// end of simplify

		if ((subG1Graph.sExists && subG2Graph.tExists)
				|| (subG1Graph.tExists && subG2Graph.sExists))
			throw new InvalidParameterException(
					"s and t are in parallel graphs");

		if (subG1Graph.sExists || subG1Graph.tExists) {
			return subG1Graph;
		}

		if (subG2Graph.sExists || subG2Graph.tExists) {
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
		STPair stPair = g1.locateRecursive(vertsLoc, x, y, length,
				g1.getWidth());
		g2.locateRecursive(vertsLoc, x, y + g1.getWidth(), length,
				g2.getWidth());

		return stPair;
	}

    @Override
    public Set<Vertex> getVerticesRecursive() {
        Set<Vertex> set = new HashSet<>();
        set.addAll(this.g1.getVerticesRecursive());
        set.addAll(this.g2.getVerticesRecursive());
        return set;
    }
}

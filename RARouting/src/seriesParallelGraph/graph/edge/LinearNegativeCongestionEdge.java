package seriesParallelGraph.graph.edge;

import seriesParallelGraph.graph.Vertex;

/**
 * Created by benitbk on 23/05/2016.
 */
public class LinearNegativeCongestionEdge extends Edge {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4581791098774497391L;

	public double factor;

	public LinearNegativeCongestionEdge(Vertex s, Vertex t, double factor) {
		super(s, t);
		this.factor = factor;
	}

	@Override
	public double getCostForSingleAgent() {
		return factor * this.load;
	}

	@Override
	public String toString() {
		return super.toString() + ":" + this.factor;
	}

	@Override
	public String getLabel() {
		return "" + factor + "/" + this.load;
	}
}

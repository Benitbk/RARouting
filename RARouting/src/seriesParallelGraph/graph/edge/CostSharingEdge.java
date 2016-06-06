package seriesParallelGraph.graph.edge;

import seriesParallelGraph.graph.Vertex;

/**
 * Created by benitbk on 23/05/2016.
 */
public class CostSharingEdge extends Edge {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2741414792845886199L;

	public double cost;

	public CostSharingEdge(Vertex s, Vertex t, double cost) {
		super(s, t);
		this.cost = cost;
	}

	@Override
	public double getCostForSingleAgent() {
		return this.cost / this.load;
	}

	@Override
	public String toString() {
		return super.toString() + ":" + this.cost;
	}

	@Override
	public String getLabel() {
		return "" + cost + "/" + this.load;
	}

}

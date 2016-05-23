package seriesParallelGraph.graph.edge;

import seriesParallelGraph.graph.Vertex;

/**
 * Created by benitbk on 23/05/2016.
 */
public class LinearNegativeCongestionEdge extends Edge {
    double factor;

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
        return super.toString() + ": " + this.factor;
    }
}

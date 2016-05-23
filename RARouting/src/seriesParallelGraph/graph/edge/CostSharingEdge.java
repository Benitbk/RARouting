package seriesParallelGraph.graph.edge;

import seriesParallelGraph.graph.Vertex;

/**
 * Created by benitbk on 23/05/2016.
 */
public class CostSharingEdge extends Edge{

    double cost;
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
        return super.toString() + " :" + this.cost;
    }
}

package seriesParallelGraph;


/**
 * Created by benitbk on 09/05/2016.
 */
public class SubSPGraph {

    boolean sExists;
    boolean tExists;
    SPGraph graph;
    public SubSPGraph(SPGraph graph, boolean sExists, boolean tExists)
    {
        this.sExists = sExists;
        this.tExists = tExists;
        this.graph = graph;
    }

}

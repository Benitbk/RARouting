package seriesParallelGraph;

import java.security.InvalidParameterException;

public class SeriesGraph extends SPGraph {
	SPGraph g1;
	SPGraph g2;

    public SeriesGraph(SPGraph g1, SPGraph g2) {
        this.g1 = g1;
        this.g2 = g2;
    }

    @Override
	public Path solve() {
		Path path = g1.solve();
		path.edges.addAll(g2.solve().edges);
		return path;
	}


	@Override
	public SubSPGraph GenerateSubGraphFromVerticesRecursive(Vertex s, Vertex t) {
		SubSPGraph subG1Graph = g1.GenerateSubGraphFromVerticesRecursive(s, t);
		SubSPGraph subG2Graph = g2.GenerateSubGraphFromVerticesRecursive(s, t);

        if(subG1Graph.sExists && subG1Graph.tExists)
        {
            return subG1Graph;
        }
        if(subG2Graph.sExists && subG2Graph.tExists)
        {
            return subG2Graph;
        }

        if(subG1Graph.sExists)
        {
            if(subG2Graph.tExists)
            {
                return new SubSPGraph(new SeriesGraph(subG1Graph.graph, subG2Graph.graph), true, true);
            }
            return new SubSPGraph(new SeriesGraph(subG1Graph.graph, g2), true, false);
        }

        if(subG2Graph.tExists)
        {
            return new SubSPGraph(new SeriesGraph(g1, subG2Graph.graph), false, true);
        }
        if(subG1Graph.tExists)
        {
            return subG1Graph;
        }
        if(subG2Graph.sExists)
        {
            return subG2Graph;
        }
    }

}

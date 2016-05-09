package seriesParallelGraph;

public abstract class SPGraph {
	public abstract Path solve();

	public abstract SubSPGraph GenerateSubGraphFromVerticesRecursive(Vertex s,
			Vertex t);

	public SPGraph GenerateSubGraphFromVertices(Vertex s, Vertex t) {
		SubSPGraph subSPGraph = this
				.GenerateSubGraphFromVerticesRecursive(s, t);
		if (subSPGraph.sExists && subSPGraph.tExists)
			return subSPGraph.graph;
		return null;
	}

}

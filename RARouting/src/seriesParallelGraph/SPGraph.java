package seriesParallelGraph;

public abstract class SPGraph {
	public abstract Path solve();
    public abstract SubSPGraph GenerateSubGraphFromVerticesRecursive(Vertex s, Vertex t);
    public SPGraph GenerateSubGraphFromVertices(Vertex s, Vertex t)
    {
        return GenerateSubGraphFromVerticesRecursive(s, t).graph;
    }

}

package seriesParallelGraph;

public class SeriesGraph implements SPGraph {
	SPGraph g1;
	SPGraph g2;

	@Override
	public Path solve() {
		Path path = g1.solve();
		path.edges.addAll(g2.solve().edges);
		return path;
	}

}

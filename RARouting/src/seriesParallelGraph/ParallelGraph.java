package seriesParallelGraph;

public class ParallelGraph implements SPGraph {

	SPGraph g1;
	SPGraph g2;
	
	@Override
	public Path solve() {
		Path path1 = g1.solve();
		Path path2 = g2.solve();
		
		if(path1.cost() < path2.cost())
			return path1;
		return path2;
	}

}

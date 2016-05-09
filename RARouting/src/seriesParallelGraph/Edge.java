package seriesParallelGraph;


public class Edge implements SPGraph {
	
	Vertex s;
	Vertex t;
	double cost;
	int agents;

	@Override
	public Path solve() {
		return new Path(this);
	}

}

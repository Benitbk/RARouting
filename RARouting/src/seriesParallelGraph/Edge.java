package seriesParallelGraph;


public class Edge extends SPGraph {
	
	Vertex s;
	Vertex t;
	double cost;
	int agents;

	@Override
	public Path solve() {
		return new Path(this);
	}

	@Override
	public SubSPGraph GenerateSubGraphFromVerticesRecursive(Vertex s, Vertex t) {
		return new SubSPGraph(this,this.s == s,this.t == t);
	}


}

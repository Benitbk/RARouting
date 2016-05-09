package seriesParallelGraph;


public class Edge extends SPGraph {
	
	int id;
	Vertex s;
	Vertex t;
	public Edge(int id, Vertex s, Vertex t, double cost) {
		super();
		this.id = id;
		this.s = s;
		this.t = t;
		this.cost = cost;
		
		s.leaving.add(this);
		t.entering.add(this);
	}

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

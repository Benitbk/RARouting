package seriesParallelGraph;

import java.util.ArrayList;
import java.util.List;

public class Path {
	
	List<Edge> edges;

	public Path(Edge edge) {
		this.edges = new ArrayList<Edge>();
		edges.add(edge);
	}

	public double cost() {
		// TODO Auto-generated method stub
		return 0;
	}
}

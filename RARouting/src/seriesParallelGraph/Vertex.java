package seriesParallelGraph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

	List<Edge> leaving;
	List<Edge> entering;
	
	int id;

	public Vertex(int i) {
		this.id = i;
		
		leaving = new ArrayList<Edge>();
		entering = new ArrayList<Edge>();
	}
}

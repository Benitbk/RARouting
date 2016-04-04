package graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {

	int m = 4;
	
	List<Vertex> vertices;

	public Graph(int m) {
		super();
		this.m = m;
		
		vertices = new ArrayList<Vertex>;
		Segment prevS = null;
		for (int i = 0; i < m; i++) {
			Vertex v = new Vertex(i);
			v.entering = 
			vertices.add(v);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

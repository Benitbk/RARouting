package seriesParallelGraph.graph;

import seriesParallelGraph.graph.edge.Edge;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Vertex implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3527824038366921294L;

	public List<Edge> leaving;
	public List<Edge> entering;

	public int id;

	static int nextId = 0;

	public Vertex() {
		this.id = nextId++;

		leaving = new ArrayList<Edge>();
		entering = new ArrayList<Edge>();
	}

	public Vertex(int i) {
		this.id = i;

		leaving = new ArrayList<Edge>();
		entering = new ArrayList<Edge>();
	}

	public Vertex[] getParents() {
		Vertex[] parents = new Vertex[entering.size()];
		int i = 0;
		for (Edge edge : entering) {
			parents[i++] = edge.s;
		}
		return parents;
	}

	public Vertex[] getChildren() {
		Vertex[] children = new Vertex[leaving.size()];
		int i = 0;
		for (Edge edge : leaving) {
			children[i++] = edge.t;
		}
		return children;
	}

	@Override
	public String toString() {
		return "" + id;
	}

	public void merge(Vertex v) {
		this.leaving.addAll(v.leaving);
		for (Edge edge : v.leaving) {
			edge.s = this;
		}
		v.leaving = null;

		this.entering.addAll(v.entering);
		for (Edge edge : v.entering) {
			edge.t = this;
		}
		v.entering = null;

	}

}

package seriesParallelGraph.graph;

import seriesParallelGraph.graph.edge.CostSharingEdge;
import seriesParallelGraph.graph.edge.Edge;
import seriesParallelGraph.graph.edge.EdgeKind;
import seriesParallelGraph.graph.edge.LinearNegativeCongestionEdge;
import seriesParallelGraph.graph.panel.Point;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public abstract class SPGraph implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2228953572891708338L;

	public Vertex s;
	public Vertex t;

	public abstract Route solve();

	protected abstract SubSPGraph generateSubGraphFromVerticesRecursive(
			Vertex s, Vertex t);

	public SPGraph generateSubGraphFromVertices(Vertex s, Vertex t) {
		SubSPGraph subSPGraph = this
				.generateSubGraphFromVerticesRecursive(s, t);
		if (subSPGraph.sExists && subSPGraph.tExists)
			return subSPGraph.graph;
		return null;
	}

	/**
	 * The length of the graph is the longest s-t path in the graph. It is
	 * needed for drawing.
	 * 
	 * @return The length of the graph.
	 */
	public abstract float getLength();

	/**
	 * The width of the graph is the maximum number of edges parallel to each
	 * other
	 * 
	 * @return The width of the graph.
	 */
	public abstract float getWidth();

	/**
	 * @param vertsLoc
	 * @param graph
	 * @return
	 */
	protected abstract STPair locateRecursive(Map<Vertex, Point> vertsLoc,
			float x, float y, float length, float width);

	/**
	 * Assign each vertex a location an the 2D plane for drawing.
	 * 
	 * @param length
	 * @param width
	 * @return
	 */
	public Map<Vertex, Point> locate(float length, float width) {
		Map<Vertex, Point> vertsLoc = new HashMap<Vertex, Point>();
		STPair stPair = this.locateRecursive(vertsLoc, 0, 0, length, width);

		vertsLoc.put(stPair.s, new Point(0, width / 2));
		vertsLoc.put(stPair.t, new Point(length, width / 2));

		return vertsLoc;
	}

	public static SPGraph randomizeGraph(int size, int maxEdgeCost, double parallelProbability, EdgeKind edgeKind) {
		Random random = new Random();
		List<SPGraph> graphs = new ArrayList<SPGraph>();
		for (int i = 0; i < size; i++) {
            if(edgeKind == EdgeKind.CostSharing)
            {
                graphs.add(new CostSharingEdge(new Vertex(), new Vertex(), random.nextInt(maxEdgeCost) + 1));
            }
            else
            {
                graphs.add(new LinearNegativeCongestionEdge(new Vertex(), new Vertex(), random
                        .nextInt(maxEdgeCost) + 1));
            }

		}

		while (graphs.size() > 1) {
			int i1 = 0;
			int i2 = 0;
			while (i1 >= i2) {
				i1 = random.nextInt(graphs.size());
				i2 = random.nextInt(graphs.size());
			}

			if (random.nextDouble() <= parallelProbability)
				graphs.add(new ParallelGraph(graphs.get(i1), graphs.get(i2),
						true));
			else
				graphs.add(new SeriesGraph(graphs.get(i1), graphs.get(i2), true));

			graphs.remove(i2);
			graphs.remove(i1);

		}
		SPGraph graph = graphs.get(0);
		graph.refresh();

		FileOutputStream fout;
		try {
			fout = new FileOutputStream("last graph");
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(graph);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return graph;
	}

	public abstract  Route generateRandomRoute();

	public static SPGraph read(String name) throws Exception {
		// read object from file
		FileInputStream fis = new FileInputStream(name);
		ObjectInputStream ois = new ObjectInputStream(fis);
		SPGraph g = (SPGraph) ois.readObject();
		ois.close();
		return g;
	}

	public List<Vertex> getVertices() {
		List<Vertex> result = new ArrayList<Vertex>();
		result.addAll(this.getVerticesRecursive());
		return result;
	}

	public abstract Set<Vertex> getVerticesRecursive();

    public List<Edge> getEdges() {
        List<Edge> edges = new ArrayList<>();
        edges.addAll(this.getEdgesRecursive());
        return edges;
    }

    public abstract List<Edge> getEdgesRecursive();
    
    /**
     * Updates the fields of the graph recursively
     */
    public abstract void refresh();
}

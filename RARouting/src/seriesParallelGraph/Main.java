package seriesParallelGraph;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		SPGraph g = SPGraph.randomizeGraph(50, 50);
//		SPGraph g = null;
//		try {
//			g = SPGraph.read("last graph");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		new SPGraphFrame(g);
//		SPGraph h = ((ParallelGraph) g).getG1();
//		new SPGraphFrame(h);
//		h = ((ParallelGraph) h).g2;
//		new SPGraphFrame(h);
//		SPGraph h2 = ((ParallelGraph) h).g2;
//		new SPGraphFrame(h2);

//		Vertex s = g.s;
		// s = s.leaving.get(0).t;
//		System.out.println(s);
//
//		Vertex t = s.leaving.get(3).t;
//		System.out.println(t);
//		t = t.leaving.get(0).t;
//		System.out.println(t);
//		t = t.leaving.get(0).t;
//		System.out.println(t);
//		t = t.leaving.get(0).t;
//		System.out.println(t);
//
//		SPGraph subGraph = g.generateSubGraphFromVertices(s, t);

		List<Vertex> vertices = g.getVertices();
        List<Agent> agents = new ArrayList<>();
        for (int i = 0;i< 10; i++) {
            agents.add(Agent.randomizeAgent(g, vertices));
        }
        System.out.println(vertices);
        System.out.println(agents);

//		new SPGraphFrame(subGraph);

		// test();

	}

	public static void test() {
		Vertex[] v = new Vertex[14];
		for (int i = 0; i < v.length; i++) {
			v[i] = new Vertex();
		}

		// create edges
		Edge[] edges = new Edge[17];
		edges[0] = new Edge(0, v[0], v[1], 1);
		edges[1] = new Edge(1, v[1], v[2], 1);
		edges[2] = new Edge(2, v[2], v[3], 2);
		edges[3] = new Edge(3, v[3], v[4], 1);
		edges[4] = new Edge(4, v[1], v[5], 1);
		edges[5] = new Edge(5, v[5], v[6], 3);
		edges[6] = new Edge(6, v[6], v[4], 1);
		edges[7] = new Edge(7, v[0], v[7], 2);
		edges[8] = new Edge(8, v[7], v[4], 5);
		edges[9] = new Edge(9, v[0], v[8], 2);
		edges[10] = new Edge(10, v[8], v[9], 1);
		edges[11] = new Edge(11, v[9], v[10], 2);
		edges[12] = new Edge(12, v[10], v[11], 2);
		edges[13] = new Edge(13, v[11], v[4], 3);
		edges[14] = new Edge(14, v[8], v[10], 4);
		edges[15] = new Edge(15, v[8], v[12], 1);
		edges[16] = new Edge(16, v[12], v[10], 1);

		// create graph
		SPGraph g1 = new SeriesGraph(edges[1], edges[2]);
		g1 = new SeriesGraph(g1, edges[3]);

		SPGraph g2 = new SeriesGraph(edges[5], edges[6]);
		g2 = new SeriesGraph(edges[4], g2);

		g2 = new ParallelGraph(g1, g2);
		g1 = new SeriesGraph(edges[0], g2);

		g2 = new SeriesGraph(edges[7], edges[8]);
		g1 = new ParallelGraph(g1, g2);

		SPGraph h1 = new SeriesGraph(edges[10], edges[11]);
		h1 = new ParallelGraph(h1, edges[14]);

		SPGraph h2 = new SeriesGraph(edges[15], edges[16]);
		h2 = new ParallelGraph(h1, h2);

		h1 = new SeriesGraph(edges[9], h2);
		h2 = new SeriesGraph(edges[12], edges[13]);

		g2 = new SeriesGraph(h1, h2);

		g1 = new ParallelGraph(g1, g2);
		System.out.println("length: " + g1.getLength());
		System.out.println("width: " + g1.getWidth());

		new SPGraphFrame(g1);

		// test sub graph generation
		SPGraph subGraph = g1.generateSubGraphFromVertices(v[8], v[4]);

		new SPGraphFrame(subGraph);

		Route route = subGraph.solve();
		for (Edge edge : route.edges) {
			System.out.print(edge.id + ", ");
		}
		System.out.println();

		subGraph = g1.generateSubGraphFromVertices(v[0], v[4]);

		route = subGraph.solve();
		for (Edge edge : route.edges) {
			System.out.print(edge.id + ", ");
		}
		System.out.println();

		try {
			subGraph = g1.generateSubGraphFromVertices(v[7], v[3]);
		} catch (Exception e) {
			System.out.println(e.getMessage() + " - as expected");
		}
		try {
			subGraph = g1.generateSubGraphFromVertices(v[8], v[6]);
		} catch (Exception e) {
			System.out.println(e.getMessage() + " - as expected");
		}
		try {
			subGraph = g1.generateSubGraphFromVertices(v[3], v[1]);
		} catch (Exception e) {
			System.out.println(e.getMessage() + " - as expected");
		}
	}
}

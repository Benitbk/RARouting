package seriesParallelGraph;

import seriesParallelGraph.agent.Agent;
import seriesParallelGraph.game.GameState;
import seriesParallelGraph.graph.*;
import seriesParallelGraph.game.Game;
import seriesParallelGraph.game.GamePlayer;
import seriesParallelGraph.graph.edge.EdgeKind;
import seriesParallelGraph.graph.panel.SPGraphPanel;
import seriesParallelGraph.policies.AgentGreatestImprovePolicy;
import seriesParallelGraph.policies.AgentIncreasingPolicy;

import javax.swing.JFrame;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
        Map<Agent, Route> oldRoutes = new HashMap<>();

        Game game = Game.randomizeGame(15, 10, 5, 0.6, EdgeKind.LinearNegativeCongestion, true);
        for(Agent agent : game.agents)
        {
            oldRoutes.put(agent, agent.getRoute());
        }
        showSPGraph(game.graph);
        GameState gameState = new GameState(game);
        GamePlayer gamePlayer = new GamePlayer(gameState, new AgentGreatestImprovePolicy(gameState));
        gamePlayer.start();
        System.out.println("finished");
        System.out.println("old routes");
        for(Agent agent:oldRoutes.keySet()) {
            System.out.print(agent.id + "\t");
            System.out.println(oldRoutes.get(agent));
        }

		// showSPGraph(g);

		// test();∂®

	}

	private static void showSPGraph(SPGraph g) {
		JFrame frame = new JFrame();
		frame.add(new SPGraphPanel(g));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.repaint();
	}

	// public static void test() {
	// Vertex[] v = new Vertex[14];
	// for (int i = 0; i < v.length; i++) {
	// v[i] = new Vertex();
	// }
	//
	// // create edges
	// Edge[] edges = new Edge[17];
	// edges[0] = new Edge(v[0], v[1], 1);
	// edges[1] = new Edge(v[1], v[2], 1);
	// edges[2] = new Edge(v[2], v[3], 2);
	// edges[3] = new Edge(v[3], v[4], 1);
	// edges[4] = new Edge(v[1], v[5], 1);
	// edges[5] = new Edge(v[5], v[6], 3);
	// edges[6] = new Edge(v[6], v[4], 1);
	// edges[7] = new Edge(v[0], v[7], 2);
	// edges[8] = new Edge(v[7], v[4], 5);
	// edges[9] = new Edge(v[0], v[8], 2);
	// edges[10] = new Edge(v[8], v[9], 1);
	// edges[11] = new Edge(v[9], v[10], 2);
	// edges[12] = new Edge(v[10], v[11], 2);
	// edges[13] = new Edge(v[11], v[4], 3);
	// edges[14] = new Edge(v[8], v[10], 4);
	// edges[15] = new Edge(v[8], v[12], 1);
	// edges[16] = new Edge(v[12], v[10], 1);
	//
	// // create graph
	// SPGraph g1 = new SeriesGraph(edges[1], edges[2]);
	// g1 = new SeriesGraph(g1, edges[3]);
	//
	// SPGraph g2 = new SeriesGraph(edges[5], edges[6]);
	// g2 = new SeriesGraph(edges[4], g2);
	//
	// g2 = new ParallelGraph(g1, g2);
	// g1 = new SeriesGraph(edges[0], g2);
	//
	// g2 = new SeriesGraph(edges[7], edges[8]);
	// g1 = new ParallelGraph(g1, g2);
	//
	// SPGraph h1 = new SeriesGraph(edges[10], edges[11]);
	// h1 = new ParallelGraph(h1, edges[14]);
	//
	// SPGraph h2 = new SeriesGraph(edges[15], edges[16]);
	// h2 = new ParallelGraph(h1, h2);
	//
	// h1 = new SeriesGraph(edges[9], h2);
	// h2 = new SeriesGraph(edges[12], edges[13]);
	//
	// g2 = new SeriesGraph(h1, h2);
	//
	// g1 = new ParallelGraph(g1, g2);
	// System.out.println("length: " + g1.getLength());
	// System.out.println("width: " + g1.getWidth());
	//
	// showSPGraph(g1);
	//
	// // test sub graph generation
	// SPGraph subGraph = g1.generateSubGraphFromVertices(v[8], v[4]);
	//
	// showSPGraph(subGraph);
	//
	// Route route = subGraph.solve();
	// for (Edge edge : route.edges) {
	// System.out.print(edge.id + ", ");
	// }
	// System.out.println();
	//
	// subGraph = g1.generateSubGraphFromVertices(v[0], v[4]);
	//
	// route = subGraph.solve();
	// for (Edge edge : route.edges) {
	// System.out.print(edge.id + ", ");
	// }
	// System.out.println();
	//
	// try {
	// subGraph = g1.generateSubGraphFromVertices(v[7], v[3]);
	// } catch (Exception e) {
	// System.out.println(e.getMessage() + " - as expected");
	// }
	// try {
	// subGraph = g1.generateSubGraphFromVertices(v[8], v[6]);
	// } catch (Exception e) {
	// System.out.println(e.getMessage() + " - as expected");
	// }
	// try {
	// subGraph = g1.generateSubGraphFromVertices(v[3], v[1]);
	// } catch (Exception e) {
	// System.out.println(e.getMessage() + " - as expected");
	// }
	// }
}

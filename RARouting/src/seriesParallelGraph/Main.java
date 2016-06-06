package seriesParallelGraph;

import seriesParallelGraph.agent.Agent;
import seriesParallelGraph.game.GameState;
import seriesParallelGraph.game.results.GameResult;
import seriesParallelGraph.game.results.PolicyResult;
import seriesParallelGraph.graph.*;
import seriesParallelGraph.game.Game;
import seriesParallelGraph.game.GamePlayer;
import seriesParallelGraph.graph.edge.EdgeKind;
import seriesParallelGraph.graph.panel.SPGraphPanel;
import seriesParallelGraph.policies.AgentGreatestImprovePolicy;
import seriesParallelGraph.policies.AgentIncreasingPolicy;
import seriesParallelGraph.policies.AgentPolicy;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
        System.out.println("Started");

        Game game = getGame(false);
        GameResult gameResult = new GameResult(game);
        showSPGraph(game.graph);
        GameState gameState = new GameState(game);

        startWithPolicy(gameResult, gameState, new AgentIncreasingPolicy(gameState, EdgeKind.CostSharing));
        startWithPolicy(gameResult, gameState, new AgentGreatestImprovePolicy(gameState, EdgeKind.CostSharing)));
        for(PolicyResult policyResult: gameResult.policyResults) {
            System.out.println("Policy: " + policyResult.policyName);
            System.out.println("Number of steps: " + policyResult.steps.size());
            System.out.println("Final social cost: " + policyResult.steps.get(policyResult.steps.size() - 1).socialCost);
        }
        System.out.println("Started");
	}


    private static void startWithPolicy(GameResult gameResult, GameState gameState, AgentPolicy policy) {
        GamePlayer gamePlayer = new GamePlayer(gameState, policy);
        PolicyResult policyResult = new PolicyResult(policy.getClass().getSimpleName());
        gamePlayer.start(policyResult);
        System.out.println("finished policy: " + policyResult.policyName);
        for(Agent agent:gameState.game.agents) {
            gameResult.game.agents.stream().filter(initialAgent -> initialAgent.id == agent.id).forEach(initialAgent -> {
                agent.setRoute(initialAgent.getRoute());
            });
        }
        gameResult.policyResults.add(policyResult);

    }

    private static Game getGame(boolean fromCache) {
        Game game = null;
        if(!fromCache)
            game = Game.randomizeGame(100, 10, 50, 0.6, EdgeKind.LinearNegativeCongestion, true);
        else {
            try {
                game = Game.read("last game");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Failed reading game from file");
            }
        }
        return game;
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

package seriesParallelGraph.game;

import seriesParallelGraph.agent.Agent;
import seriesParallelGraph.graph.SPGraph;
import seriesParallelGraph.graph.Vertex;
import seriesParallelGraph.graph.edge.EdgeKind;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benitbk on 23/05/2016.
 */
public class Game {
    public final SPGraph graph;
    public final List<Agent> agents;

    public Game(SPGraph graph, List<Agent> agents)
    {
        this.graph = graph;
        this.agents = agents;
    }

    public static Game randomizeGame(int graphSize, int maxEdgeCost, int numberOfAgents, double parallelProbability,
                                     EdgeKind edgeKind, boolean randomizeAgents) {
        SPGraph graph = SPGraph.randomizeGraph(graphSize, maxEdgeCost, parallelProbability, edgeKind);

        List<Vertex> vertices = graph.getVertices();
        List<Agent> agents = new ArrayList<>();
        for (int i = 0; i < numberOfAgents; i++) {
            Agent agent = null;
            if(randomizeAgents) {
                agent = Agent.randomizeAgent(graph, vertices);
            }
            else {
                agent = new Agent(graph.s, graph.t);
            }

            SPGraph subGraph = graph.generateSubGraphFromVertices(agent.source,
                    agent.destination);
            agent.setRoute(subGraph.generateRandomRoute());
            agents.add(agent);
        }




        System.out.println("Agent\tRoute\tCost");
        for (int i = 0; i < numberOfAgents; i++) {
            Agent currAgent = agents.get(i);
            System.out.println(currAgent.toStringEx());
        }





        System.out.println(vertices);
        System.out.println(agents);
        System.out.println(graph.getEdges());
        return new Game(graph, agents);
    }
}

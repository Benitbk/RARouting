package seriesParallelGraph.game;

import seriesParallelGraph.agent.Agent;
import seriesParallelGraph.graph.SPGraph;
import seriesParallelGraph.graph.Vertex;

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

    public static Game randomizeGame(int graphSize, int maxEdgeCost, int numberOfAgents) {
        SPGraph graph = SPGraph.randomizeGraph(graphSize, maxEdgeCost);

        List<Vertex> vertices = graph.getVertices();
        List<Agent> agents = new ArrayList<>();
        for (int i = 0; i < numberOfAgents; i++) {
            Agent agent = Agent.randomizeAgent(graph, vertices);
            SPGraph subGraph = graph.generateSubGraphFromVertices(agent.source,
                    agent.destination);
            agent.setRoute(subGraph.generateRandomRoute());
            agents.add(agent);
        }

        System.out.println("Agent\tRoute\tCost");
        for (int i = 0; i < numberOfAgents; i++) {
            Agent currAgent = agents.get(i);
            System.out.println("" + currAgent.id + "\t" + currAgent.getRoute()
                    + "\t" + currAgent.getRoute().cost());
        }

        System.out.println(vertices);
        System.out.println(agents);
        System.out.println(graph.getEdges());
        return new Game(graph, agents);
    }
}

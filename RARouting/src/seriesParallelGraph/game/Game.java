package seriesParallelGraph.game;

import seriesParallelGraph.agent.Agent;
import seriesParallelGraph.graph.SPGraph;
import seriesParallelGraph.graph.Vertex;
import seriesParallelGraph.graph.edge.EdgeKind;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by benitbk on 23/05/2016.
 */
public class Game implements Serializable {

    private static final long serialVersionUID = 2228953572123708335L;

    public final SPGraph graph;
    public final List<Agent> agents;

    public Game(SPGraph graph, List<Agent> agents)
    {
        this.graph = graph;
        this.agents = agents;
    }

    public double getSocialCost() {
        int socialCost = 0;
        for(Agent agent : this.agents) {
            socialCost += agent.getRoute().cost();
        }
        return socialCost;
    }


    public static Game read(String name) throws Exception {
        // read object from file
        FileInputStream fis = new FileInputStream(name);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Game g = (Game) ois.readObject();
        ois.close();
        return g;
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


        Game game = new Game(graph, agents);
        game.write("last game");
        System.out.println(vertices);
        System.out.println(agents);
        System.out.println(graph.getEdges());
        return game;
    }


    public void write(String fileName) {
        FileOutputStream fout;
        try {
            fout = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(this);
            fout.close();
        } catch (Exception e) {
            System.out.println("Failed writing game to file");
            e.printStackTrace();
        }
    }
}

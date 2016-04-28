package game;

import agent.Agent;
import graph.Graph;
import interfaces.GamePolicy;

import java.util.List;

public class Game {

    public final Graph graph;
    public final List<Agent> agents;


    public Game(Graph graph, List<Agent> agents)
    {
        this.graph = graph;
        this.agents = agents;
    }
}

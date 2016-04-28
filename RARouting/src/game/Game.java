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

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("The Agents in the game are: \n");
        for (int i=0; i<agents.size(); i++)
        {
            sb.append(agents.get(i).toString() +"\n");
        }

        sb.append("********************\n");
        sb.append("The Graph combined from segments: \n");
        sb.append(graph.toString());

        return sb.toString();
    }
}

package segmentsGraph.game;

import segmentsGraph.agent.Agent;
import segmentsGraph.graph.Graph;

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
        sb.append("The Agents in the segmentsGraph.game are: \n");
        for (int i=0; i<agents.size(); i++)
        {
            sb.append(agents.get(i).toString() +"\n");
        }

        sb.append("********************\n");
        sb.append("The graph combined from segments: \n");
        sb.append(graph.toString());

        return sb.toString();
    }
}

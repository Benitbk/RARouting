package seriesParallelGraph;

import seriesParallelGraph.agent.Agent;
import seriesParallelGraph.graph.ParallelGraph;
import seriesParallelGraph.graph.SPGraph;
import seriesParallelGraph.graph.SeriesGraph;
import seriesParallelGraph.graph.Vertex;
import seriesParallelGraph.graph.edge.Edge;
import seriesParallelGraph.graph.edge.LinearNegativeCongestionEdge;

import java.util.List;

/**
 * Created by benitbk on 23/05/2016.
 */
public class Tests {
    public static SPGraph generateGraph() {
        Vertex[] vertices = {new Vertex(), new Vertex(), new Vertex()};
        Edge e1 = new LinearNegativeCongestionEdge(vertices[0], vertices[1], 5);
        Edge e2 = new LinearNegativeCongestionEdge(vertices[0], vertices[2], 3);
        Edge e3 = new LinearNegativeCongestionEdge(vertices[2], vertices[1], 3);
        SeriesGraph sg = new SeriesGraph(e2, e3);
        return new ParallelGraph(e1, sg);

    }

    public static Agent[] generateAgents(SPGraph g) {
        List<Vertex> vertices = g.getVertices();
        return new Agent[]{new Agent(vertices.get(2), vertices.get(1)), new Agent(vertices.get(2), vertices.get(1))};
    }
}

package seriesParallelGraph;

import java.util.*;

/**
 * Created by benitbk on 09/05/2016.
 */
public class Agent {
    public final long id;
    public final Vertex source;
    public final Vertex destination;
    public static long currentId = 0;
    public Path path;
    public Agent(Vertex source, Vertex destination) {
        this.id = currentId++;
        this.source = source;
        this.destination = destination;
        path = new Path();
    }

    @Override
    public String toString() {
        return ("Id " + id + ": " + source.toString() + "-" + destination
                .toString());
    }

    public static Agent randomizeAgent(SPGraph graph, List<Vertex> vertices) {
        Random random = new Random();
        Vertex v1 = vertices.get(random.nextInt(vertices.size()));
        List<Vertex> reachableVertices = new ArrayList<>();
        List<Vertex> targetVertices = new ArrayList<>();
        List<Vertex> sourceVertices = new ArrayList<>();
        if(v1 != graph.t)
        {
            targetVertices.addAll(graph.generateSubGraphFromVertices(v1, graph.t).getVertices());
            reachableVertices.addAll(targetVertices);
        }

        if(v1 != graph.s)
        {
            sourceVertices.addAll(graph.generateSubGraphFromVertices(graph.s, v1).getVertices());
            reachableVertices.addAll(sourceVertices);
        }
        reachableVertices.removeAll(Collections.singleton(v1));
        Vertex v2 = reachableVertices.get(random.nextInt(reachableVertices.size()));
        if(targetVertices.contains(v2)) {
            return new Agent(v1, v2);
        }
        return new Agent(v2, v1);
    }
}

package agent;

import graph.Edge;
import graph.Vertex;

import java.util.List;

/**
 * Created by benitbk on 11/04/2016.
 */
public class Agent {
    public final long id;
    public final Vertex source;
    public final Vertex destination;

    public Agent(int id, Vertex source, Vertex destination)
    {
        this.id = id;
        this.source = source;
        this.destination = destination;

    }
}
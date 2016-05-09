package seriesParallelGraph;

/**
 * Created by benitbk on 09/05/2016.
 */
public class Agent {
    public final long id;
    public final Vertex source;
    public final Vertex destination;

    public Agent(int id, Vertex source, Vertex destination) {
        this.id = id;
        this.source = source;
        this.destination = destination;

    }

    @Override
    public String toString() {
        return ("Id " + id + ": " + source.toString() + "-" + destination
                .toString());
    }
}

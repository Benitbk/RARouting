package segmentsGraph.agent;

import segmentsGraph.graph.Edge;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benitbk on 28/04/2016.
 */
public class AgentRoute {
    public List<Edge> edges;
    public AgentRoute(List<Edge> edges)
    {
        this.edges = edges;
    }
    public AgentRoute()
    {
        this.edges = new ArrayList<Edge>();
    }

	@Override
	public String toString() {
		return edges.toString();
	}
}



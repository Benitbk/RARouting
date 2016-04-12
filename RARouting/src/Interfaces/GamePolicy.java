package interfaces;

/**
 * Created by benitbk on 11/04/2016.
 */
public class GamePolicy {
    public AgentPolicy agentPolicy;
    public RoutingPolicy routingPolicy;

    public GamePolicy(AgentPolicy agentPolicy, RoutingPolicy routingPolicy)
    {
        this.agentPolicy = agentPolicy;
        this.routingPolicy = routingPolicy;

    }
}

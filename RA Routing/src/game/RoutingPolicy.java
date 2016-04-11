package game;

import agent.Agent;

/**
 * Created by benitbk on 11/04/2016.
 */
public abstract class RoutingPolicy {

    private final Game game;

    public RoutingPolicy(Game game)
    {
        this.game = game;
    }
    abstract void improveAgentPath(Agent agent);
}

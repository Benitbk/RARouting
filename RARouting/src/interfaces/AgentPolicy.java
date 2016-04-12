package interfaces;

import agent.Agent;
import game.Game;

/**
 * Created by benitbk on 11/04/2016.
 */
public abstract class AgentPolicy {

    private final Game game;

    public AgentPolicy(Game game)
    {
        this.game = game;
    }
    public abstract Agent getNextAgent();
}

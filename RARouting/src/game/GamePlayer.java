package game;

import agent.Agent;
import interfaces.GamePolicy;

/**
 * Created by benitbk on 11/04/2016.
 */
public class GamePlayer {

    private final Game game;
    private final GamePolicy gamePolicy;
    public GamePlayer(Game game, GamePolicy gamePolicy)
    {
        this.game = game;
        this.gamePolicy = gamePolicy;
    }


    public boolean canAgentImprove(Agent agent)
    {
        return false;
    }


    public void start()
    {
        Agent agent = gamePolicy.agentPolicy.getNextAgent();
        while(agent != null)
        {
            gamePolicy.routingPolicy.improveAgentPath(agent);
            agent = gamePolicy.agentPolicy.getNextAgent();
        }

    }

}

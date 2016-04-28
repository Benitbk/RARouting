package game;

import agent.Agent;
import agent.AgentRoute;
import agent.AgentRoutingState;
import interfaces.GamePolicy;

/**
 * Created by benitbk on 11/04/2016.
 */
public class GamePlayer {

    private final Game game;
    private final GamePolicy gamePolicy;
    private final AgentRoutingState routingState;
    public GamePlayer(Game game, GamePolicy gamePolicy)
    {
        this.game = game;
        this.gamePolicy = gamePolicy;
        this.routingState = new AgentRoutingState();
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
            AgentRoute newRoute = gamePolicy.routingPolicy.getAgentImprovedRoute(agent);
            this.routingState.UpdateAgentRouting(agent, newRoute);
            agent = gamePolicy.agentPolicy.getNextAgent();
        }

    }

}

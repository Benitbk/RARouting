package seriesParallelGraph.game.results;

import seriesParallelGraph.agent.Agent;
import seriesParallelGraph.game.Game;
import seriesParallelGraph.graph.Route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by benitbk on 06/06/2016.
 */
public class GameResult {
	public final Game game;
	public List<PolicyResult> policyResults = new ArrayList<>();

	public GameResult(Game game) {
		this.game = game;
	}
}

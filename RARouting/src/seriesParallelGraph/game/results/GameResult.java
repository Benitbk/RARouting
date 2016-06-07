package seriesParallelGraph.game.results;

import seriesParallelGraph.game.Game;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by benitbk on 06/06/2016.
 */
public class GameResult {
	public final Game game;
	public List<PolicyResult> policyResults = new ArrayList<>();

	public GameResult(Game game) {
		this.game = (Game) DeepCopy.copy(game);
	}
}

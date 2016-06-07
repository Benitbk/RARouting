package seriesParallelGraph.game.results;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benitbk on 06/06/2016.
 */
public class PolicyResult {
    public final String policyName;
    public List<AgentStep> steps;

    public PolicyResult(String policyName) {
        this.policyName = policyName;
        steps = new ArrayList<>();
    }
}

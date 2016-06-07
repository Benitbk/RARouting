package seriesParallelGraph.game.gui;

import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import seriesParallelGraph.agent.Agent;
import seriesParallelGraph.game.results.GameResult;
import seriesParallelGraph.graph.edge.Edge;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	GameResult gameResult;
	private SPGraphPanel graphPanel;

	public GameFrame(GameResult gameResult) throws HeadlessException {
		super();
		this.gameResult = gameResult;
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 500);

		graphPanel = new SPGraphPanel(gameResult.game.graph);
		add(graphPanel);
		add(new ResultsPanel(gameResult, this));

		// pack();
		setVisible(true);
		repaint();
	}

	public void agentSelectionChanged(int[] indices) {
		List<Edge> edgesInRoutes = new ArrayList<Edge>();
		for (int i : indices) {
			Agent agent = gameResult.game.agents.get(i);
			edgesInRoutes.addAll(agent.getRoute().edges);
		}
		graphPanel.setBoldEdges(edgesInRoutes);
	}

}

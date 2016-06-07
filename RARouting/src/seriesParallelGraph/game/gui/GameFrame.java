package seriesParallelGraph.game.gui;

import java.awt.HeadlessException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import seriesParallelGraph.game.results.GameResult;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	GameResult gameResult;

	public GameFrame(GameResult gameResult) throws HeadlessException {
		super();
		this.gameResult = gameResult;
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 500);
		
		add(new SPGraphPanel(gameResult.game.graph));
		
		add(new ResultsPanel(gameResult));
		
//		pack();
		setVisible(true);
		repaint();
	}

}

package seriesParallelGraph.game.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import seriesParallelGraph.agent.Agent;
import seriesParallelGraph.game.results.GameResult;
import seriesParallelGraph.game.results.PolicyResult;

@SuppressWarnings("serial")
public class ResultsPanel extends JPanel {

	GameResult gameResult;

	public ResultsPanel(GameResult gameResult) {
		super();
		this.gameResult = gameResult;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		JPanel policySelectPanel = new JPanel();
		policySelectPanel.setLayout(new BoxLayout(policySelectPanel,
				BoxLayout.Y_AXIS));
		// System.out.println(policySelectPanel.getLayout());
		policySelectPanel.setBorder(BorderFactory
				.createTitledBorder("Policies"));
		ButtonGroup group = new ButtonGroup();
		for (PolicyResult policyResult : gameResult.policyResults) {
			JRadioButton radioButton = new JRadioButton(policyResult.policyName);
			radioButton.setSelected(true);

			group.add(radioButton);
			policySelectPanel.add(radioButton);
		}

		add(policySelectPanel);
	}

	// @Override
	// public void paint(Graphics g) {
	// super.paint(g);
	// g.drawString("aaaaaaa", 10, 10);
	//
	// setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
	// }
}

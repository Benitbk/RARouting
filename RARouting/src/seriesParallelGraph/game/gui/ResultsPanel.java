package seriesParallelGraph.game.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import seriesParallelGraph.agent.Agent;
import seriesParallelGraph.game.results.GameResult;
import seriesParallelGraph.game.results.PolicyResult;
import seriesParallelGraph.graph.edge.Edge;

@SuppressWarnings("serial")
public class ResultsPanel extends JPanel {

	GameResult gameResult;

	private GameFrame frame;

	public ResultsPanel(GameResult gameResult,GameFrame frame) {
		super();
		this.gameResult = gameResult;
		this.frame = frame;
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		addPoliciesPanel(gameResult);

		addAgentsList(gameResult);
	}

	public void addAgentsList(GameResult gameResult) {
		List<String> agentsStrList = new ArrayList<String>();
		for (Agent agent : gameResult.game.agents) {
			String agentStr = "Agent " + agent.id + " \t"
					+ agent.getRoute().cost();
			agentsStrList.add(agentStr);
		}
		JList<Object> agentsList = new JList<Object>();
		agentsList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					frame.agentSelectionChanged(agentsList.getSelectedIndices());
				}
			}
		});
		agentsList.setListData(agentsStrList.toArray());

		JScrollPane agentsScrollPane = new JScrollPane(agentsList);
		add(agentsScrollPane);
	}

	PolicyResult currPolicy;

	public void addPoliciesPanel(GameResult gameResult) {

		ActionListener policyChangeListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currPolicy = gameResult.policyResults.get(Integer.parseInt(e
						.getActionCommand()));
			}
		};

		JPanel policySelectPanel = new JPanel();
		policySelectPanel.setLayout(new BoxLayout(policySelectPanel,
				BoxLayout.Y_AXIS));
		// System.out.println(policySelectPanel.getLayout());
		policySelectPanel.setBorder(BorderFactory
				.createTitledBorder("Policies"));
		ButtonGroup group = new ButtonGroup();
		for (int i = 0; i < gameResult.policyResults.size(); i++) {
			PolicyResult policyResult = gameResult.policyResults.get(i);
			JRadioButton radioButton = new JRadioButton(policyResult.policyName);
			radioButton.setActionCommand("" + i);
			radioButton.addActionListener(policyChangeListener);
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

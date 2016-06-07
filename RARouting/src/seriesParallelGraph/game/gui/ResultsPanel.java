package seriesParallelGraph.game.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import seriesParallelGraph.agent.Agent;
import seriesParallelGraph.game.results.AgentStep;
import seriesParallelGraph.game.results.GameResult;
import seriesParallelGraph.game.results.PolicyResult;
import seriesParallelGraph.graph.Route;

@SuppressWarnings("serial")
public class ResultsPanel extends JPanel {

	GameResult gameResult;

	private GameFrame frame;

	private JButton nextButton;

	private JButton prevButton;

	public ResultsPanel(GameResult gameResult, GameFrame frame) {
		super();
		this.gameResult = gameResult;
		this.frame = frame;

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		addPoliciesPanel(gameResult);

		addAgentsList(gameResult);

		prevButton = new JButton("<");
		prevButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prevIteration();
			}
		});
		add(prevButton);

		nextButton = new JButton(">");
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nextIteration();
			}
		});
		add(nextButton);
		handleButtons();

		setCurrPolicy(0);

	}

	JList<Object> agentsList = new JList<Object>();

	public void addAgentsList(GameResult gameResult) {
		agentsList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					frame.agentSelectionChanged(agentsList.getSelectedIndices());
				}
			}
		});

		JScrollPane agentsScrollPane = new JScrollPane(agentsList);
		add(agentsScrollPane);
	}

	public void refreshAgentsList() {
		int[] selected = agentsList.getSelectedIndices();

		AgentStep currAgentStep = getCurrAgentStep();
		Agent lasPlayedtAgent = null;
		if (currAgentStep != null) {
			lasPlayedtAgent = currAgentStep.agent;
		}

		List<String> agentsStrList = new ArrayList<String>();
		for (Agent agent : gameResult.game.agents) {
			String agentStr = "";
			if (agent == lasPlayedtAgent) {
				agentStr += "*";
			}
			agentStr += "Agent " + agent.id + " \t" + agent.getRoute().cost();

			agentsStrList.add(agentStr);
		}
		agentsList.setListData(agentsStrList.toArray());

		agentsList.setSelectedIndices(selected);
	}

	public AgentStep getCurrAgentStep() {
		if (iteration < 0)
			return null;
		return currPolicy.steps.get(iteration);
	}

	PolicyResult currPolicy;
	int iteration = 0;

	public void addPoliciesPanel(GameResult gameResult) {

		ActionListener policyChangeListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setCurrPolicy(Integer.parseInt(e.getActionCommand()));
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

	public PolicyResult getCurrPolicy() {
		return currPolicy;
	}

	public void setCurrPolicy(int i) {
		this.currPolicy = gameResult.policyResults.get(i);

		for (Entry<Agent, Route> entry : gameResult.game.initialRoutes
				.entrySet()) {
			Agent agent = entry.getKey();
			Route route = entry.getValue();
			agent.setRoute(route);
		}
		setIteration(-1);
		refreshAgentsList();
	}

	public int getIteration() {
		return iteration;
	}

	public void nextIteration() {
		setIteration(this.iteration + 1);
		AgentStep agentStep = getCurrAgentStep();
		agentStep.agent.setRoute(agentStep.newRoute);

		refreshAgentsList();
	}

	public void prevIteration() {
		AgentStep agentStep = getCurrAgentStep();
		agentStep.agent.setRoute(agentStep.oldRoute);
		setIteration(this.iteration - 1);

		refreshAgentsList();
	}

	public void setIteration(int iteration) {
		this.iteration = iteration;
		handleButtons();
	}

	public void handleButtons() {
		if (currPolicy == null) {
			nextButton.setEnabled(false);
			prevButton.setEnabled(false);
			return;
		}
		if (iteration >= currPolicy.steps.size() - 1)
			nextButton.setEnabled(false);
		else
			nextButton.setEnabled(true);

		if (iteration <= -1)
			prevButton.setEnabled(false);
		else
			prevButton.setEnabled(true);
	}

	// @Override
	// public void paint(Graphics g) {
	// super.paint(g);
	// g.drawString("aaaaaaa", 10, 10);
	//
	// setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
	// }
}

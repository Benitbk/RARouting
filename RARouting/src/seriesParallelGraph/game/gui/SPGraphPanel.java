package seriesParallelGraph.game.gui;

import seriesParallelGraph.graph.edge.Edge;
import seriesParallelGraph.graph.SPGraph;
import seriesParallelGraph.graph.Vertex;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

// FIXME fix bug where an edge from g.s to g.t is drawn even when it is not
// part of g (it is connected to g in parallel)
@SuppressWarnings("serial")
public class SPGraphPanel extends JPanel implements MouseWheelListener,
		MouseInputListener, ComponentListener {

	SPGraph graph;
	Map<Vertex, Point> vertexLocations;

	Point offset = new Point(0.1f, 0.1f);
	Point grid = new Point(40, 40);

	Point scale = new Point(2, 1.2f);

	boolean showParallelEdges = true;

	public SPGraphPanel(SPGraph graph) {
		super();
		this.graph = graph;

		setBorder(BorderFactory.createLineBorder(Color.black, 5));

		vertexLocations = graph.locate(graph.getLength(), graph.getWidth());

		Set<Entry<Vertex, Point>> vertsSet = vertexLocations.entrySet();
		for (Entry<Vertex, Point> entry : vertsSet) {
			// System.out.println(entry);
			// Vertex v = entry.getKey();
			Point p = entry.getValue();
			p.x *= scale.x;
			p.y *= scale.y;
		}

		this.addMouseWheelListener(this);
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		this.addComponentListener(this);

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		// grid
		g.setColor(Color.ORANGE);
		for (int i = 0; i < 200; i++) {
			g.drawLine((int) (grid.x * i), 0, (int) (grid.x * i), 2000);
			g.drawLine(0, (int) (grid.y * i), 2000, (int) (grid.y * i));
		}

		drawGraph(g);

	}

	BasicStroke boldStroke = new BasicStroke(5);
	BasicStroke normalStroke = new BasicStroke(1);

	private void drawGraph(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Set<Entry<Vertex, Point>> vertsSet = vertexLocations.entrySet();
		for (Entry<Vertex, Point> entry : vertsSet) {

			Vertex v = entry.getKey();
			Point p = entry.getValue();
			Point vertexLoc = new Point((p.x + offset.x) * grid.x,
					(p.y + offset.y) * grid.y);
			// draw vertex
			g.setColor(Color.BLACK);
			g.fillOval((int) vertexLoc.x, (int) vertexLoc.y, (int) grid.x,
					(int) grid.y);

			Point vertexCenter = new Point(vertexLoc.x + grid.x / 2,
					vertexLoc.y + grid.y / 2);

			List<Edge> edges = v.leaving;

			// draw edges leaving v
			Color randColor = new Color(new Random().nextInt(0xFFFFFF) / 2);
			// Color randColor = Color.DARK_GRAY;

			// calculate the lines destinations
			Map<Edge, Point> destinations = new HashMap<Edge, Point>();
			for (Edge e : edges) {
				Point v2Point = vertexLocations.get(e.t);
				if (v2Point == null)
					continue;
				Point v2Loc = new Point((v2Point.x + offset.x) * grid.x,
						(v2Point.y + offset.y) * grid.y);
				v2Loc.x += grid.x / 2;
				v2Loc.y += grid.y / 2;
				destinations.put(e, new Point(v2Loc.x, v2Loc.y));
			}
			// sort the lines by angel from v
			Collections.sort(edges, new Comparator<Edge>() {
				@Override
				public int compare(Edge e1, Edge e2) {
					Point p1 = destinations.get(e1);
					Point p2 = destinations.get(e2);
					Double p1Direction = Math.atan((p1.y - vertexCenter.y)
							/ (p1.x - vertexCenter.x));
					Double p2Direction = Math.atan((p2.y - vertexCenter.y)
							/ (p2.x - vertexCenter.x));
					return p1Direction.compareTo(p2Direction);
				}
			});

			// draw the lines

			Point lineSource;
			if (showParallelEdges)
				lineSource = new Point(vertexLoc.x + grid.x / 2, vertexLoc.y
						+ grid.y / (destinations.size() + 1));
			else
				lineSource = new Point(vertexLoc.x + grid.x / 2, vertexLoc.y
						+ grid.y / 2);

			int i = 0;
			for (Edge e : edges) {
				Point dest = destinations.get(e);
				if (boldEdges.contains(e)) {
					g2.setStroke(boldStroke);
					g.setColor(Color.ORANGE);
				} else {
					g2.setStroke(normalStroke);
					g.setColor(randColor);
				}

				g.drawLine((int) lineSource.x, (int) lineSource.y,
						(int) dest.x, (int) dest.y);

				// draw cost
				g.setColor(Color.BLACK);
				String s = e.getLabel();
				if (i == 0) {
					g.drawString(s,
							(int) (0.75 * lineSource.x + 0.25 * dest.x),
							(int) (0.75 * lineSource.y + 0.25 * dest.y));
				} else {
					g.drawString(s, (int) (0.7 * lineSource.x + 0.3 * dest.x),
							(int) (0.7 * lineSource.y + 0.3 * dest.y));
				}

				if (showParallelEdges)
					lineSource.y += grid.y / (destinations.size() + 1);

				i = 1 - i;
			}
		}

		// draw IDs on vertices
		g.setColor(new Color(240, 240, 240));
		g.setFont(new Font("Arial", Font.BOLD, (int) grid.y / 2));
		for (Entry<Vertex, Point> entry : vertsSet) {
			Vertex v = entry.getKey();
			Point p = entry.getValue();

			Point vertexLoc = new Point((p.x + offset.x) * grid.x,
					(p.y + offset.y) * grid.y);
			g.drawString("" + v.id, (int) (vertexLoc.x + grid.x / 10),
					(int) (vertexLoc.y + 2 * grid.y / 3));
		}
	}

	@Override
	public void componentResized(ComponentEvent e) {
		float scaleBy = 0;
		float graphAspectRatio = graph.getLength() * scale.x / graph.getWidth()
				/ scale.y;
		if (graphAspectRatio > 1) {
			// fit to panel width
			scaleBy = getSize().width
					/ ((graph.getLength() * scale.x + offset.x + 1) * grid.x);
		} else {// fit to panel height
			scaleBy = getSize().height
					/ ((graph.getWidth() * scale.y + offset.y + 1) * grid.y);
		}
		grid.x = grid.x * scaleBy;
		grid.y = grid.y * scaleBy;

		Dimension dim = new Dimension(getSize().width, (int) ((graph.getWidth()
				+ offset.y + 1)
				* scale.y * grid.y));

		 setMinimumSize(dim);
		// setMaximumSize(dim);
		// this.setSize(dim);
		this.setPreferredSize(dim);

		repaint();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		grid.x -= (grid.x * 0.1 * e.getWheelRotation());
		grid.y -= (grid.y * 0.1 * e.getWheelRotation());
		repaint();
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	private java.awt.Point oldLocation = null;

	@Override
	public void mouseDragged(MouseEvent e) {
		if (oldLocation != null) {
			offset.x += (e.getX() - oldLocation.x) / grid.x;
			offset.y += (e.getY() - oldLocation.y) / grid.y;
		}

		oldLocation = e.getPoint();
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		oldLocation = null;

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	List<Edge> boldEdges = new ArrayList<Edge>();

	public void setBoldEdges(List<Edge> boldEdges) {
		this.boldEdges = boldEdges;
		repaint();
	}
}

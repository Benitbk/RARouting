package seriesParallelGraph.game.gui;

public class Point {
	public float x;
	public float y;

	public Point(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Point() {
		super();
	}

	@Override
	public String toString() {
		return x + ", " + y;
	}

}

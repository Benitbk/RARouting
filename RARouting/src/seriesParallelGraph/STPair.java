package seriesParallelGraph;

public class STPair {
	public Vertex s;
	public Vertex t;

	public STPair(Vertex s, Vertex t) {
		super();
		this.s = s;
		this.t = t;
	}

	@Override
	public String toString() {
		return s.toString() + ", " + t.toString();
	}

}

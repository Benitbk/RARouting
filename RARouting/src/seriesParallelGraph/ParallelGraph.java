package seriesParallelGraph;

import java.security.InvalidParameterException;

public class ParallelGraph extends SPGraph {

	SPGraph g1;
	SPGraph g2;
	
	@Override
	public Path solve() {
		Path path1 = g1.solve();
		Path path2 = g2.solve();
		
		if(path1.cost() < path2.cost())
			return path1;
		return path2;
	}

	@Override
	public SubSPGraph GenerateSubGraphFromVerticesRecursive(Vertex s, Vertex t) {
		SubSPGraph subG1Graph = g1.GenerateSubGraphFromVerticesRecursive(s, t);
        SubSPGraph subG2Graph = g2.GenerateSubGraphFromVerticesRecursive(s, t);

        if(subG1Graph.sExists && subG1Graph.tExists)
		{
			if(subG2Graph.sExists && subG2Graph.tExists)
			{
				return new SubSPGraph(this, true, true);
			}

			return subG1Graph;

		}
		if(subG2Graph.sExists && subG2Graph.tExists)
		{
			return subG2Graph;
		}



		if(subG1Graph.sExists && subG2Graph.sExists)
		{
			return new SubSPGraph(this, true, false);
		}

		if(subG1Graph.tExists && subG2Graph.tExists)
		{
			return new SubSPGraph(this, false, true);
		}

        if((subG1Graph.sExists && subG2Graph.tExists) || (subG1Graph.tExists && subG2Graph.sExists))
            throw new InvalidParameterException("s and t are in parallel graphs");

		if(subG1Graph.sExists || subG1Graph.tExists)
        {
            return subG1Graph;
        }

        if(subG2Graph.sExists || subG2Graph.tExists)
        {
            return subG2Graph;
        }
        return new SubSPGraph(this, false, false);

	}


}

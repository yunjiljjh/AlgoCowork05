package MinDis;

public class Distance {
	protected Node[] node;
	protected int numOfNodes;
	protected int pathLength;
	
	protected double distance(int node1ID, int node2ID){
		return Math.sqrt((node[node1ID-1].x-node[node2ID-1].x)*(node[node1ID-1].x-node[node2ID-1].x)
				+ (node[node1ID-1].y-node[node2ID-1].y)*(node[node1ID-1].y-node[node2ID-1].y));
	}

}

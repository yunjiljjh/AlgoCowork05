package MinDis;

//Parent class for different solving methods of TSP. 
//Stores number of TSP nodes, x and y coordinates of the nodes, length of minimum path, 
// and give method distance(node1ID, node2ID) as a tool to calculate distance between two nodes node1 and node2. 
public class Distance {
	protected Node[] node; // node array with x and y coordinates. nodeID = index+1
	protected int numOfNodes; // number of nodes
	protected double pathLength;
	
	//return 
	protected double distance(int node1ID, int node2ID){
		return Math.sqrt((node[node1ID-1].x-node[node2ID-1].x)*(node[node1ID-1].x-node[node2ID-1].x)
				+ (node[node1ID-1].y-node[node2ID-1].y)*(node[node1ID-1].y-node[node2ID-1].y));
	}

}

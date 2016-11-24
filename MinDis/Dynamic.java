//later
package MinDis;

//import java.util.ArrayList;

public class Dynamic extends Distance {
//	Node[] node;
//	int numOfNodes;
// 	int pathLength
//	double distance(int node1ID, int node2ID)
	
//	int[][] pathNodeIDs;
	double[][] dpArray;
//	ArrayList S;
	
	Dynamic(int numOfNode, Node[] node){
		this.node = node;
		this.numOfNodes = numOfNode;
		
//		Double.POSITIVE_INFINITY
		
//		pathNodes = new int[2][numOfNodeIDs];//?
		dpArray = new double[2][numOfNodes];
		
		for(int i=0; i<numOfNodes; i++){
			dpArray[0][i] = distance(1,i);
		}		
		
		for(int i=0; i<numOfNodes; i++){
			dpArray[1][i] = Double.POSITIVE_INFINITY;
		}
		
	}
	
	//returns min value of dpArray[arrayNo]
	private double min(int arrayNo){
		double min = Double.POSITIVE_INFINITY;
		for(int i=0; i<numOfNodes; i++){
			min = Math.min(min, dpArray[arrayNo][i]);
		}
		return min;
	}
}

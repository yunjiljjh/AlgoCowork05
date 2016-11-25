package MinDis;

import java.util.LinkedList;
import java.util.Stack;

//DFS with pruning
//prune if greater than current minimum. 
public class Backtracking extends Distance {
	double minDist; // minimum distance
	Stack<Integer> pathNodeIDs; // node order of path
	String result; // result string
	
	Backtracking(int numOfNode, Node[] node){
		this.numOfNodes = numOfNode; // number of nodes
		this.node = node; // node array of 
		minDist = Double.POSITIVE_INFINITY; // initialize minDist to infinity
		pathNodeIDs = new Stack<Integer>(); 
		pathNodeIDs.push(1); //starts from node 1
		
		LinkedList<Integer> nodeIDsToVisit = new LinkedList<Integer>(); // list of node IDs to visit
		for(int i=2; i<=numOfNodes; i++){ // initialize nodeIDsToVisit
			nodeIDsToVisit.add(i); 
		}		
		//find the minimum cost and the path
		backtrack(1, nodeIDsToVisit, (double)0, minDist);
		
//test		
		System.out.println(result);
	}
	//recursive function to find the minimum cost and the path
	//from current node, see distance to a notVisited node, 
	//calculate length of travel until the current node, 
	//compare to current minDist if the length of path is same to numOfNodes and set minDist.
	void backtrack(int currentNodeID, LinkedList<Integer> notVisited, double dist, double minDist){
//		System.out.println("===============" + currentNodeID);
		LinkedList<Integer> toVisit = new LinkedList<Integer>(); // to send to next method nodes to visit as parameter 			
		this.minDist = minDist;
		for(int i:notVisited){ // if node i has not been visited
//			System.out.println(" loop i: " + i);
			toVisit = (LinkedList) notVisited.clone();	// to leave notVisited unchanged.		
			double newDist = dist + distance(currentNodeID, i);
			if (newDist >= this.minDist){ //Length of path + node i is greater than current minDist
				//prune since path + node i cannot be the answer
			}else {
				pathNodeIDs.push(i); // add node i to path
				toVisit.remove(toVisit.indexOf(i)); // remove node i from toVisit list
				if(pathNodeIDs.size() == numOfNodes){ // if the path visits all nodes of graph
					newDist = newDist + distance(i, 1); // salesman have to go home
					if(newDist < this.minDist){ // if this path is better, update result string to store the answer
						this.minDist = newDist; 
						result = toString(pathNodeIDs, this.minDist);
					}
					pathNodeIDs.pop(); // remove node i from the path to check other orders
				} else{
//					System.out.println("backtrack(" + i + ", " + newDist + ", " + this.minDist + ")");
					backtrack(i, toVisit, newDist, this.minDist); // to examine next node to visit
					pathNodeIDs.pop(); // remove node from path to check other orders
				}
			}
		}
//		System.out.println("----------------" + currentNodeID);
	}
	//generate result string from path node IDs and minimum travel length
	String toString(Stack<Integer> s, double minDist){
		String result ="";
		pathLength = minDist;
		for(int i=0; i<numOfNodes; i++){
			result = result + " " + s.get(i);
		}
//		long minimumDist = Math.round(minDist);
//		result = result + " " + "1" + "\n" + minimumDist;
		result = result + " " + "1" + "\n" + minDist;
		return result;
	}
}

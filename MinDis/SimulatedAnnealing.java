package MinDis;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class SimulatedAnnealing extends Distance {
	public String result="";
	private double cost;
	private double coolingRate = 0.9;
	LinkedList<Integer> currentCycle;
	LinkedList<Integer> neighbor;
	
	
	SimulatedAnnealing(int numOfNode, Node[] node){
		this.numOfNodes = numOfNode;
		this.node = node;
		double temperature = 100000;
		//pick a random cycle
		currentCycle = cycle();
		cost = travellen(currentCycle);
		System.out.println(toString(currentCycle));
		
		while(temperature > 1){
			double costDiff = randomNeighborCostDiff(currentCycle);
			
		//to test
//			costDiff = travellen(currentCycle) - travellen(neighbor);
			
			if(costDiff < 0){
				currentCycle = neighbor;
				cost = cost + costDiff;
			}else if (acceptProb(costDiff, temperature) > Math.random()){
				currentCycle = neighbor;
				cost = cost + costDiff;
			}
			System.out.println(toString(currentCycle));
			
			temperature *= coolingRate;
		}
		result = toString(currentCycle);
		System.out.println("result: " + result);		
	}
	private String toString(LinkedList<Integer> travel){
		String result = "";
		for (int i=0; i<numOfNodes; i++){
			result = result + travel.get(i) + " ";
//			result = result + travel.removeFirst() + " ";
		}
		result = result + "\n" + cost;
		return result;
	}
	
	private double acceptProb(double costDiff, double temperature){
		double test = Math.exp(-(costDiff/temperature));
		System.out.println("exp: " + test);
		return Math.exp(costDiff/temperature);
	}
	private LinkedList<Integer> cycle(){
		LinkedList<Integer> cycle = new LinkedList<Integer>();
		for(int i=2; i<=numOfNodes; i++){
			cycle.add(i);
		}
		Collections.shuffle(cycle);
		cycle.add(0, 1);
		cycle.add(1);
		return cycle;
	}

	private double randomNeighborCostDiff(LinkedList<Integer> cycle){
		neighbor = (LinkedList) cycle.clone();
		double costDiff;
		
		int a, b;
		Random r = new Random();
		Integer temp;
		//pick two random nodeIDs to insert at/swap/reverse
		a = r.nextInt(numOfNodes);
		b = r.nextInt(numOfNodes);
		while (a==b){
			b = r.nextInt(numOfNodes);
		}
		int nodeA, nodeB, nodeAprev, nodeAnext, nodeBprev, nodeBnext;
		nodeA = cycle.get(a);
		nodeB = cycle.get(b);		
		nodeAprev = cycle.get(Math.floorMod(a-1, numOfNodes));
		nodeAnext = cycle.get(Math.floorMod(a+1, numOfNodes));
		nodeBprev = cycle.get(Math.floorMod(b-1, numOfNodes));
		nodeBnext = cycle.get(Math.floorMod(b+1, numOfNodes));
		
		int c = r.nextInt(3);
		if (c == 0){
		//insertion - insert a before b
			temp = neighbor.remove(b);
			neighbor.add(a, temp);
			costDiff = distance(nodeB, nodeA) + distance(nodeA, nodeBnext) + distance(nodeAprev, nodeAnext) 
					- distance(nodeB, nodeBnext) - distance(nodeA, nodeAprev) - distance(nodeA, nodeAnext);
		}else if(c == 1){
		//swap
			Collections.swap(neighbor, a, b);
			costDiff = distance(nodeAprev, nodeB) + distance(nodeAnext, nodeB) + distance(nodeA, nodeBprev) + distance(nodeA, nodeBnext)
					- distance(nodeAprev, nodeA) - distance(nodeAnext, nodeA) - distance(nodeB, nodeBprev) - distance(nodeB, nodeBnext);
		}else{
		//reverse
			neighbor.clear();
			if (a > b){int t = a; a = b; b=t;}
			for(int i=0; i<a; i++){
				neighbor.add(cycle.get(i));
			} 
			for(int i=a; i<b; i++){
				neighbor.add(cycle.get(a+b-i-1));
			}
			for(int i=b; i<numOfNodes; i++){
				neighbor.add(i);
			}
			costDiff = distance(nodeAprev, nodeBprev) + distance(nodeA, nodeB) - distance(nodeAprev, nodeA) - distance(nodeBprev, nodeB);
		}
		System.out.println("a: " + nodeA + " b: " + nodeB + " c: " + c + "        costDiff: " + costDiff);
		return costDiff;
	}
}

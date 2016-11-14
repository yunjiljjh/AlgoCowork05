package MinDis;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputReader reader=new InputReader("C:\\hw5\\131tsp.log");
		
		Node[] node = reader.node;
		int numOfNode = reader.numOfNode;
		
		Dynamic dy = new Dynamic(numOfNode,node);
		//Backtracking bt = new Backtracking(numOfNode,node);
		//BranchAndBound bb= new BranchAndBound(numOfNode,node);
		//Genetic gn = new Genetic(numOfNode,node);
		//SimulatedAnnealing sa = new SimulatedAnnealing(numOfNode,node);
	}

}

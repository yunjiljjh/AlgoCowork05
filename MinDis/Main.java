//hw5? hw56? CSI3108PGM_5tsp_2?
package MinDis;

public class Main {

	public static void main(String[] args) {
		

		InputReader reader=new InputReader("C:\\hw5\\131tsp.log");
//		String path = "C:\\Users\\p34g\\Documents\\GitHub\\AlgoCowork05";
//		InputReader reader = new InputReader(path + "\\CSI3108PGM_5tsp_2\\13tsp.log");
		
		Node[] node = reader.node;
		int numOfNode = reader.numOfNode;

		long BEGIN, END; // for calculating run time
		BEGIN = System.currentTimeMillis();
		
		Genetic ga = new Genetic(numOfNode,node);
      
		END = System.currentTimeMillis();
        System.out.println("Time: " + (END - BEGIN) / 1000.0 + " sec.");
		
		
//		Dynamic dp = new Dynamic(numOfNode,node);
		//Backtracking bt = new Backtracking(numOfNode,node);
		//BranchAndBound bb= new BranchAndBound(numOfNode,node);
		//Genetic ga = new Genetic(numOfNode,node);
		//SimulatedAnnealing sa = new SimulatedAnnealing(numOfNode,node);

//		OutputWriter wr = new OutpurWriter(dp.result, "C:\\hw56\\id.txt");
//		OutputWriter wr = new OutputWriter("test", path + "\\Output\\test.txt");
	}
}

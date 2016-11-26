//hw5? hw56? CSI3108PGM_5tsp_2?
package MinDis;

public class Main {

	public static void main(String[] args) {
		

		InputReader reader=new InputReader("C:\\hw5\\131tsp.log");
		/**
		String path = "C:\\Users\\p34g\\Documents\\GitHub\\AlgoCowork05";
		int sw = 3;
		String fileName = "13tsp.log";
	
		
		switch(sw){
			case 1: fileName = "13tsp.log"; break;
			case 2: fileName = "16tsp.log"; break;
			case 3: fileName = "38tsp.log"; break;
			case 4: fileName = "51tsp.log"; break;
			case 5: fileName = "70tsp.log"; break;
			case 6: fileName = "131tsp.log"; break;
			case 7: fileName = "2924tsp.log"; break;
			case 8: fileName = "10639tsp.log"; break;
			default: fileName = "custom_test_5.log"; break;
		}
		InputReader reader = new InputReader(path + "\\CSI3108PGM_5tsp_2\\" + fileName);
//		InputReader reader = new InputReader(path + "\\CSI3108PGM_5tsp_2\\13tsp.log");
		
		**/
		
		Node[] node = reader.node;
		int numOfNode = reader.numOfNode;

		long BEGIN, END; // for calculating run time
		BEGIN = System.currentTimeMillis();
		
		//Dynamic dp = new Dynamic(numOfNode,node);
		//Backtracking bt = new Backtracking(numOfNode,node);
		//BranchAndBound bb= new BranchAndBound(numOfNode,node);
		Genetic ga = new Genetic(numOfNode,node);
		//SimulatedAnnealing sa = new SimulatedAnnealing(numOfNode,node);

		END = System.currentTimeMillis();
		String time = "Time: " + (END - BEGIN) / 1000.0 + " sec.";
        System.out.println("Time: " + (END - BEGIN) / 1000.0 + " sec.");
 
 //       OutputWriter test = new OutputWriter(bt.result + "\n" + time, path + "\\Output\\test\\" + fileName);

//		OutputWriter wr = new OutpurWriter(dp.result, "C:\\hw56\\id.txt");
//		OutputWriter wr = new OutputWriter(dp.result, path + "\\Output\\test.txt");
	}
}

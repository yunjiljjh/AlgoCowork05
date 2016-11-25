package MinDis;

public class BranchAndBound {
	private double[][] nodeMatrix;
	private double[] visited;
	private double cost;
	private double[][] a;
	private int n;
			
	public BranchAndBound() {
	}

	public BranchAndBound(int numOfNode, Node[] node) {
		visited = new double[10];
		cost = 0;
		n=0;
		a= new double[10][10];
		nodeMatrix = new double[numOfNode][numOfNode];
		for (int i = 0; i < numOfNode; i++) {
			for (int j = 0; j < numOfNode; j++) {
				if (i == j) {
					nodeMatrix[i][j] = 0;
				} else {
					nodeMatrix[i][j] = node[i].distanceTo(node[j]);
					nodeMatrix[j][i] = nodeMatrix[i][j];
				}
			}
		}
	
		
		System.out.println();
		System.out.println("The Path is:");
		System.out.println();
		mincost(0);
		put();
	}

	// pasted

	void mincost(int city){
			int i,ncity;
			visited[city]=1;
			System.out.println(city+1+"->");
			ncity=least(city);
			
			if(ncity==999){
					ncity=0;
					System.out.println(ncity+1+"->");
					cost+=a[city][ncity];
					return;
			}
			mincost(ncity);
			}

	int least(int c) {
		int i, nc = 999;
		double min = 999;
		double kmin=999;
		for (i = 0; i < n; i++) {
			if ((a[c][i] != 0) && (visited[i] == 0))
				if (a[c][i] < min) {
					min = a[i][0] + a[c][i];
					kmin = a[c][i];
					nc = i;
				}
		}
		if (min != 999)
			cost += kmin;
		return nc;
	}

	void put()
	{
		System.out.println();
		System.out.println("Minimum cost:" + cost);
	}


}

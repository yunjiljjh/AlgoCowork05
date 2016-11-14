package MinDis;

public class Node {
	public int nodeNum;
	public double x;
	public double y;
	public Node(){
		nodeNum =0;
		x=-1;
		y=-1;
	
	}
	public Node(int nodeNum, double x, double y){
		this.nodeNum = nodeNum;
		this.x =x;
		this.y =y;
	}
	public void set(int nodeNum, double x, double y){
		this.nodeNum = nodeNum;
		this.x =x;
		this.y =y;
	}
}

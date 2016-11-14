package MinDis;

public class Node {
	//public int nodeNum; <- used index of Node array instead. nodeNum = index + 1
	public double x;
	public double y;
	
	public Node(){
		x=-1;
		y=-1;
	}
	public Node(double x, double y){
		this.x =x;
		this.y =y;
	}
	public void set(double x, double y){
		this.x =x;
		this.y =y;
	}
}

package MinDis;

import java.math.*;

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
	
	 public double distanceTo(Node given){
	        double xDistance = Math.abs(this.x - given.x);
	        double yDistance = Math.abs(this.y - given.y);
	        double distance = Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );
	        return  distance;
	    }
/*	private int nodeID;
	public Node(int nodeID, double x, double y){
		this.nodeID = nodeID;
		this.x = x;
		this.y = y;
	}
	public int getID(){
		return nodeID;
	} */
/*	public void printNode(){
		System.out.println(x + " " + y);
	}*/
}

package MinDis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InputReader {
	private FileReader fr;
	private BufferedReader br;
	
	public int numOfNode;
	public Node node[];

	public InputReader(String path){
		numOfNode = 0;
		read(path);
	}

	private void read(String path){
		try{
			fr=new FileReader(path);
			br=new BufferedReader(fr);
			
			String s = null;
			int nodeNum = 0;
			double x = -1;
			double y = -1;
			
			// reads the first line
			numOfNode = Integer.parseInt(br.readLine());
			node = new Node[numOfNode];
					

			for(int i=0 ; i <numOfNode ; i++){
				s = br.readLine();
				String[] line = s.split(" ");
				nodeNum  = Integer.parseInt(line[0]);
				x = Double.parseDouble(line[1]);
				y = Double.parseDouble(line[2]);
				node[i].set(nodeNum, x, y);		
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(br != null){
				try{br.close();}
				catch(IOException e){}
			}
			if(fr != null){
				try{fr.close();}
				catch(IOException e){}
			}
		}			
	}}

package MinDis;

import java.io.FileWriter;
import java.io.IOException;

public class OutputWriter {
	private FileWriter fw;

	OutputWriter(String result, String path){
		try{
			fw=new FileWriter(path,true);
	
			fw.write(result);
			fw.close();
		}catch(IOException e){}
	}
}

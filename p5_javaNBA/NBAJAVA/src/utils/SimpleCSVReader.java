package utils;


import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * Simple CSV reader
 * 
 * Shamelessly stolen from mykong
 * @author mykong https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
 *
 */
public class SimpleCSVReader{

	public ArrayList<String[]> load(String fileName) {
		
		ArrayList<String[]> lines = new ArrayList<String []>();
		String line = "";
	    String cvsSplitBy = ",";
	    
	  //new BufferedReader(new FileReader(fileName))) 
	    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8")))
{

	        while ((line = br.readLine()) != null ) {
	        	String[] data = line.split(cvsSplitBy);
	            // use comma as separator
	        	
            	lines.add(data);
	        	
	        }
	        

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	
		return lines;
	}
}
//end load

//end class



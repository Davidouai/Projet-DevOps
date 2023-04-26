package fr.uga.etu.Data_Analysis_DevOps;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Dataframe 
{
	HashMap<String, Column> data;
	HashMap<Integer, Integer> indexes;
	
	Dataframe(Object[][] t, String labels[], int indexes[]) {
		data = new HashMap<>();
		
		for(int j = 0; j < labels.length; j++) {	// A proprifier
			if (t[0][j] instanceof String) {
				ArrayList<String> c = new ArrayList<>();
				for(int i = 0; i < t.length; i++) {
					c.add((String) t[i][j]);
				}
				Column<String> column = new Column<>(c);
				data.put(labels[j], column);
			} else if (t[0][j] instanceof Integer) {
				ArrayList<Integer> c = new ArrayList<>();
				for(int i = 0; i < t.length; i++) {
					c.add((Integer) t[i][j]);
				}
				Column<Integer> column = new Column<>(c);
				data.put(labels[j], column);
			}
		}
		
		this.indexes = new HashMap<>();
		for(int i = 0; i < indexes.length; i++) {
			this.indexes.put(indexes[i], i);
		}
		
	}
	
	Dataframe(String filename, int[] indexes) {
		data = new HashMap<>();
		ArrayList<String> labels = new ArrayList<>();
		InputStream stream = Dataframe.class.getResourceAsStream(filename);
		try (BufferedReader br = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
			String line;
			ArrayList<String> array = new ArrayList<>();
			
			int i = 0;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        array = new ArrayList<String>(Arrays.asList(values));
		        
		        if (i == 0) {	// Première ligne : labels
		        	labels = array;
		        } else {	// TODO : voir si int ou string et enlever les guillemets
		        	for(int j = 0; j < array.size(); j++) {
		        		if (data.get(labels.get(j)) != null) {
		        			data.get(labels.get(j)).add(array.get(j));
		        		} else {
			        		ArrayList<String> c = new ArrayList<>();
			        		c.add(array.get(j));
							Column<String> column = new Column<>(c);
			        		data.put(labels.get(j), column);
		        		}
		        	}
				    
		        }
			    i++;
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.indexes = new HashMap<>();
		for(int i = 0; i < indexes.length; i++) {
			this.indexes.put(indexes[i], i);
		}
	}
	
	public Object get(String label, int index) {
		return data.get(label).get(indexes.get(index));
	}
	
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}

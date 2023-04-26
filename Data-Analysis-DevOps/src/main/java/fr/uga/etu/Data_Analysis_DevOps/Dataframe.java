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
import java.util.LinkedHashMap;

public class Dataframe 
{
	HashMap<String, ArrayList<Object>> data;
	LinkedHashMap<Integer, Integer> indexes;
	
	Dataframe(Object[][] t, String labels[], int indexes[]) {
		data = new HashMap<>();
		
		for(int j = 0; j < labels.length; j++) {
			ArrayList<Object> column = new ArrayList<>();
			for(int i = 0; i < t.length; i++) {
				column.add(t[i][j]);
			}
			data.put(labels[j], column);
		}
		
		this.indexes = new LinkedHashMap<>();
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
		        } else {
		        	for(int j = 0; j < array.size(); j++) {
		        		if (data.get(labels.get(j)) != null) {	// Si il y a deja des éléments dans cette colonne
		        			if (isInteger(array.get(j))) {
			        			data.get(labels.get(j)).add(Integer.parseInt(array.get(j)));
		        			} else if (isFloat(array.get(j))) {
			        			data.get(labels.get(j)).add(Float.parseFloat(array.get(j)));
		        			} else {
			        			data.get(labels.get(j)).add(array.get(j));
		        			}
		        		} else {								// Sinon, il faut créer la colonne
			        		ArrayList<Object> column = new ArrayList<>();
		        			if (isInteger(array.get(j))) {
				        		column.add(Integer.parseInt(array.get(j)));
		        			} else if (isFloat(array.get(j))) {
		        				column.add(Float.parseFloat(array.get(j)));
		        			} else {
				        		column.add(array.get(j));
		        			}
			        		data.put(labels.get(j), column);
		        		}
		        	}
				    
		        }
			    i++;
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.indexes = new LinkedHashMap<>();
		for(int i = 0; i < indexes.length; i++) {
			this.indexes.put(indexes[i], i);
		}
	}
	
	public Object[][] selectLines(int indexes[]) {
		Object[][] lines = new Object[indexes.length][indexes.length];
		
		for (int line = 0; line < indexes.length; line++) {
			int column = 0;
			for (HashMap.Entry<String, ArrayList<Object>> mapentry : data.entrySet()) {
				lines[line][column] = get(mapentry.getKey(), indexes[line]);
				column++;
			}
		}
		return lines;
	}
	
	public Object[][] selectColumns(String labels[]) {
		Object[][] columns = new Object[indexes.size()][labels.length];
		
			int line = 0;
			for (HashMap.Entry<Integer, Integer> mapentry : indexes.entrySet()) {
				int column = 0;
				for (String label : labels) {
					columns[line][column] = get(label, mapentry.getKey());
					column++;
				}
				line++;
			}
		
		return columns;
	}
	
	public Object get(String label, int index) {
		return data.get(label).get(indexes.get(index));
	}
	
	public static boolean isInteger(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	public static boolean isFloat(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Float.parseFloat(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}

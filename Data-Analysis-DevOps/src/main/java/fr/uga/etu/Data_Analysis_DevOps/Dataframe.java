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
	HashMap<String, ArrayList<Object>> data;
	HashMap<Integer, Integer> indexes;
	
	Dataframe(Object[][] t, String labels[], int indexes[]) {
		data = new HashMap<>();
		
		for(int j = 0; j < labels.length; j++) {
			ArrayList<Object> column = new ArrayList<>();
			for(int i = 0; i < t.length; i++) {
				column.add(t[i][j]);
			}
			data.put(labels[j], column);
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
		
		this.indexes = new HashMap<>();
		for(int i = 0; i < indexes.length; i++) {
			this.indexes.put(indexes[i], i);
		}
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
	
	public void printAll() {
		ArrayList<String> lines = new ArrayList<>();
		lines.add("");
		for (String key: data.keySet()) {
			lines.set(0, lines.get(0).concat(key+" "));
		    ArrayList<Object> value = data.get(key);
		    for(int i=0; i<value.size(); i++) {
		    	if(lines.size()==i+1){
		    		lines.add("");
		    	}
		    	lines.set(i+1, lines.get(i+1).concat(value.get(i)+" "));
		    }
		}
		for(String line : lines) {
			System.out.println(line);
		}
	}
	
	// Prints only the first 5 rows
	public void printHead() {
		ArrayList<String> lines = new ArrayList<>();
		lines.add("");
		for (String key: data.keySet()) {
			lines.set(0, lines.get(0).concat(key+" "));
		    ArrayList<Object> value = data.get(key);
		    for(int i=0; i<value.size() && i<5; i++) {
		    	if(lines.size()==i+1){
		    		lines.add("");
		    	}
		    	lines.set(i+1, lines.get(i+1).concat(value.get(i)+" "));
		    }
		}
		for(String line : lines) {
			System.out.println(line);
		}
	}
	
	// Print only the last 5 rows
	public void printTail() {
		ArrayList<String> lines = new ArrayList<>();
		lines.add("");
		for (String key: data.keySet()) {
			lines.set(0, lines.get(0).concat(key+" "));
		    ArrayList<Object> value = data.get(key);
		    int start = Math.max(value.size()-5, 0);
		    for(int i=0; i+start<value.size() && i<5; i++) {
		    	if(lines.size()==i+1){
		    		lines.add("");
		    	}
		    	lines.set(i+1, lines.get(i+1).concat(value.get(i+start)+" "));
		    }
		}
		for(String line : lines) {
			System.out.println(line);
		}
	}
	
    public static void main( String[] args )
    {
    	Object[][] o = new Object[2][2];
    	o[0][0] = "Grenoble";
    	o[1][0] = "Paris";
    	o[0][1] = 115;
    	o[1][1] = 300;
    	
    	String[] l = {"ville", "taille"};
    	int[] indexes = {10, 5};
    	
    	Dataframe data = new Dataframe(o, l, indexes);
    	data.printAll();
    }
}

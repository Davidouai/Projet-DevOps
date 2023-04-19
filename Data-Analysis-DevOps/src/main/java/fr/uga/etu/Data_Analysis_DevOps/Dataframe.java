package fr.uga.etu.Data_Analysis_DevOps;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	
	public Object get(String label, int index) {
		return data.get(label).get(indexes.get(index));
	}
	
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}

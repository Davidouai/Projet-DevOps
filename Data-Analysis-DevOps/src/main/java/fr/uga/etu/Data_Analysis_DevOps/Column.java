package fr.uga.etu.Data_Analysis_DevOps;

import java.util.ArrayList;

public class Column<T> {
	ArrayList<T> data;
	
	Column(ArrayList<T> t) {
		data = t;
	}
	
	public T get(int i) {
		return data.get(i);
	}
	
	public void add(T obj) {
		data.add(obj);
	}
	
}

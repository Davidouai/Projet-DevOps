package fr.uga.etu.Data_Analysis_DevOps;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class DataframeTest 
{
    /**
     * Rigorous Test :-)
     */
	Dataframe data;
	Object[][] objects;
	String[] labels;
	int[] indexes;
	
	@Before
	public void init() {
    	objects = new Object[4][3];
    	objects[0][0] = "Grenoble";
    	objects[1][0] = "Paris";
    	objects[2][0] = "Lyon";
    	objects[3][0] = "Marseille";
    	
    	objects[0][1] = 115.45;
    	objects[1][1] = 300.59;
    	objects[2][1] = 256.78;
    	objects[3][1] = 234.56;
    	
    	objects[0][2] = 200000;
    	objects[1][2] = 6000000;
    	objects[2][2] = 2000000;
    	objects[3][2] = 1000000;
    	
    	String [] l = {"ville", "taille", "population"};
    	labels = l;
    	int [] i = {10, 5, 20, 9};
    	indexes = i;
	}
	
	
    @Test
    public void checkClumnsAccess()
    {
    	data = new Dataframe(objects, labels, indexes);
    	for(int i = 0; i < indexes.length; i++) {
    		for(int j = 0; j < labels.length; j++) {
    			assertEquals(data.get(labels[j], indexes[i]), objects[i][j]);
    		}
    	}
    }

    @Test
    public void checkCSV()
    {
    	int[] indexes = new int[18];
    	for (int i = 0; i < 18; i++) {
    		indexes[i] = i*i;
    	}
    	data = new Dataframe("/quotas.csv", indexes);
    	assertEquals(data.get("Username", indexes[6]), "trailhead19.d1fxj2goytkp@example.com");
    }

    @Test
    public void checkLineSelection()
    {
    	data = new Dataframe(objects, labels, indexes);
    	
    	Object[][] o2 = data.selectLines(indexes);

    	for(int i = 0; i < indexes.length; i++) {
    		for(int j = 0; j < labels.length; j++) {
    			assertEquals(objects[i][j], o2[i][j]);
    		}
    	}
    }

    @Test
    public void checkColumnSelection()
    {
    	data = new Dataframe(objects, labels, indexes);
    	
    	Object[][] o2 = data.selectColumns(labels);

    	for(int i = 0; i < indexes.length; i++) {
    		for(int j = 0; j < labels.length; j++) {
    			assertEquals(objects[i][j], o2[i][j]);
    		}
    	}
    }
    
    
}

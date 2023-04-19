package fr.uga.etu.Data_Analysis_DevOps;

import static org.junit.Assert.assertTrue;

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
    @Test
    public void checkClumnsAccess()
    {
    	Object[][] o = new Object[2][2];
    	o[0][0] = "Grenoble";
    	o[1][0] = "Paris";
    	o[0][1] = 115;
    	o[1][1] = 300;
    	
    	String[] l = {"ville", "taille"};
    	int[] indexes = {10, 5};
    	
    	data = new Dataframe(o, l, indexes);
    	for(int i = 0; i < indexes.length; i++) {
    		for(int j = 0; j < l.length; j++) {
        		assertTrue(data.get(l[j], indexes[i]) == o[i][j]);
    		}
    	}
    }
}

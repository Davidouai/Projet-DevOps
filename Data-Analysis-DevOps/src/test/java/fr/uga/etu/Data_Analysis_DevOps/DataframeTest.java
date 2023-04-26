package fr.uga.etu.Data_Analysis_DevOps;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
    			assertEquals(data.get(l[j], indexes[i]), o[i][j]);
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
    public void printAllTest()
    {
    	Object[][] o = new Object[2][2];
    	o[0][0] = "Grenoble";
    	o[1][0] = "Paris";
    	o[0][1] = 115;
    	o[1][1] = 300;
    	
    	String[] l = {"ville", "taille"};
    	int[] indexes = {10, 5};
    	
    	data = new Dataframe(o, l, indexes);
    	
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	PrintStream originalOut = System.out;
    	System.setOut(new PrintStream(outContent));
    	data.printAll();
    	String expected = "ville taille \n" + "Grenoble 115 \n" + "Paris 300 \n";
    	assertEquals(expected, outContent.toString());
    	System.setOut(originalOut);
    }
    
    @Test
    public void printAllCSVTest()
    {
    	int[] indexes = new int[18];
    	for (int i = 0; i < 18; i++) {
    		indexes[i] = i*i;
    	}
    	data = new Dataframe("/quotas.csv", indexes);
    	
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	PrintStream originalOut = System.out;
    	System.setOut(new PrintStream(outContent));
    	data.printAll();
    	String expected = "StartDate OwnerName Username Rating QuotaAmount \n"
    			+ "2016-01-01 Chris Riley trailhead9.ub20k5i9t8ou@example.com 0.1 150000 \n"
    			+ "2016-02-01 Chris Riley trailhead9.ub20k5i9t8ou@example.com 0.2 150000 \n"
    			+ "2016-03-01 Chris Riley trailhead9.ub20k5i9t8ou@example.com 0.3 150000 \n"
    			+ "2016-01-01 Harold Campbell trailhead14.jibpbwvuy67t@example.com 0.4 150000 \n"
    			+ "2016-02-01 Harold Campbell trailhead14.jibpbwvuy67t@example.com 0.5 150000 \n"
    			+ "2016-03-01 Harold Campbell trailhead14.jibpbwvuy67t@example.com 0.6 150000 \n"
    			+ "2016-01-01 Jessica Nichols trailhead19.d1fxj2goytkp@example.com 0.7 150000 \n"
    			+ "2016-02-01 Jessica Nichols trailhead19.d1fxj2goytkp@example.com 0.8 150000 \n"
    			+ "2016-03-01 Jessica Nichols trailhead19.d1fxj2goytkp@example.com 0.9 150000 \n"
    			+ "2016-01-01 Catherine Brown trailhead16.kojyepokybge@example.com 0.1 150000 \n"
    			+ "2016-02-01 Catherine Brown trailhead16.kojyepokybge@example.com 0.11 150000 \n"
    			+ "2016-03-01 Catherine Brown trailhead16.kojyepokybge@example.com 0.12 150000 \n"
    			+ "2016-01-01 Kelly Frazier trailhead7.zdcsy4ax10mr@example.com 0.13 150000 \n"
    			+ "2016-02-01 Kelly Frazier trailhead7.zdcsy4ax10mr@example.com 0.14 150000 \n"
    			+ "2016-03-01 Kelly Frazier trailhead7.zdcsy4ax10mr@example.com 0.15 150000 \n"
    			+ "2016-01-01 Dennis Howard trailhead4.wfokpckfroxp@example.com 0.16 150000 \n"
    			+ "2016-02-01 Dennis Howard trailhead4.wfokpckfroxp@example.com 0.17 150000 \n"
    			+ "2016-03-01 Dennis Howard trailhead4.wfokpckfroxp@example.com 0.18 150000 \n"
    			+ "";
    	assertEquals(expected, outContent.toString());
    	System.setOut(originalOut);
    }
    
    @Test
    public void printHeadTest()
    {
    	Object[][] o = new Object[2][2];
    	o[0][0] = "Grenoble";
    	o[1][0] = "Paris";
    	o[0][1] = 115;
    	o[1][1] = 300;
    	
    	String[] l = {"ville", "taille"};
    	int[] indexes = {10, 5};
    	
    	data = new Dataframe(o, l, indexes);
    	
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	PrintStream originalOut = System.out;
    	System.setOut(new PrintStream(outContent));
    	data.printHead();
    	String expected = "ville taille \n" + "Grenoble 115 \n" + "Paris 300 \n";
    	assertEquals(expected, outContent.toString());
    	System.setOut(originalOut);
    }
    
    @Test
    public void printHeadCSVTest()
    {
    	int[] indexes = new int[18];
    	for (int i = 0; i < 18; i++) {
    		indexes[i] = i*i;
    	}
    	data = new Dataframe("/quotas.csv", indexes);
    	
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	PrintStream originalOut = System.out;
    	System.setOut(new PrintStream(outContent));
    	data.printHead();
    	String expected = "StartDate OwnerName Username Rating QuotaAmount \n"
    			+ "2016-01-01 Chris Riley trailhead9.ub20k5i9t8ou@example.com 0.1 150000 \n"
    			+ "2016-02-01 Chris Riley trailhead9.ub20k5i9t8ou@example.com 0.2 150000 \n"
    			+ "2016-03-01 Chris Riley trailhead9.ub20k5i9t8ou@example.com 0.3 150000 \n"
    			+ "2016-01-01 Harold Campbell trailhead14.jibpbwvuy67t@example.com 0.4 150000 \n"
    			+ "2016-02-01 Harold Campbell trailhead14.jibpbwvuy67t@example.com 0.5 150000 \n";
    	assertEquals(expected, outContent.toString());
    	System.setOut(originalOut);
    }
    
    @Test
    public void printTailTest()
    {
    	Object[][] o = new Object[2][2];
    	o[0][0] = "Grenoble";
    	o[1][0] = "Paris";
    	o[0][1] = 115;
    	o[1][1] = 300;
    	
    	String[] l = {"ville", "taille"};
    	int[] indexes = {10, 5};
    	
    	data = new Dataframe(o, l, indexes);
    	
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	PrintStream originalOut = System.out;
    	System.setOut(new PrintStream(outContent));
    	data.printTail();
    	String expected = "ville taille \n" + "Grenoble 115 \n" + "Paris 300 \n";
    	assertEquals(expected, outContent.toString());
    	System.setOut(originalOut);

    }
    
    @Test
    public void printTailCSVTest()
    {
    	int[] indexes = new int[18];
    	for (int i = 0; i < 18; i++) {
    		indexes[i] = i*i;
    	}
    	data = new Dataframe("/quotas.csv", indexes);
    	
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	PrintStream originalOut = System.out;
    	System.setOut(new PrintStream(outContent));
    	data.printTail();
    	String expected = "StartDate OwnerName Username Rating QuotaAmount \n"
    			+ "2016-02-01 Kelly Frazier trailhead7.zdcsy4ax10mr@example.com 0.14 150000 \n"
    			+ "2016-03-01 Kelly Frazier trailhead7.zdcsy4ax10mr@example.com 0.15 150000 \n"
    			+ "2016-01-01 Dennis Howard trailhead4.wfokpckfroxp@example.com 0.16 150000 \n"
    			+ "2016-02-01 Dennis Howard trailhead4.wfokpckfroxp@example.com 0.17 150000 \n"
    			+ "2016-03-01 Dennis Howard trailhead4.wfokpckfroxp@example.com 0.18 150000 \n";
    	assertEquals(expected, outContent.toString());
    	System.setOut(originalOut);
    }
       
}

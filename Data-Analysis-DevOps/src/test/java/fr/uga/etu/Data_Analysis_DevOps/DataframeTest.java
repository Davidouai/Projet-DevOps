package fr.uga.etu.Data_Analysis_DevOps;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
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
	Dataframe dataCSV;
	Object[][] objects;
	String[] labels;
	int[] indexes;
	int[] indexesCSV;
	
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
    	
    	data = new Dataframe(objects, labels, indexes);
    	indexesCSV = new int[18];
    	for (int k = 0; k< 18; k++) {
    		indexesCSV[k] = k*k;
    	}
    	
    	dataCSV = new Dataframe("/quotas.csv", indexesCSV);
	}
	
	
    @Test
    public void checkClumnsAccess()
    {
    	for(int i = 0; i < indexes.length; i++) {
    		for(int j = 0; j < labels.length; j++) {
    			assertEquals(data.get(labels[j], indexes[i]), objects[i][j]);
    		}
    	}
    }

    @Test
    public void checkCSV()
    {
    	assertEquals(dataCSV.get("Username", indexesCSV[6]), "trailhead19.d1fxj2goytkp@example.com");
    }

    @Test
    public void checkLineSelection()
    {
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
    	Object[][] o2 = data.selectColumns(labels);

    	for(int i = 0; i < indexes.length; i++) {
    		for(int j = 0; j < labels.length; j++) {
    			assertEquals(objects[i][j], o2[i][j]);
    		}
    	}
    }

    @Test
    public void checkEqualsSelection()
    {
    	Object[][] o2 = data.selectLinesEquals(6000000);
    	
    	assertEquals(o2[0][0], objects[1][0]);
    	
    }
    
    @Test
    public void printAllTest()
    {
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	PrintStream originalOut = System.out;
    	System.setOut(new PrintStream(outContent));
    	data.printAll();
    	String expected = "ville taille population \n"
    			+ "Grenoble 115.45 200000 \n"
    			+ "Paris 300.59 6000000 \n"
    			+ "Lyon 256.78 2000000 \n"
    			+ "Marseille 234.56 1000000 \n";
    	assertEquals(expected, outContent.toString());
    	System.setOut(originalOut);
    }
    
    @Test
    public void printAllCSVTest()
    {
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	PrintStream originalOut = System.out;
    	System.setOut(new PrintStream(outContent));
    	dataCSV.printAll();
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
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	PrintStream originalOut = System.out;
    	System.setOut(new PrintStream(outContent));
    	data.printHead();
    	String expected = "ville taille population \n"
    			+ "Grenoble 115.45 200000 \n"
    			+ "Paris 300.59 6000000 \n"
    			+ "Lyon 256.78 2000000 \n"
    			+ "Marseille 234.56 1000000 \n";
    	assertEquals(expected, outContent.toString());
    	System.setOut(originalOut);
    }
    
    @Test
    public void printHeadCSVTest()
    {
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	PrintStream originalOut = System.out;
    	System.setOut(new PrintStream(outContent));
    	dataCSV.printHead();
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
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	PrintStream originalOut = System.out;
    	System.setOut(new PrintStream(outContent));
    	data.printTail();
    	String expected = "ville taille population \n"
    			+ "Grenoble 115.45 200000 \n"
    			+ "Paris 300.59 6000000 \n"
    			+ "Lyon 256.78 2000000 \n"
    			+ "Marseille 234.56 1000000 \n";
    	assertEquals(expected, outContent.toString());
    	System.setOut(originalOut);

    }
    
    @Test
    public void printTailCSVTest()
    {
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	PrintStream originalOut = System.out;
    	System.setOut(new PrintStream(outContent));
    	dataCSV.printTail();
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

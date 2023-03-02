package test;
import server.DatasetManager;
import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;

import server.DatasetManagerFactory;
import utils.SimpleCSVReader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
public class CSVReaderTest {
	private SimpleCSVReader csvreader;
	
@Test
	
	public void testDataManager() {
		assertNotNull("after setUp() loaderToTest must not be null",csvreader);
	}
	public void registerDatasetMethod() {
		assertEquals("the return value of loadAllData() must be equal to the total number of objects created(119347)",119347,csvreader.load(null));
	}
}

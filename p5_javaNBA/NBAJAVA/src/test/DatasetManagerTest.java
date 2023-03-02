package test;

import server.DatasetManager;
import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;

import server.DatasetManagerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class DatasetManagerTest {

	private DatasetManager dmanager;
	
	@Test
	
	public void testDataManager() {
		assertNotNull("after setUp() loaderToTest must not be null",dmanager);
	}
	@Test
	public void registerDatasetMethod() {
		assertEquals("the return value of loadAllData() must be equal to the total number of objects created(119347)",119347,dmanager.registerDataset(null, null));
	}
	
	@Test
	public void retrieveDatasetMethod() {
		assertEquals("the return value of loadAllData() must be equal to the total number of objects created(119347)",119347,dmanager.retrieveDataset(null, null));
	}
	@Test
	public void filterDatasetMethod() {
		assertEquals("the return value of loadAllData() must be equal to the total number of objects created(119347)",119347,dmanager.filterDataset(null, null, null, null));
	}
	@Test
	public void dataProjectionMethod() {
		assertEquals("the return value of loadAllData() must be equal to the total number of objects created(119347)",119347,dmanager.getDatasetProjection(null, null));
	}
	
}

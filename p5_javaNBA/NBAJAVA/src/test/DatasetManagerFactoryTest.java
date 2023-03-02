package test;
import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;


import server.DatasetManagerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;



/**
 * 
 * 
 *
 *
 */
public class DatasetManagerFactoryTest {
	private DatasetManagerFactory factoryToTest;
	@Before
	public void setUp() throws Exception {
		
		factoryToTest = new DatasetManagerFactory();
		
	}
	
	@Test
	public void testLoaderFactoryNotNull() {
		assertNotNull("After setUp(), the loaderFactory must not be null.",factoryToTest);
	}
}

package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({DatasetManagerTest.class,DatasetManagerFactoryTest.class,CSVReaderTest.class})
public class AllTests {
}

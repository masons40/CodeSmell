/*
	Test suite uses the Suite package to run HeatTest
	and ParameterizedHeatTest consecutively.
 */
package nosejobTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
@RunWith(Suite.class)
@SuiteClasses({
		HeatTest.class,
		ParameterizedHeatTest.class })
public class TestSuite	{}
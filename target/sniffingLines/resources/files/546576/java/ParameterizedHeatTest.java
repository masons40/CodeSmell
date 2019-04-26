package nosejobTest;

import nosejob.*;
import org.junit.Test;
import org.junit.runner.*;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.runners.Parameterized.*;

/*
	ParameterizedHeatTest creates multiple test instances and directly
	injects data values into fields using the @Parameter annotation.
*/
@RunWith(Parameterized.class)
public class ParameterizedHeatTest {
	private final double DELTA = 0.01; //"Wiggle" room for acceptable value

	//Define parameters for triples of data array
	@Parameter(0)
	public double test_celsius;
	@Parameter(1)
	public double test_fahrenheit;
	@Parameter(2)
	public double test_kelvin;

	@Parameters
	public static Collection<Object[]> temperatures() {
		/*
			Define array of triples where each of the three elements
			correspond to the three parameters defined above.
		*/
		Object[][] data = new Object[][] {
				{0.0,   32.0,   273.15},
				{10.0,  50.0,   283.15},
				{20.0,  68.0,   293.15},
				{30.0,  86.0,   303.15},
				{40.0,  104.0,  313.15},
				{50.0,  122.0,  323.15},
				{60.0,  140.0,  333.15},
				{70.0,  158.0,  343.15},
				{80.0,  176.0,  353.15},
				{90.0,  194.0,  363.15},
				{100.0, 212.0,  373.15},
		};
		//return a list view of the three dimensional data array
		return Arrays.asList(data);
	}

	//Celsius->
	@Test
	public void testCelsiusToFahrenheit(){
		double result = new Celsius(test_celsius).toFahrenheit();
		assertEquals(test_fahrenheit, result, DELTA);
	}

	@Test
	public void testCelsiusToKelvin(){
		double result = new Celsius(test_celsius).toKelvin();
		assertEquals(test_kelvin, result, DELTA);
	}

	//Fahrenheit
	@Test
	public void testFahrenheitToCelsius(){
		double result = new Fahrenheit(test_fahrenheit).toCelsius();
		assertEquals(test_celsius, result, DELTA);
	}

	@Test
	public void testFahrenheitToKelvin(){
		double result = new Fahrenheit(test_fahrenheit).toKelvin();
		assertEquals(test_kelvin, result, DELTA);
	}

	//Kelvin->
	@Test
	public void testKelvinToCelsius(){
		double result = new Kelvin(test_kelvin).toCelsius();
		assertEquals(test_celsius, result, DELTA);
	}

	@Test
	public void testKelvinToFahrenheit(){
		double result = new Kelvin(test_kelvin).toFahrenheit();
		assertEquals(test_fahrenheit, result, DELTA);
	}

}

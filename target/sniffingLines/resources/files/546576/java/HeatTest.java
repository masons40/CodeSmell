package nosejobTest;

import nosejob.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HeatTest {
	private final double test_value = 100;
	private final double DELTA = 0.01;
	private Celsius test_celsius = new Celsius(test_value);
	private Fahrenheit test_fahrenheit = new Fahrenheit(test_value);
	private Kelvin test_kelvin = new Kelvin(test_value);


	//Celsius->
	@Test
	public void testCelsiusToFahrenheit() {
		double result = test_celsius.toFahrenheit();
		assertEquals(212.0, result, DELTA);
	}

	@Test
	public void testCelsiusToKelvin() {
		double result = test_celsius.toKelvin();
		assertEquals(373.15, result, DELTA);
	}

	//Fahrenheit->
	@Test
	public void testFahrenheitToCelsius() {
		double result = test_fahrenheit.toCelsius();
		assertEquals(37.78, result, DELTA);
	}

	@Test
	public void testFahrenheitToKelvin() {
		double result = test_fahrenheit.toKelvin();
		assertEquals(310.93, result, DELTA);
	}

	//Kelvin->
	@Test
	public void testKelvinToCelsius() {
		double result = test_kelvin.toCelsius();
		assertEquals(-173.15, result, DELTA);
	}

	@Test
	public void testKelvinToFahrenheit() {
		double result = test_kelvin.toFahrenheit();
		assertEquals(-279.67, result, DELTA);
	}
}

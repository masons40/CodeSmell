package nosejob;

public class Fahrenheit implements HeatScalable{
	private double value=0;
	
	public Fahrenheit(double init_value) {
		value = init_value;
	}

	@Conversion(min=-273.15)
	public double toCelsius() {
		return 5*(value - 32)/9;
	}

	@Invariant
	@Conversion(min=-459.67)
	public double toFahrenheit() {
		return value;
	}

	@Conversion(min=0)
	public double toKelvin() {
		return 5*(value + 459.67)/9;
	}
	
	public String toString() {
		return HeatScalable.formatter.format(value) + "F";
	}
}

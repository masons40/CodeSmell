package nosejob;

public class Celsius implements HeatScalable{
	private double value = 0;
	
	public Celsius(double init_value) {
		value = init_value;
	}

	@Invariant
	@Conversion(min=-273.15)
	public double toCelsius() {
		return value;
	}
	@Conversion(min=-459.67)
	public double toFahrenheit() {
		return (9*value)/5 + 32;
	}
	@Conversion(min=0)
	public double toKelvin() {
		return value + 273.15;
	}
	
	public String toString() {
		return HeatScalable.formatter.format(value) + "C";
	}
}

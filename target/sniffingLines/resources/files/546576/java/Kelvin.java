package nosejob;

public class Kelvin implements HeatScalable{
	private double value = 0;

	public Kelvin(double init_value){
		value = init_value;
	}

	@Conversion(min=-273.15)
	public double toCelsius() {
		return value - 273.15;
	}
	@Conversion(min=-459.67)
	public double toFahrenheit() {
		return (9*value)/5 - 459.67;
	}
	@Invariant
	@Conversion(min=0)
	public double toKelvin() {
		return value;
	}

	public String toString() {
		return HeatScalable.formatter.format(value) +  "K";
	}
}
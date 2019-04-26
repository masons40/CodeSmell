package nosejob;
/**
 * Class converts Fahrenheit temperatures to Celsius, Fahrenheit and Kelvin
 */

public class Fahrenheit implements HeatScalable {
    private double temperature;
    public Fahrenheit(double temperature){
        this.temperature = temperature;
    }

    /**
     * Methods to convert Fahrenheit temperature to Celsius
     * @return Celsius value of the input Fahrenheit temperature
     */

    @Override
    @Conversion(min=-1500, max=10000)
    public double toCelsius() {
        return (temperature-32)*5/9;
    }

    /**
     * Methods to convert Fahrenheit temperature to Fahrenheit
     * @return Fahrenheit value of the input Fahrenheit temperature
     */

    @Override
    @Invariant
    @Conversion
    public double toFahrenheit() {
        return temperature;
    }

    /**
     * Methods to convert Fahrenheit temperature to Kelvin
     * @return Kelvin value of the input Fahrenheit temperature
     */

    @Override
    @Conversion(min=-1500, max=10000)
    public double toKelvin() {
        return (temperature+459.67)*5/9;
    }

    /**
     * toString method to give the correct format for Fahrenheit temperature
     * @return Formatted string
     */

    public String toString(){
        return "Fahrenheit: "+formatter.format(toFahrenheit())+"F";
    }

    /**
     * Method to return the temperature in Fahrenheit of the Fahrenheit object
     * @return double of the Fahrenheit object's temperature
     */

    @Override
    public double getTemperature() {
        return  temperature;
    }
}

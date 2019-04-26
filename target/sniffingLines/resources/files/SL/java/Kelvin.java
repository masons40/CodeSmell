package nosejob;
/**
 * Class converts Kelvin temperatures to Celsius, Fahrenheit and Kelvin
 */

public class Kelvin implements HeatScalable {
    private double temperature;
    public Kelvin(double temperature){
        this.temperature = temperature;
    }

    /**
     * Methods to convert Kelvin temperature to Celsius
     * @return Celsius value of the input Kelvin temperature
     */

    @Override
    @Conversion(min=-1000.15, max=10000.15)
    public double toCelsius() {
        return temperature-273.15;
    }

    /**
     * Methods to convert Kelvin temperature to Fahrenheit
     * @return Fahrenheit value of the input Kelvin temperature
     */

    @Override
    @Conversion(min=-1000.15, max=10000.15)
    public double toFahrenheit() {
        return (temperature-273.15)*(9.0/5)+32;
    }

    /**
     * Methods to Kelvin Celsius temperature to Kelvin
     * @return Kelvin value of the input Kelvin temperature
     */

    @Override
    @Invariant
    @Conversion
    public double toKelvin() {
        return temperature;
    }

    /**
     * toString method to give the correct format for Kelvin temperature
     * @return Formatted string
     */

    public String toString(){
        return "Kelvin: "+formatter.format(toKelvin())+"K";
    }

    /**
     * Method to return the temperature in Kelvin of the Kelvin object
     * @return double of the Kelvin object's temperature
     */

    @Override
    public double getTemperature() {
        return  temperature;
    }
}

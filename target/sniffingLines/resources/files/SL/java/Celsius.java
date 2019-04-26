package nosejob;
/**
 * Class converts Celsius temperatures to Celsius, Fahrenheit and Kelvin
 */

public class Celsius implements HeatScalable{

    private double temperature;
    public Celsius(double temperature){
        this.temperature = temperature;
    }

    /**
     * Methods to convert Celsius temperature to Celsius
     * @return Celsius value of the input Celsius temperature
     */

    @Override
    @Invariant
    @Conversion
    public double toCelsius() {
        return temperature;
    }

    /**
     * Methods to convert Celsius temperature to Fahrenheit
     * @return Fahrenheit value of the input Celsius temperature
     */

    @Override
    @Conversion(min=-1000, max=10000)
    public double toFahrenheit() {
        return temperature*(9.0/5)+32;
    }

    /**
     * Methods to convert Celsius temperature to Kelvin
     * @return Kelvin value of the input Celsius temperature
     */

    @Override
    @Conversion(min=-1000, max=10000)
    public double toKelvin() {
        return temperature+273.15;
    }

    /**
     * toString method to give the correct format for Celsius temperature
     * @return Formatted string
     */

    public String toString(){
        return "Celsius: "+formatter.format(toCelsius())+"C";
    }

    /**
     * Method to return the temperature in Celsius of the Celsius object
     * @return double of the Celsius object's temperature
     */

    @Override
    public double getTemperature() {
        return  temperature;
    }
}

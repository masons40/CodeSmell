package nosejob;

public class Kelvin implements HeatScalable {
    private double temperature;
    public Kelvin(double temperature){
        this.temperature = temperature;
    }

    @Override
    public double toCelsius() {
        return temperature-273.15;
    }

    @Override
    public double toFahrenheit() {
        return (temperature-273.15)*(9/5)+32;
    }

    @Override
    public double toKelvin() {
        return temperature;
    }

    public String toString(){
        return "Celsius: "+formatter.format(toCelsius())+"C, Fahrenheit: "+formatter.format(toFahrenheit())+"F, Kelvin: "+formatter.format(toKelvin())+"K";
    }
}

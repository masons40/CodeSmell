package nosejob;

public class Fahrenheit implements HeatScalable {
    private double temperature;
    public Fahrenheit(double temperature){
        this.temperature = temperature;
    }

    @Override
    public double toCelsius() {
        return (temperature-32)*5/9;
    }

    @Override
    public double toFahrenheit() {
        return temperature;
    }

    @Override
    public double toKelvin() {
        return (temperature+459.67)*5/9;
    }

    public String toString(){
        return "Celsius: "+formatter.format(toCelsius())+"C, Fahrenheit: "+formatter.format(toFahrenheit())+"F, Kelvin: "+formatter.format(toKelvin())+"K";
    }
}

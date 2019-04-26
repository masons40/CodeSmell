package nosejob;

public class Celsius implements HeatScalable{

    private double temperature;
    public Celsius(double temperature){
        this.temperature = temperature;
    }

    @Override
    public double toCelsius() {
        return temperature;
    }

    @Override
    public double toFahrenheit() {
        return temperature*(9/5)+32;
    }

    @Override
    public double toKelvin() {
        return temperature+273.15;
    }

    public String toString(){
        return "Celsius: "+formatter.format(toCelsius())+"C, Fahrenheit: "+formatter.format(toFahrenheit())+"F, Kelvin: "+formatter.format(toKelvin())+"K";
    }
}

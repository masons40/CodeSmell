package nosejob;

public class Fahrenheit implements HeatScalable {

    private double temp;

    public Fahrenheit(double temp){
        this.temp = temp;
    }
    @Override
    public double toCelsius() {
        return (temp - 32) * 5/9;
    }

    @Override
    public double toFahrenheit() {
        return temp;
    }

    @Override
    public double toKelvin() {
        return (temp - 32) * 5/9 + 273.15;
    }

    public String toString(){
        return "Celsius:" + formatter.format(toCelsius()) + "C \nFahrenheit:" + formatter.format(toFahrenheit()) + "F\nKelvin:" + formatter.format(toKelvin()) + "K \n" ;
    }
}

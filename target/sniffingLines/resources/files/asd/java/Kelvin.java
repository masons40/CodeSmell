package nosejob;

public class Kelvin implements HeatScalable {

    private double temp;
    public  Kelvin(double temp){
        this.temp = temp;
    }
    @Override
    public double toCelsius() {
        return temp - 273.15;
    }

    @Override
    public double toFahrenheit() {
        return (temp - 273.15) * (9/5) + 32;
    }

    @Override
    public double toKelvin() {
        return temp;
    }

    public String toString(){
        return "Celsius:" + formatter.format(toCelsius()) + "C \nFahrenheit:" + formatter.format(toFahrenheit()) + "F\nKelvin:" + formatter.format(toKelvin()) + "K \n" ;
    }
}

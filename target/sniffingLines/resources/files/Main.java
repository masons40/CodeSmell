package nosejob;

public class Main {
    public static void main(String[] args){
        Celsius celsiusTemp = new Celsius(25);
        Fahrenheit fahrenheitTemp = new Fahrenheit(50);
        Kelvin kelvinTemp = new Kelvin(200);

        System.out.println(celsiusTemp.toString());
        System.out.println(fahrenheitTemp.toString());
        System.out.println(kelvinTemp.toString());
    }
}

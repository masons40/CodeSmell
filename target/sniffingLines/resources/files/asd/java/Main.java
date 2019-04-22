package nosejob;

public class Main {


    public static void main(String[] args){

        Celsius cel = new Celsius(25);
        Fahrenheit fah = new Fahrenheit(45);
        Kelvin kel = new Kelvin(273.15);

        System.out.println(cel.toString());
        System.out.println(fah.toString());
        System.out.println(kel.toString());
    }
}

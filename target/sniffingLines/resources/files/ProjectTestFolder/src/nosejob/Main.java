package nosejob;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Celsius celsiusTemp = new Celsius(25);
        Fahrenheit fahrenheitTemp = new Fahrenheit(50);
        Kelvin kelvinTemp = new Kelvin(200);

        System.out.println(celsiusTemp.toString());
        System.out.println(fahrenheitTemp.toString());
        System.out.println(kelvinTemp.toString());

        Converter convert = new Converter();
        ArrayList<Method> annotatedCelsiusMethods;
        annotatedCelsiusMethods = convert.getConversionMethods(Celsius.class, Double.TYPE);

        System.out.println("\nAnnotated Methods from the Celsius class of type double are as follows:");
        for (int i=0; i<annotatedCelsiusMethods.size(); i++) {
            System.out.println(annotatedCelsiusMethods.get(i));
        }

        //574.59 kelvin and fahrenheit meet, -40 where celsius and fahrenheit meet
        boolean test1 = convert.isFixedPoint(new Celsius(-40));
        System.out.println("\nIs -40.0C a fixed point: " +test1);
        boolean test2 = convert.isFixedPoint(new Fahrenheit(20.6));
        System.out.println("Is 20.6F a fixed point: " +test2);
        boolean test3 = convert.isFixedPoint(new Kelvin(574.59));
        System.out.println("Is 574.59K a fixed point: " +test3);

        //Inspection on the DummyClass for Assignment 5
        Inspector inspector = new Inspector();
        Method testMethod = DummyClass.class.getMethod("birthday", int.class);
        System.out.println("\nNumber of primitive methods from test class: "+inspector.getNumPrimitiveMethods(DummyClass.class));
        System.out.println("Number of primitive fields from test class: "+inspector.getNumPrimitiveFields(DummyClass.class));
        System.out.println("Number of primitive parameters from test method: "+inspector.getNumPrimitiveParameters(testMethod));
        System.out.println("Number of private methods from test class: "+inspector.getNumPrivateMethods(DummyClass.class));
        System.out.println("Number of public methods from test class: "+inspector.getNumPublicMethods(DummyClass.class));
        System.out.println("Number of protected methods from test class: "+inspector.getNumProtectedMethods(DummyClass.class));

        //Checking whether the Celsius and SingletonClass is a singleton for Assignment 6
        System.out.println("\nIs the Celsius class a singleton: "+inspector.isSingleton(Celsius.class));
        System.out.println("Is the SingletonClass class a singleton: "+inspector.isSingleton(SingletonClass.class));
    }
}

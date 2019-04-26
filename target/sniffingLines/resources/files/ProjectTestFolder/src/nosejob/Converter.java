package nosejob;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Converter class that can either get the conversion methods of a given class
 * and return type. Class also can see if a given temperature is a fixed point
 * across both Celsius, Fahrenheit and Kelvin
 */

public class Converter {

    public Converter() {}

    /**
     * Method that takes in a class object which can be of any type and also that class's return type.
     * It will then return the Conversion methods that are in the inputted class as an array list of methods.
     * @param myClass class object that can have any type
     * @param returnType specifies the return type of the methods we wish to get
     * @return array list of the Conversion methods in the Class myClass of return type double
     */

    public ArrayList<Method> getConversionMethods(Class<?> myClass, Class returnType) {
        Method[] classMethods = myClass.getMethods();
        ArrayList<Method> annotatedMethods = new ArrayList<>();

        for (Method m:classMethods) {
            if (m.isAnnotationPresent(Conversion.class) && m.getReturnType() == returnType) {
                annotatedMethods.add(m);
            }
        }

        return annotatedMethods;
    }

    /**
     * Method that takes in an instance of the HeatScalable interface and then sees
     * if the instance is a fixed point i.e. that the value in either Celsius, Kelvin or Fahrenheit
     * is the same numeric value as one of the two other Heat scales
     * @param instance a Celsius, Kelvin or Fahrenheit object
     * @return boolean as to whether the inputted HeatScalable instance is a fixed point or not
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */

    public boolean isFixedPoint(HeatScalable instance) throws InvocationTargetException, IllegalAccessException {

        Class<?> heatObject = instance.getClass();
        String packageAndClassName = heatObject.toString();
        String[] className = packageAndClassName.split("\\.");  //used to get the class name
        double temperature = instance.getTemperature();  //used to get the temperature value of the inputted HeatScalable instance
        ArrayList<Double> conversionTemperatures = new ArrayList<>();  //will store the temperature values when the input temperature is converted to the different heat scales
        
        Method[] instanceMethods = instance.getClass().getMethods();  //array of the methods in the class
        //looping for each method in the class
        for (Method m:instanceMethods) {
            if (m.isAnnotationPresent(Conversion.class)) {
                if (m.isAnnotationPresent(Invariant.class)) {
                    continue;  //we don't care if the method is an invariant conversion method
                } else {
                    double temp = 0.0;

                    if (className[1].equals("Celsius")) {
                        Celsius celsiusObject = new Celsius(temperature);
                        temp = (double) m.invoke(celsiusObject);  //invoking the current conversion method on the object and getting the resulting temperature

                    } else if (className[1].equals("Fahrenheit")) {
                        Fahrenheit fahrenheitObject = new Fahrenheit(temperature);
                        temp = (double) m.invoke(fahrenheitObject);

                    } else if (className[1].equals("Kelvin")) {
                        Kelvin kelvinObject = new Kelvin(temperature);
                        temp = (double) m.invoke(kelvinObject);
                    }
                    //rounding the temperature to 2 decimal places
                    temp *= 100;
                    temp = Math.round(temp);
                    temp /= 100;
                    conversionTemperatures.add(temp);  //adding the temperature to the array list of temperature values
                }
            }
        }

        //checking of the temperature was the same across any of the other temperature scales
        if (conversionTemperatures.get(0) == temperature ||
            conversionTemperatures.get(1) == temperature) {
            return true;  //returns true if there is a match between the numeric values and false otherwise
        } else {
            return false;
        }
    }
}

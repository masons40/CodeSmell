package nosejob;

//import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.lang.reflect.Method;


public class Converter {

	//Return an array list of all method that return returnType in myClass
	public ArrayList<Method> getMethodsWithType(Class myClass, Class returnType) {
		Method[] ms	= myClass.getMethods();
		ArrayList<Method> matchingMethods = new ArrayList<>();
		for (Method	m:ms) {
			if (m.getReturnType().equals(returnType))
				matchingMethods.add(m);
		}
		return matchingMethods;
	}

	//Invoke methods of a class that return objects of returnType
	public ArrayList<Object> invokeWithArgs(Class myClass, Class returnType, Object[] classArgs, Class[] classArgTypes)
	{
		ArrayList<Method> methods = getMethodsWithType(myClass, returnType);
		ArrayList<Object> values = new ArrayList<Object>();

		if (!methods.isEmpty())
			try {
				for(int i=0; i< methods.size(); i++) {
					Object testArg =myClass.getDeclaredConstructor(classArgTypes).newInstance(classArgs);
					Object new_value = methods.get(i).invoke(testArg);
					values.add(new_value);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return values;
	}

	//Given temperature value object implementing HeatScalable,
	//returns true if at least two of it's conversion methods return the same value
	public boolean isFixedPoint(HeatScalable myTemp) {
		Class myClass = myTemp.getClass();

		ArrayList<Method> convertMethods = getMethodsWithType(myClass, double.class); //all the convert methods
		ArrayList<Object> values = new ArrayList<>(); //results from all convert methods

		if (!convertMethods.isEmpty())
			try {
				for(int i=0; i< convertMethods.size(); i++) {
					Object new_value = convertMethods.get(i).invoke(myTemp); //Obtain result for this convert method
					values.add(HeatScalable.formatter.format(new_value)); //Format and add this result to values array list
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		//Create a set derived from the UNIQUE values from values
		Set<Object> uniqueTemps = new HashSet<Object>(values);

		//If this unique list is smaller, that implies that at	least two values are identical
		return uniqueTemps.size() < values.size();
	}

}

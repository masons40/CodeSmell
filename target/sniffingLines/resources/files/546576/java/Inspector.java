import dummy.DummyClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class Inspector {

	public boolean isSingleton(Class host) {
		boolean hasPrivateConstructors = true;
		boolean hasInitiatingMethod = false;
	//	try {
			Constructor constructors[] = host.getDeclaredConstructors();
			for (Constructor c : constructors) {
				if (Modifier.isPublic((c.getModifiers()))) {
					hasPrivateConstructors = false;
				}
			}
			hasInitiatingMethod = hasInitiatingMethod(host);


	//	} catch (NoSuchMethodException e) {
	//		e.printStackTrace();
	//	}

		return hasPrivateConstructors && hasInitiatingMethod;
	}

	/**
	 *
	 * @param host = Class being tested for Singleton pattern
	 * @return Whether there is a static method in class that returns object of type class
	 */
	public Boolean hasInitiatingMethod (Class host) {
		Method[] allMethods	= host.getDeclaredMethods();
		List<Method> matchingMethods = new ArrayList<>();
		for (Method	m : allMethods) {
			if (Modifier.isStatic(m.getModifiers()) && m.getReturnType() == host) {
				return true;
			}
		}
		return false;
	}

	/**
	 *
	 * @param host = Class being tested
	 * @return number of class fields (e.g. variables) that are primitive
	 */
	public int getNumPrimitiveFields(Class	host) {
		int numPrimFields = 0;
		Field[] allFields = host.getDeclaredFields();
		//List<Field> primitiveFields = new ArrayList<>();
		for (Field f : allFields) {
			if ((f.getType().isPrimitive())) {
				numPrimFields++;
				//primitiveFields.add(f);
			}
		}
		return numPrimFields;
	}

	/**
	 *
	 * @param host = Class being tested
	 * @return Number of primitive methods (duh)
	 */
	public int getNumPrimitiveMethods(Class host) {
		Method[] allMethods	= host.getDeclaredMethods();
		int numPrimitiveMethods = 0;
		//List<Method> primitiveMethods = new ArrayList<>();
		for (Method	m : allMethods) {
			//I don't consider void to be primitive, so I've covered that case
			if (m.getReturnType().isPrimitive() && !m.getReturnType().equals(void.class)) {
				numPrimitiveMethods++;
				//primitiveMethods.add(m);
			}
		}
		return numPrimitiveMethods;
	}

	/**
	 *
	 * @param host = Method being tested
	 * @return Number of parameters that are primitive (e.g. int, char, short...)
	 */
	public int getNumPrimitiveParameters(Method host) {
		Class[] parameters = host.getParameterTypes();
		int numPrimitiveParams = 0;
		//List<Class> primitiveParams = new ArrayList<>();
		for (Class	c : parameters) {
			if (c.isPrimitive()) {
				numPrimitiveParams++;
				//primitiveParams.add(m);
			}
		}
		return numPrimitiveParams;
	}

	/**
	 *
	 * @param host = Class being tested
	 * @return Number of methods that return a primitive type
	 */
	public int getNumPrivateMethods	(Class host) {
		Method[] allMethods	= host.getDeclaredMethods();
		int numPrivateMethods = 0;
		//List<Method> privateMethods = new ArrayList<>();
		for (Method	m : allMethods) {
			if (Modifier.isPrivate(m.getModifiers())) {
				numPrivateMethods++;
				//privateMethods.add(m);
			}
		}
		return numPrivateMethods;
	}

	/**
	 *
	 * @param host = Class being tested
	 * @return = Number of methods that can be publicly accessed
	 */
	public int getNumPublicMethods(Class host) {
		Method[] allMethods	= host.getDeclaredMethods();
		int numPublicMethods = 0;
		//List<Method> publicMethods = new ArrayList<>();
		for (Method	m : allMethods) {
			if (Modifier.isPublic(m.getModifiers())) {
				numPublicMethods++;
				//publicMethods.add(m);
			}
		}
		return numPublicMethods;
	}

}

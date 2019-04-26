package nosejob;

import java.lang.reflect.*;

/**
 * Inspector class that is used to get the count of various different methods from a passed in class
 * or to get the number of primitive methods/field from an inputted class
 * or to get the number of primitive parameters from an inputted method
 */

public class Inspector {

    /**
     * Method to get the number of primitive methods from a class
     * We also need to make sure we ignore methods with void as the return type
     * @param host the class we want to analyse
     * @return the number of primitive methods in the class 'host'
     */

    public int getNumPrimitiveMethods(Class host) {
        Method[] methods = host.getDeclaredMethods();
        int numberOfPrimitiveMethods = 0;

        for (Method m:methods) {
            if (m.getReturnType().isPrimitive()&&(m.getReturnType()!=void.class)) {
                numberOfPrimitiveMethods++;
            }
        }

        return numberOfPrimitiveMethods;
    }

    /**
     * Method to get the number of primitive fields from a class
     * @param host the class we want to analyse
     * @return the number of primitive fields in the class 'host'
     */

    public int getNumPrimitiveFields(Class host) {
        Field[] fields = host.getDeclaredFields();
        int numberOfPrimitiveFields = 0;

        for (Field f:fields) {
            if (f.getType().isPrimitive()) {
                numberOfPrimitiveFields++;
            }
        }

        return numberOfPrimitiveFields;
    }

    /**
     * Method to get the number of primitive parameters passed into a method
     * @param host the method we want to analyse
     * @return the number of primitive parameters passed into the method 'host'
     */

    public int getNumPrimitiveParameters(Method host) {
        Parameter[] parameters = host.getParameters();
        int numberOfPrimitiveParameters = 0;

        for (Parameter p:parameters) {
            if (p.getType().isPrimitive()) {
                numberOfPrimitiveParameters++;
            }
        }

        return  numberOfPrimitiveParameters;
    }

    /**
     * Method to get the number of private methods from a class
     * @param host the class we want to analyse
     * @return the number of private methods in the class 'host'
     */

    public int getNumPrivateMethods(Class host) {
        Method[] methods = host.getDeclaredMethods();
        int numberOfPrivateMethods = 0;

        for (Method m:methods) {
            if (Modifier.isPrivate(m.getModifiers())) {
                numberOfPrivateMethods++;
            }
        }

        return numberOfPrivateMethods;
    }

    /**
     * Method to get the number of public methods from a class
     * @param host the class we want to analyse
     * @return the number of public methods in the class 'host'
     */

    public int getNumPublicMethods(Class host) {
        Method[] methods = host.getDeclaredMethods();
        int numberOfPublicMethods = 0;

        for (Method m:methods) {
            if (Modifier.isPublic(m.getModifiers())) {
                numberOfPublicMethods++;
            }
        }

        return numberOfPublicMethods;
    }

    /**
     * Method to get the number of protected methods from a class
     * @param host the class we want to analyse
     * @return the number of protected methods in the class 'host'
     */

    public int getNumProtectedMethods(Class host) {
        Method[] methods = host.getDeclaredMethods();
        int numberOfProtectedMethods = 0;

        for (Method m:methods) {
            if (Modifier.isProtected(m.getModifiers())) {
                numberOfProtectedMethods++;
            }
        }

        return numberOfProtectedMethods;
    }

    /**
     * Singleton design pattern is used when you only want to have one instance of a given class.
     * The method isSingleton checks to see if the passed in host is a singleton
     * It checks the three cases for a class to be a singleton, these are:
     * That the constructor is private,
     * The field is private, static and of the type of the class,
     * That it has a static method with the return type of the class.
     * @param host the class we want to analyse
     * @return a boolean as to whether the passed in class is a singleton or not
     */

    public boolean isSingleton(Class host) {
        Constructor[] constructors = host.getDeclaredConstructors();
        Field[] fields = host.getDeclaredFields();
        Method[] methods = host.getDeclaredMethods();
        boolean constructorPrivate = false;
        boolean fieldStaticPrivate = false;
        boolean methodStatic = false;

        for (Constructor c:constructors) {
            if (Modifier.isPrivate(c.getModifiers())) {
                constructorPrivate = true;
            }
        }

        for (Field f:fields) {
            if (f.getType()==host) {
                if (Modifier.isPrivate(f.getModifiers()) && Modifier.isStatic(f.getModifiers())) {
                    fieldStaticPrivate = true;
                }
            }
        }

        for (Method m:methods) {
            if (m.getReturnType()==host) {
                if (Modifier.isStatic(m.getModifiers())) {
                    methodStatic = true;
                }
            }
        }

        return constructorPrivate&&fieldStaticPrivate&&methodStatic;
    }
}

import dummy.*;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        Class myDummyClass = DummyClass.class;
        Class myTimeClass = Time.class;
        Class myLazyClass = LazySingleton.class;    //Singleton (Lazy Implementation)
        Class mySingletonClass = Singleton.class;   //Singleton
        Inspector inspector = new Inspector();

        System.out.println("Is \""+ myDummyClass.getSimpleName() + "\" singleton?: " + inspector.isSingleton(myDummyClass));
        System.out.println("Is \""+ myTimeClass.getSimpleName() + "\" singleton?: " + inspector.isSingleton(myTimeClass));
        System.out.println("Is \""+ myLazyClass.getSimpleName() + "\" singleton?: " + inspector.isSingleton(myLazyClass));
        System.out.println("Is \""+ mySingletonClass.getSimpleName()+ "\" singleton?: " + inspector.isSingleton(mySingletonClass));
        
    }

    //Test Methods:
    private static void testPrimFields(Class myClass, Inspector inspector){
        int numPrimFields = inspector.getNumPrimitiveFields(myClass);
        System.out.println("-> Found " + numPrimFields + " primitive fields in the " +
                myClass.getName() + " class");
    }

    private static void testPrimMethods(Class myClass, Inspector inspector){
        int numPrimMethods = inspector.getNumPrimitiveMethods(myClass);
        System.out.println("-> Found " + numPrimMethods + " methods with " +
                " primitive return types in "+ myClass.getName() + " class");
    }

    private static void testPrimParams(Class myClass, Inspector inspector) {
        try {
            Method myMethod = DummyClass.class.getMethod("foo", int.class, float.class, Time.class, int[].class);
            System.out.println("-> Found " + inspector.getNumPrimitiveParameters(myMethod) + " primitive parameters in " +
                    myClass.getName() + " class");
        } catch (NoSuchMethodException e) { //If method was not found in myClass
            e.printStackTrace();
        }
    }

    private static void testPrivateMethods(Class myClass, Inspector inspector) {
        int numPrivateMethods = inspector.getNumPrivateMethods(myClass);
        System.out.println("-> Found " + numPrivateMethods + "  private methods in " +
                            myClass.getName() + " class");
    }

    public static void testPublicMethods(Class myClass, Inspector inspector) {
        int numPublicMethods = inspector.getNumPublicMethods(myClass);
        System.out.println("-> Found " + numPublicMethods + "  public methods in " +
                myClass.getName() + " class");
    }
}

package files;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

public class VariableDetector {

    public ArrayList<SLVariable> getVariables(Class<?> myClass) {

        Field[] classFields = myClass.getDeclaredFields();
        Method[] classMethods = myClass.getDeclaredMethods();
        ArrayList<SLVariable> variables = new ArrayList<>();

        //used to get constructor variables
        for (Field f:classFields) {
            System.out.println(f.getType()+ " "+f.getName());
        }

        //used to get the variables passed in to a method
        for (Method m:classMethods) {
            for (Parameter p:m.getParameters()) {
                //first variable is called arg0, arg1, etc.
                System.out.println(p.getType()+": "+p.getName());
            }
        }



        return null;
    }
}

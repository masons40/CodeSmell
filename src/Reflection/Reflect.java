package Reflection;

import files.SLMethod;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Reflect{

    /*
    public ArrayList<SLMethod> getMethods(Class c){
        ArrayList<SLMethod> methods = new ArrayList<>();
        for(Method m: c.getDeclaredMethods()){
            methods.add(new SLMethod(m.getName(), Modifier.toString(m.getModifiers()), m.getReturnType().toString(),m.getParameters()));
        }
        return methods;
    }

    public List getNumPrimitiveFields(Class c){
        return Arrays.stream(c.getDeclaredFields()).filter(m -> m.getType().isPrimitive()).collect(Collectors.toList());
    }

    public List getNumDeclaredClassObjects(Class c){
        return Arrays.stream(c.getDeclaredFields()).filter(m -> !(m.getType().isPrimitive())).collect(Collectors.toList());
    }

    public boolean isEnum(Class c){
        return c.isEnum();
    }

    public boolean isInterface(Class c){
        return c.isInterface();
    }

    */
}

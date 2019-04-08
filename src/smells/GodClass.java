package smells;

import files.*;

/*
A God Class is a class which either knows or does too much
To see if a class is a god class or not, a number of metrics will be needed to detect
this. We will try to detect this by seeing how much access a class has to foreign data,
how much the object does that doesn't relate to itself, the number of lines and the number
of methods in the class
 */
public class GodClass {
    public boolean isGodClass(SLClass cls) {
        if((isLoosleyCoupled(cls)||hasLowCohesion(cls)) && cls.getMethods().size()>20){
            return true;
        }
        else {
            return false;
        }

    }

    private boolean isLoosleyCoupled(SLClass cls){
        int numOfNonPrimitive = 0;

        for (SLVariable variable : cls.getVariables()) {
            if(!variable.getType().matches("(byte|short|int|long|float|double|boolean|char|String)")){
                numOfNonPrimitive ++;
            }
        }

        return numOfNonPrimitive>20;
    }

    /*
    cohesion refers to how many methods inside a class that are
    relevant to that class only
     */
    private boolean hasLowCohesion(SLClass cls){
        int numPrivateMethods = 0;
        int numPublicMethods = 0;

        for (SLMethod method : cls.getMethods()) {
            if(method.getAccessor().contains("public")){
                numPublicMethods++;
            }
            else if(method.getAccessor().contains("private")){
                numPrivateMethods++;
            }
        }

        return numPublicMethods>numPrivateMethods;
    }
}

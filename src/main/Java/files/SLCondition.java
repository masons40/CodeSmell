package files;
import java.util.ArrayList;

/*
Class used to represent a condition
 */
public class SLCondition {
    private String conditionType;
    private String name;

    public SLCondition(String conditionType, String name) {
        this.conditionType = conditionType;
        this.name = name;
    }

    public String getConditionType(){ return  conditionType; }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return conditionType + "(" + name + ")";
    }
}

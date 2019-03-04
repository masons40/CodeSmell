package SmellTest;

public class SLVariable {

    private String scope = "",type="", name="", value="";

    public SLVariable(String scope, String type, String name, String value) {
        this.scope = scope;
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public String getScope() {
        return scope;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}

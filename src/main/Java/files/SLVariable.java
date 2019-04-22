package files;

public class SLVariable {

    private String scope = "",type="", name="";

    public SLVariable(String scope, String type, String name) {
        this.scope = scope;
        this.type = type;
        this.name = name;
    }

    //are constants found?
    public String getScope() {
        return scope;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }


    public  String toString(){
        return scope+" "+type+" "+name+" ";
    }
}

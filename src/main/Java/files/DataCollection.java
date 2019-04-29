package files;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import java.util.ArrayList;

/**
 * This class is used to extract all of the data from the java parser we use to extract all of the info
 * This class will be used to create an object of a file that is parsed through our system. This file is was made by us
 * so we know all of the correct methods to use to get teh relevant info for smells. A lot of the methods use lambdas so please use
 * java version 9 and above
 */

public class DataCollection {

    private ArrayList<SLVariable> variables = new ArrayList<>();
    private ArrayList<SLMethod> methods = new ArrayList<>();
    private ArrayList<SLClass> classes = new ArrayList<>();
    private ArrayList<SLInterface> interfaces = new ArrayList<>();
    private ArrayList<SLEnum> enums = new ArrayList<>();

    private int classPrev = -1;

    public void variableDetection(CompilationUnit cu){
        cu.findAll(FieldDeclaration.class).stream().filter(FieldDeclaration::isFieldDeclaration).forEach(f -> {
            for(VariableDeclarator vd: f.getVariables()){
                variables.add(new SLVariable(f.getModifiers().toString(),vd.getType().asString(),vd.getName().asString()));
            }
            //f.getRange().map(r -> r.begin.line).orElse(-1));
        });

    }

    public void enumDetector(CompilationUnit cu){

        cu.findAll(EnumDeclaration.class).forEach(en -> {
            String name = en.getName().asString();
            String enumMod;
            ArrayList<String> enumConstants = new ArrayList<>();
            if(!(en.getModifiers().isEmpty())){
                enumMod=en.getModifiers().get(0).toString();
            }else{
                enumMod="";
            }
            en.findAll(EnumConstantDeclaration.class).forEach(m->{
                enumConstants.add(m.getName().asString());
            });
            /*
            if(en.isNestedType()){
                enums.get(enumCounter).addNestedEnum(name);
            }
            */
            Boolean isNested = en.isNestedType();
            enums.add(new SLEnum(name,enumMod,enumConstants,isNested));
        });
    }

    public void interfaceDetetection(CompilationUnit cu){
        cu.findAll(ClassOrInterfaceDeclaration.class).stream().filter(ClassOrInterfaceDeclaration::isInterface).forEach(i -> {
            String name = i.getName().asString();
            ArrayList<SLMethod> interfaceMethod = new ArrayList<>();
            ArrayList<SLVariable> interfaceVariables = new ArrayList<>();
            ArrayList<String> interfaceExtends = new ArrayList<>();

            i.findAll(MethodDeclaration.class).forEach(m -> {
                ArrayList<String> parameters = new ArrayList<>();
                for(Parameter p: m.getParameters()){
                    parameters.add(p.getType().asString()+" "+p.getName().asString());
                }
                interfaceMethod.add(new SLMethod(m.getName().asString(),m.getModifiers().toString(),m.getType().asString(),parameters, "", null));
            });
            i.findAll(FieldDeclaration.class).stream().filter(FieldDeclaration::isFieldDeclaration).forEach(f  -> {
                for(VariableDeclarator ivd: f.getVariables()){
                    interfaceVariables.add(new SLVariable(f.getModifiers().toString(),ivd.getType().asString(),ivd.getName().asString()));
                }
            });
            for(ClassOrInterfaceType ie: i.getExtendedTypes()){
                interfaceExtends.add(ie.asString());
            }
            interfaces.add(new SLInterface(interfaceMethod, interfaceVariables, name, interfaceExtends));
        });
    }
    public void classDetection(CompilationUnit cu){
        cu.findAll(ClassOrInterfaceDeclaration.class).stream().filter(c -> !c.isInterface()).forEach(c -> {

            String name = c.getName().asString();
            ArrayList<String> extendsName = new ArrayList<>();
            ArrayList<String> classModifier = new ArrayList<>();
            ArrayList<String> implementsNames = new ArrayList<>();
            ArrayList<SLMethod> classMethods = new ArrayList<>();
            ArrayList<SLVariable> classVariables = new ArrayList<>();

            for(ClassOrInterfaceType e:c.getExtendedTypes()) {
                extendsName.add(e.toString());
            }
            for(ClassOrInterfaceType i:c.getImplementedTypes()){
                implementsNames.add(i.asString());
            }
            for(Modifier m:c.getModifiers()){
                classModifier.add(m.toString());
            }
            if(c.isInnerClass()){
                classes.get(classPrev).addSubClasses(name);
            }
            c.findAll(FieldDeclaration.class).stream().filter(FieldDeclaration::isFieldDeclaration).forEach(f  -> {
                for(VariableDeclarator ivd: f.getVariables()){
                    classVariables.add(new SLVariable(f.getModifiers().toString(),ivd.getType().asString(),ivd.getName().asString()));
                }
            });
            c.findAll(MethodDeclaration.class).stream().filter(MethodDeclaration::isMethodDeclaration).forEach(method -> {
                ArrayList<String> parameters = new ArrayList<>();
                String type = method.getType().toString();
                String modifier="";
                ArrayList<SLVariable> methodVariables = new ArrayList<>();
                if(!(method.getModifiers().isEmpty())){
                    modifier = method.getModifiers().get(0).toString();
                }
                String methodName = method.getName().asString();
                for(Parameter p: method.getParameters()){
                    parameters.add(p.getType().asString()+" "+p.getName().asString());
                }
                String body="";
                for(Object b:method.getBody().stream().toArray()){
                    body+=b.toString();
                }

                method.findAll(FieldDeclaration.class).stream().filter(FieldDeclaration::isFieldDeclaration).forEach(field -> {
                    for(VariableDeclarator vd: field.getVariables()) {
                        methodVariables.add(new SLVariable(field.getModifiers().toString(),vd.getType().asString(),vd.getName().asString()));
                    }
                });
                classMethods.add(new SLMethod(methodName,modifier,type,parameters,body, methodVariables));
            });
            classes.add(new SLClass(classModifier, name, extendsName, implementsNames, c.isInnerClass(), classMethods, classVariables));
            classPrev++;
        });
    }

    public void methodDetection(CompilationUnit cu){
        cu.findAll(MethodDeclaration.class).stream().filter(MethodDeclaration::isMethodDeclaration).forEach(f -> {
            ArrayList<String> parameters = new ArrayList<>();
            String type = f.getType().toString();
            String modifier="";
            ArrayList<SLVariable> methodVariables = new ArrayList<>();
            if(!(f.getModifiers().isEmpty())){
                modifier = f.getModifiers().get(0).toString();
            }
            String name = f.getName().asString();
            for(Parameter p: f.getParameters()){
                parameters.add(p.getType().asString()+" "+p.getName().asString());
            }
            String body="";
            for(Object b:f.getBody().stream().toArray()){
                body+=b.toString();

            }
            methods.add(new SLMethod(name,modifier,type,parameters,body, methodVariables));
        });
    }

    public int commentCount(CompilationUnit cu){
        return cu.getComments().size();
    }

    public String getMethods(){
        String me = "";
        for(SLMethod v: methods){
            me+=  v.getAccessor()+" "+v.getReturnType()+" "+v.getName()+" "+v.getParameters().toString();
        }
        return me;
    }

    public String getInterfaces(){
        String me = "";
        for(SLInterface i: interfaces){
            me+=  i.getName()+" "+i.getInterfaceMethods().toString()+" "+i.interfaceExtends().toString()+" "+i.getInterfaceVariables().toString()+"\n";
        }
        return me;
    }

    public String getClasses(){
        String me = "";
        for(SLClass v: classes){
            me+=  v.getClassModfier()+" "+v.getClassName()+" "+v.getExtendsName()+" "+v.getInterfaceNames().toString() + v.getSubClasses().toString() + v.getSubClass()+"\n";
        }
        return me;
    }

    public String getVariables(){
        String variablesString = "";
        for(SLVariable v: variables){
            variablesString+=  v.getScope()+" "+v.getType()+" "+v.getName()+"\n";
        }
        return variablesString;
    }

    public String getEnums(){
        String variablesString = "";
        for(SLEnum v: enums){
            variablesString+=  v.getName()+" "+v.getModifier()+" "+v.getEnumVariables()+" "+v.getIsNested()+"\n";
        }
        return variablesString;
    }

    public ArrayList<SLVariable> getVariablesList(){
        return variables;
    }

    public ArrayList<SLClass> getClassList(){
        return classes;
    }

    public ArrayList<SLMethod> getMethodList(){
        return methods;
    }

    public ArrayList<SLInterface> getInterfaceList(){
        return interfaces;
    }

    public ArrayList<SLEnum> getEnumList(){
        return enums;
    }

    public void clearAll(){
        variables.clear();
        methods.clear();
        interfaces.clear();
        enums.clear();
        classes.clear();
    }

}

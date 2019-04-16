package Management;

import javax.servlet.http.HttpServletResponse;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.ArrayList;

public class ClassManagement {

    private String javaFilesLocation;
    private ArrayList javaFileNames;
    private ArrayList<URL> javaFileNamesPath;

    public ClassManagement(String javaFilesLocation, ArrayList javaFileNames){
        this.javaFileNames = javaFileNames;
        this.javaFilesLocation = javaFilesLocation;
    }


    /*
    public void ClassCreation(){
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        ArrayList<String> files = new ArrayList<>();
        for(Object s: javaFileNames){
            files.add(javaFilesLocation+File.separator+s + ".java");
        }
        compiler.run(null,null,null, files.toArray(new String[0]));
    }

    private void createPaths() throws MalformedURLException {
        for(Object n: javaFileNames){
            javaFileNamesPath.add(new URL( javaFilesLocation+File.separator+n+".class"));
        }
    }

    public Class instantiateClass(String className,HttpServletResponse response) throws IOException {
        createPaths();
        URLClassLoader cl = new URLClassLoader((URL[]) this.javaFileNamesPath.toArray(),Thread.currentThread().getContextClassLoader());

        String dir = cl.findResource(className+".class").toString();
        String pckg = dir.substring(0,dir.lastIndexOf("/"));
        pckg = pckg.substring(pckg.lastIndexOf("/")+1);

        String project = javaFilesLocation;

        response.getWriter().println(dir);
        response.getWriter().println(pckg);

        try {




            Constructor<?>[] constructor;
            if(pckg.equals(project))
            {
                constructor = cl.loadClass(className).getDeclaredConstructors();

            }
            else {
                constructor = cl.loadClass(pckg + "." + className).getDeclaredConstructors();
            }

            if(constructor.length > 1) {
                Constructor<?> c = constructor[0];
                Class<?>[] types = c.getParameterTypes();
                Object[] arguments = new Object[c.getParameterCount()];
                int index = 0;
                // Declaring variables required for the constructor
                for (Class type : types) {
                    if (type.isPrimitive()) {
                        arguments[index] = 0;
                    }
                    else
                        arguments[index] = null;
                    index++;
                }


                Class cls = c.newInstance(arguments).getClass();
                System.out.println(className + " has been instantiated successfully.");

                return cls;
            }
            else
            {
                // If its main or an abstract class
                System.out.println(className + " has been instantiated successfully.");

                if(pckg.equals(project))
                {
                    return cl.loadClass(className);

                }else
                    return cl.loadClass(pckg+"."+className);
            }

        }catch(ClassNotFoundException e)
        {
            e.printStackTrace();
            System.out.println(className + " is not a valid class name");
        } catch (IllegalAccessException  e){
            e.printStackTrace();
            System.out.println("Could not create new instance of class: " + className);
        } catch (InvocationTargetException e) {
            e.getTargetException();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


        return null;
    }
    */

}

package org.algo.davidyu.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class ClassLoaderHandler {
    public void start(){
        loadClass();
    }

    public void loadClass(){
        URL myurl[] = new URL[1];
        try {
            myurl[0] = new URL("file:///C:\\workspace\\JavaSample\\JavaSample\\classes\\");
            URLClassLoader x = new URLClassLoader(myurl);
            Class c = x.loadClass("TestMain");          // TestMain is name of class

            Object ob = c.newInstance();
            Class arg2[] = {};
            Method m2 = c.getMethod("printValue", String.class);        // method name
            m2.invoke(ob, "Hi");                                        // parameter

            Object ob2 = c.newInstance();
            Class arg3[] = {};
            Method m3 = c.getMethod("getList");
            List<Integer> list = (List<Integer>) m3.invoke(ob2);                // return value
            System.out.println(list.toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }

}

class TestMain {

    List<Integer> list;

    public void printValue(String value) {
        System.out.println(value);
    }

    public List<Integer> getList(){
        list = new ArrayList<>();
        list.add(1);list.add(2);list.add(3);
        return list;
    }

    public String[] getStr() {
        String[] str = {"davidyu","erickyu"};
        return str;
    }
}

/*
*
* */

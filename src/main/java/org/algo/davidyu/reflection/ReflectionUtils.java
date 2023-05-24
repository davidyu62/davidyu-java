package org.algo.davidyu.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
//
//class PersonForReflection {
//    public String name = "davidyu";
//    public int age = 36;
//    public boolean available = false;
//    private String secret = "dbrldus0990!";
//
//    public PersonForReflection() {
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public String getSecret() {
//        return secret;
//    }
//
//    public void setSecret(String secret) {
//        this.secret = secret;
//    }
//
//    public boolean changeAvailable(boolean available){
//        this.available = available;
//        return false;
//    }
//
//    public void printAge(){
//        System.out.println("age is "+ age);
//    }
//
//    public void setAndPrintAge(int age){
//        this.age = age;
//        System.out.println("age is "+ age);
//    }
//
//    @Override
//    public String toString() {
//        return "PersonForReflection{" +
//                "name='" + name + '\'' +
//                ", age=" + age +
//                ", secret='" + secret + '\'' +
//                '}';
//    }
//}
public class ReflectionUtils {

    Class<?> clz = null;
    String classPackage;
    String className;

    public ReflectionUtils(String classPackage, String className){
        this.classPackage = classPackage;
        this.className = className;
    }

    /**
     *  load reflection class
     * */
    public Class<?> getClz(){
        try{
            if(clz != null){
                return clz;
            }else{
                clz = Class.forName(classPackage + "." + className);        // class package is required
                return clz;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  retrieve all accessible field
     * */
    public List<String> retreiveAcceesibleFields(){
        List<String> fieldsList = new ArrayList<>();
        Field[] f = clz.getDeclaredFields();
        for (int i = 0; i < f.length; i++) {
            fieldsList.add(f[i].getName());
        }
        return fieldsList;
    }

    /**
     *  retrieve all field
     * */
    public List<String> retreiveAllFields(){
        List<String> fieldsList = new ArrayList<>();
        Field[] f = clz.getDeclaredFields();
        for (int i = 0; i < f.length; i++) {
            f[i].setAccessible(true);               // set true
            fieldsList.add(f[i].getName());
        }
        return fieldsList;
    }

    /**
     *  retrieve all method(including private)
     */
    public List<String> retreiveAllMethod(){
        List<String> methodList = new ArrayList<>();
        Method[] methods = clz.getDeclaredMethods();
        for(int i = 0; i < methods.length; i++){
            if(!methods[i].isAccessible()){                 // if private
                methods[i].setAccessible(true);
            }
            methodList.add(methods[i].getName());
        }
        return methodList;
    }

    public void callMethod(String methodName){
        Method method = null;
        try {
            method = clz.getDeclaredMethod(methodName);
            method.invoke(clz.newInstance());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    public void callMethodWithParam(String methodName){
        Method method = null;
        try {
            method = clz.getDeclaredMethod(methodName,int.class);           // int.class is parameter of method
            method.invoke(clz.newInstance(),10);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    public void callMethodAndReturn(String methodName){
        Method method = null;
        try {
            method = clz.getDeclaredMethod(methodName);           // int.class is parameter of method
            String returnValue = method.invoke(clz.newInstance()).toString();
            System.out.println("returnValue : "+returnValue);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ReflectionUtils reflectionUtils = new ReflectionUtils("org.algo.davidyu.reflection","PersonForReflection");
        reflectionUtils.getClz();
        reflectionUtils.retreiveAcceesibleFields();
        reflectionUtils.retreiveAllFields();
        reflectionUtils.retreiveAllMethod();
        reflectionUtils.callMethodWithParam("setAndPrintAge");
        reflectionUtils.callMethodAndReturn("getName");
    }
}

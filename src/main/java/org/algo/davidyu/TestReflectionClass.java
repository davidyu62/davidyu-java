package org.algo.davidyu;

public class TestReflectionClass {
    public String name = "davidyu";
    public int age = 36;
    public boolean available = false;
    private String secret = "dbrldus0990!";

    public TestReflectionClass() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public boolean changeAvailable(boolean available){
        this.available = available;
        return false;
    }

    public void printAge(){
        System.out.println("age is "+ age);
    }

    public void setAndPrintAge(int age){
        this.age = age;
        System.out.println("age is "+ age);
    }

    @Override
    public String toString() {
        return "PersonForReflection{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", secret='" + secret + '\'' +
                '}';
    }
}

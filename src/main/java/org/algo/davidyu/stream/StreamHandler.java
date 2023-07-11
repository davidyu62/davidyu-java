package org.algo.davidyu.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamHandler {

    public static void listHandleStream(){
        List<People> peoples = new ArrayList<>();
        People people1 = new People("davidyu",36);
        People people2 = new People("erick",35);
        People people3 = new People("mjkim",33);
        People people4 = new People("swyu",4);
        People people5 = new People("giyeonyu",36);
        People people6 = new People("sjkim",67);
        People people7 = new People("csyu",65);
        People people8 = new People("shhan",6);
        peoples.add(people1);peoples.add(people2);peoples.add(people3);peoples.add(people4);peoples.add(people5);peoples.add(people6);peoples.add(people7);peoples.add(people8);

        // 출력
        peoples.stream().forEach(people -> {
            System.out.println(people);
        });
        System.out.println("==========================================");
        // filter
        List<People> adults = peoples.stream()
                .filter(people -> people.getAge() >= 20).collect(Collectors.toList());
        adults.stream().forEach(adult -> {
            System.out.println(adult);
        });
        System.out.println("==========================================");
        //sorted
        List<People> ascPeople = peoples.stream()
                .sorted(Comparator.comparing(People::getAge)).collect(Collectors.toList());
        ascPeople.stream().forEach(people -> {
            System.out.println(people);
        });
        System.out.println("==========================================");
        //map
        List<String> names = peoples.stream()
                .map(t -> t.getName()).collect(Collectors.toList());
        names.stream().forEach(name -> {
            System.out.println(name);
        });
        System.out.println("==========================================");
        // uppercase
        List<String> uppeerNames = peoples.stream()
                .map(t ->t.getName())
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        uppeerNames.stream().forEach(name -> {
            System.out.println(name);
        });
    }

    public static void main(String[] args){
        StreamHandler.listHandleStream();
    }
}

class People{
    String name;
    int age;

    public People(String name, int age) {
        this.name = name;
        this.age = age;
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

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

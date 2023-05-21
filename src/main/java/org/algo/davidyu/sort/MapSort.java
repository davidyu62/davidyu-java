package org.algo.davidyu.sort;

import java.util.*;

public class MapSort {

    public void hashMapSortByValue(){
        Map<String,Integer> map = new HashMap<>();
        map.put("tomcat-8080",5);
        map.put("tomcat-8081",15);
        map.put("tomcat-8082",12);
        map.put("tomcat-8083",1);
        map.put("tomcat-8084",5);
        map.put("tomcat-8085",8);
        map.put("tomcat-8086",9);
        map.put("tomcat-8087",21);
        map.put("tomcat-8088",11);
        map.put("tomcat-8089",16);

        List<Map.Entry<String,Integer>> entryList = new LinkedList<>(map.entrySet());
//        entryList.sort(new Comparator<Map.Entry<String, Object>>() {
//            @Override
//            public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
//                return (Long)o1.getValue() - (Long)o2.getValue();
//            }
//        });
//        entryList.sort(Map.Entry.comparingByValue(Comparator.<T>reverseOrder()));
        entryList.sort(Map.Entry.comparingByValue());
        Collections.reverse(entryList);
        System.out.println("entryList:"+entryList.toString());

    }

    public void hashMapSortByValueWithObject(){
        Map<String,Person> map = new HashMap<>();
        map.put("davidyu",new Person(36));
        map.put("mjkim", new Person(34));
        map.put("siwoo",new Person(2));
        System.out.println(map);

        List<Map.Entry<String,Person>> entryList = new LinkedList<>(map.entrySet());
        entryList.sort(new Comparator<Map.Entry<String, Person>>() {
            @Override
            public int compare(Map.Entry<String, Person> o1, Map.Entry<String, Person> o2) {
                return o1.getValue().getAge() - o2.getValue().getAge();     // 오름차순s
            }
        });

        System.out.println("entryList:"+entryList);
    }

    public static void main(String[] args){
        new MapSort().hashMapSortByValueWithObject();
    }
}

class Person{
    int age;
    public Person(int age){
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                '}';
    }
}

package org.algo.davidyu.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListSort {

    public void DTOStringDesc(){
        List<Data> list = new ArrayList<>();
        list.add(new Data("data1","23"));
        list.add(new Data("data2","43"));
        list.add(new Data("data3","67"));
        list.add(new Data("data4","12"));
        list.add(new Data("data5","93"));
        list.add(new Data("data6","56"));
        list.add(new Data("data7","73"));
        list.add(new Data("data8","12"));
        list.add(new Data("data9","98"));
        list.add(new Data("data10","87"));

        Collections.sort(list, new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                if(o1.getCpu().compareTo(o2.getCpu()) > 0){
                    return -1;
                }else {
                    return 1;
                }
            }
        });

        System.out.println(list);

    }

    public void DTOIntegerDesc(){
        List<Data> list = new ArrayList<>();
        list.add(new Data("data1","23",2));
        list.add(new Data("data2","43",9));
        list.add(new Data("data3","67",02));
        list.add(new Data("data4","12",26));
        list.add(new Data("data5","93",22));
        list.add(new Data("data6","56",32));
        list.add(new Data("data7","73",67));
        list.add(new Data("data8","12",125));
        list.add(new Data("data9","98",42));
        list.add(new Data("data10","87",23));

        Collections.sort(list, new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                if(o1.getAge() > o2.getAge()){
                    return -1;
                }else {
                    return 1;
                }
            }
        });

        System.out.println(list);
        System.out.println("============");
        System.out.println("list.size():"+list.size());
        int listSize = list.size();
        if(listSize > 5){
            for(int i=5; i<listSize; i++){
                System.out.println("remove:"+list.get(5));
                list.remove(5);
            }
        }

        System.out.println(list);

    }


    public static void main(String[] args){
//        new ListSort().DTOStringDesc();
        new ListSort().DTOIntegerDesc();
    }
}

class Data{
    String name;
    String cpu;

    int age;

    public Data(String name, String cpu, int age) {
        this.name = name;
        this.cpu = cpu;
        this.age = age;
    }

    public Data(String name, String cpu) {
        this.name = name;
        this.cpu = cpu;

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    @Override
    public String toString() {
        return "Data{" +
                "name='" + name + '\'' +
                ", cpu='" + cpu + '\'' +
                ", age=" + age +
                '}';
    }
}
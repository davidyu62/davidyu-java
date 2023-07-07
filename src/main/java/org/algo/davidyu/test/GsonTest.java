package org.algo.davidyu.test;

import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GsonTest {
    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList<>();
        list.add("127.0.0.1:5000/front");
        list.add("127.0.0.1:8081/front");
        list.add("127.0.0.1:5000/auth");
        list.add("127.0.0.1:5002/auth");
        list.add("127.0.0.1:8082/auth");
        list.add("127.0.0.1:5000/notice");
        list.add("127.0.0.1:5003/notice");
        list.add("127.0.0.1:8083/notice");

//        list.stream().forEach(str ->{
//
//        });

        Gson gson = new Gson();

        File file = new File(".//1.json");
        if(file.exists()){
            file.delete();
        }
        file.createNewFile();

        for(int i=0; i<list.size(); i++){
            String str = list.get(i);
            if(i == 1){             // 첫 요청
                Services services = new Services();
                services.init();
                services.setTarget(str);
                String serviceJson = gson.toJson(services);
                Files.write(Paths.get(".//1.json"), serviceJson.getBytes());
                System.out.println(services);
            }else if(i == 2){
                Writer writer = new FileWriter(".//1.json");
                Reader reader = new FileReader(".//1.json");
                Services services = gson.fromJson(reader,Services.class);
//                Services serviceArr[] = services.getServices();
                System.out.println("services::"+services);
                services.getServices().get(0).setTarget(str);
                gson.toJson(services,writer);
            }else {

            }
        }

    }
}

class Services{
    private String target;
    private List<Services> services;

    public void init(){
        services = new ArrayList<>();
        services.add(new Services());
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "{" +
                "target='" + target + '\'' +
                ", services=" + services +
                '}';
    }

    public List<Services> getServices() {
        return services;
    }

    public void setServices(List<Services> services) {
        this.services = services;
    }
}

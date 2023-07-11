package org.algo.davidyu.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        File file = new File("C:\\workspace\\davidyu-java\\files\\gson\\1.json");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();

        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            String path = str.split("/")[1];
            if (i == 0) {             // 첫 요청
                Services services = new Services();
                services.setTarget(str);
                String serviceJson = gson.toJson(services);
//                System.out.println(gson.toJson(services));
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                bw.write(serviceJson);
                bw.close();
            } else if (i == 1) {
                List<String> serviceStrs = Files.readAllLines(Paths.get("C:\\workspace\\davidyu-java\\files\\gson\\1.json"));
                String serviceJsonStrs = "";
                for (String strs : serviceStrs) {
                    serviceJsonStrs += strs;
                }

                Services services = gson.fromJson(serviceJsonStrs, Services.class);
                if (services.getServices() == null) {
                    services.init();
                    Services newServices = new Services();
                    newServices.setTarget(str);
//                    newServices.init();
                    services.getServices().add(newServices);
                }
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                bw.write(gson.toJson(services));
                bw.close();
            } else {             // 서비스
                // read file
                List<String> serviceStrs = Files.readAllLines(Paths.get("C:\\workspace\\davidyu-java\\files\\gson\\1.json"));
                String serviceJsonStrs = "";
                for (String strs : serviceStrs) {
                    serviceJsonStrs += strs;
                }

                Services services = gson.fromJson(serviceJsonStrs, Services.class);
//                System.out.println(gson.toJson(services));
                Services tmpServices = services.getServices().get(0);
                List<Services> servicesList = tmpServices.getServices();
                if (servicesList == null) {       // 비어 있을 경우
                    tmpServices.init();
                    Services newService = new Services();
                    newService.setTarget(str);
                    tmpServices.getServices().add(newService);
                    ;
                } else {
                    for (int j = 0; j < servicesList.size(); j++) {
                        if (servicesList.get(j).getTarget().contains(path)) {
                            Services services1 = servicesList.get(j);
                            while (true) {
                                if (services1.getServices() == null) {        // 하위에 입력
                                    services1.init();
                                    Services newService = new Services();
                                    newService.setTarget(str);
                                    services1.getServices().add(newService);
                                    break;
                                } else {
                                    services1 = services1.getServices().get(0);
                                }
                            }
                        } else {
                            if (j == servicesList.size() - 1) {
                                Services newService = new Services();
                                newService.setTarget(str);
//                                newService.init();
                                servicesList.add(newService);
                            }
                        }
                    }
                }
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                bw.write(gson.toJson(services));
                bw.close();
            }
        }
    }

//    }public static void main(String[] args) throws IOException {
//        List<String> list = new ArrayList<>();
//        list.add("127.0.0.1:5000/front");
//        list.add("127.0.0.1:8081/front");
//        list.add("127.0.0.1:5000/auth");
//        list.add("127.0.0.1:5002/auth");
//        list.add("127.0.0.1:8082/auth");
//        list.add("127.0.0.1:5000/notice");
//        list.add("127.0.0.1:5003/notice");
//        list.add("127.0.0.1:8083/notice");
//
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//        File file = new File("C:\\workspace\\davidyu-java\\files\\gson\\1.json");
//        if(file.exists()){
//            file.delete();
//        }
//        file.createNewFile();
//
//        for(int i=0; i<list.size(); i++){
//            String str = list.get(i);
//            String path = str.split("/")[1];
//            if(i == 0){             // 첫 요청
//                Services services = new Services();
//                services.init();
//                services.setTarget(str);
//                String serviceJson = gson.toJson(services);
////                System.out.println(gson.toJson(services));
//                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
//                bw.write(serviceJson);
//                bw.close();
//            }else if(i == 1){
//                List<String> serviceStrs = Files.readAllLines(Paths.get("C:\\workspace\\davidyu-java\\files\\gson\\1.json"));
//                String serviceJsonStrs = "";
//                for(String strs : serviceStrs) {
//                    serviceJsonStrs += strs;
//                }
//
//                Services services = gson.fromJson(serviceJsonStrs,Services.class);
//                if(services.getServices().size() == 0){
//                    Services newServices = new Services();
//                    newServices.setTarget(str);
//                    newServices.init();
//                    services.getServices().add(newServices);
//                }
//                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
//                bw.write(gson.toJson(services));
//                bw.close();
//            }else {             // 서비스
//                // read file
//                List<String> serviceStrs = Files.readAllLines(Paths.get("C:\\workspace\\davidyu-java\\files\\gson\\1.json"));
//                String serviceJsonStrs = "";
//                for(String strs : serviceStrs) {
//                    serviceJsonStrs += strs;
//                }
//
//                Services services = gson.fromJson(serviceJsonStrs,Services.class);
////                System.out.println(gson.toJson(services));
//                Services tmpServices = services.getServices().get(0);
//                List<Services> servicesList = tmpServices.getServices();
//                if(servicesList.size() == 0){       // 비어 있을 경우
//                    Services newService = new Services();
//                    newService.setTarget(str);
//                    newService.init();
//                    servicesList.add(newService);;
//                }else{
//                    for(int j=0; j<servicesList.size(); j++){
//                        if(servicesList.get(j).getTarget().contains(path)){
//                            Services services1 = servicesList.get(j);
//                            while(true){
//                                if(services1.getServices().size() == 0){        // 하위에 입력
//                                    Services newService = new Services();
//                                    newService.setTarget(str);
//                                    newService.init();
//                                    services1.getServices().add(newService);
//                                    break;
//                                }else{
//                                    services1 = services1.getServices().get(0);
//                                }
//                            }
//                        }else{
//                            if(j == servicesList.size() -1){
//                                Services newService = new Services();
//                                newService.setTarget(str);
//                                newService.init();
//                                servicesList.add(newService);
//                            }
//                        }
//                    }
//                }
//                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
//                bw.write(gson.toJson(services));
//                bw.close();
//            }
//        }
//
//    }
}

class Services{
    private String target;
    private List<Services> services;

    public void init(){
        services = new ArrayList<>();
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<Services> getServices() {
        return services;
    }

    public void setServices(List<Services> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "Services{" +
                "target='" + target + '\'' +
                ", services=" + services +
                '}';
    }
}

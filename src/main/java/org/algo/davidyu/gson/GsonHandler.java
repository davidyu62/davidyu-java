package org.algo.davidyu.gson;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class GsonHandler {

    Gson gson;

    public void createGson(){
        // new
        gson = new Gson();
//      gson = new GsonBuilder().create();
//      gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void createJson(){
        createGson();

        //Json Key,Value
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name","davidyu");
        jsonObject.addProperty("id","ygygood");

        jsonToString(jsonObject);
        objectToJson();
        stringToJson();
        mapToJson();
    }

    public void jsonToString(JsonObject jsonObject){
        String jsonStr = gson.toJson(jsonObject);
        System.out.println("jsonStr:"+jsonStr);
    }

    public void objectToJson(){
        People people = new People("davidyu",36);
        String jsonStr = gson.toJson(people);
        System.out.println("jsonStr:"+jsonStr);
    }

    public void stringToJson(){ 
        String jsonStr = "{\"name\":ygygood,\"age\":\"33\"}";
        People people = gson.fromJson(jsonStr, People.class);
        System.out.println(people);
    }

    public void mapToJson(){
        Map<String,String> map = new HashMap<>();
        map.put("name","minji");
        map.put("age","33");
        String jsonStr = gson.toJson(map);
        System.out.println("Map jsonStr:"+jsonStr);
    }

    public void gsonHandleWithFile(){
        String fileName= "";
    }

    public void serialization(){
//        JsonElement jsonElement = JsonParser.parseString("{\"key\":\"value\"}");
    }
}

class People{
    private String name;     // Uppercase, Lowercase check
    private int age;
    public People(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String toString(){
        return "name:"+name+",age:"+age;
    }
}
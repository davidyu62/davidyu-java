package org.algo.davidyu.gson;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public static void jsonParserTest(){
        Gson gson = new Gson();
        try {
            String jsonStr = new String(Files.readAllBytes(Paths.get("C:\\workspace\\davidyu-java\\files\\gson\\company.json")));
            JsonObject jsonObject = gson.fromJson(jsonStr,JsonObject.class);
            System.out.println("jsonObject:"+jsonObject.toString());
            for(String key : jsonObject.keySet()){
//                System.out.print("key : " + key + " / value : " );
                JsonElement jsonElement = jsonObject.get(key);
                if(jsonElement.isJsonPrimitive()){
                    if (jsonElement.getAsJsonPrimitive().isString()) {
                        System.out.println("String");
                    }else if (jsonElement.getAsJsonPrimitive().isNumber()){
                        System.out.println("Number");
                    }
                    else if (jsonElement.getAsJsonPrimitive().isBoolean()) {
                        System.out.println("Boolean");
                    }
                    else if (jsonElement.getAsJsonPrimitive().isJsonNull()) {
                        System.out.println("null");
                    }
                }else if(jsonElement.isJsonArray()){
                    JsonArray jsonArray = jsonElement.getAsJsonArray();
                    for(int i=0; i<jsonArray.size(); i++){
                        System.out.println(jsonArray.get(i));
                    }
//                    System.out.println("Array");
                }else if(jsonElement.isJsonObject()){
//                    System.out.println("Object");
                }else if(jsonElement.isJsonNull()){
//                    System.out.println("null");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
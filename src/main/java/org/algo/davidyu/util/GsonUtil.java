package org.algo.davidyu.util;

import com.google.gson.Gson;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class GsonUtil {

    /**
     * {
     *   "name": "lg",
     *   "location": "",
     *   "employ": {
     *     "number": "3",
     *     "employee": ["davidyu", "mjkim"]
     *   }
     * }
     * */
    public static void readCompanyFile(){
        Gson gson = new Gson();
        String fileName = "C:\\workspace\\davidyu-java\\files\\gson\\company.json";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(fileName)));
            String line = "";
            String jsonStr = "";
            while((line = br.readLine()) != null){
                jsonStr += line;
            }
            Company company = gson.fromJson(jsonStr,Company.class);
            System.out.println(company);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally{
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * {
     *   "firstName": "John",
     *   "lastName": "Smith",
     *   "age": 25,
     *   "address": {
     *     "streetAddress": "21 2nd Street",
     *     "city": "New York",
     *     "state": "NY",
     *     "postalCode": "10021"
     *   },
     *   "phoneNumber": [
     *     {
     *       "type": "home",
     *       "number": "212 555-1234"
     *     },
     *     {
     *       "type": "fax",
     *       "number": "646 555-4567"
     *     }
     *   ]
     * }
     * */
    public static void readPeopleFile(){
        Gson gson = new Gson();
        String fileName = "C:\\workspace\\davidyu-java\\files\\gson\\people.json";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(fileName)));
            String line = "";
            String jsonStr = "";
            while((line = br.readLine()) != null){
                jsonStr += line;
            }
            People people = gson.fromJson(jsonStr,People.class);
            System.out.println(people);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally{
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void readTmpFile(){
        Gson gson = new Gson();
        String fileName = "C:\\workspace\\davidyu-java\\files\\gson\\tmp.json";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(fileName)));
            String line = "";
            String jsonStr = "";
            while((line = br.readLine()) != null){
                jsonStr += line;
            }
            Services services = gson.fromJson(jsonStr,Services.class);
            System.out.println(services);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally{
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args){
//        GsonUtil.readCompanyFile();
//        GsonUtil.readPeopleFile();
        GsonUtil.readTmpFile();
    }
}

class Company{
    String name;
    String location;
    Employ employ;

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", employ=" + employ +
                '}';
    }
}

class Employ{
    String number;
    String[] employee;

    @Override
    public String toString() {
        return "Employ{" +
                "number='" + number + '\'' +
                ", employee=" + Arrays.toString(employee) +
                '}';
    }
}

class People{
    String firstName;
    String lastName;
    String age;
    Address address;
    PhoneNumber[] phoneNumber;

    @Override
    public String toString() {
        return "People{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age='" + age + '\'' +
                ", address=" + address +
                ", phoneNumber=" + Arrays.toString(phoneNumber) +
                '}';
    }
}

class Address{
    String streetAddress;
    String city;
    String state;
    String postalCode;

    @Override
    public String toString() {
        return "Address{" +
                "streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}

class PhoneNumber{
    String type;
    String number;

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "type='" + type + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}

class Services {
    String target;
    String status;
    List<Services> services;
    public String getTarget() {
        return target;
    }
    public void setTarget(String target) {
        this.target = target;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
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
                ", status='" + status + '\'' +
                ", services=" + services +
                '}';
    }
}
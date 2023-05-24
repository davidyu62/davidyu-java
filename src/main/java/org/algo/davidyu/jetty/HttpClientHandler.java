package org.algo.davidyu.jetty;

import com.google.gson.Gson;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.api.Result;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpMethod;

public class HttpClientHandler {

    public void start(){
        HttpClient httpClient = new HttpClient();
        String inputQueueURI = "http://127.0.0.1:1234/input";
        while(true) {
            try {
                httpClient.start();
                ContentResponse contentRes =
                        httpClient.newRequest(inputQueueURI).method(HttpMethod.GET).send();
                Gson gson = new Gson();
                Response response = gson.fromJson(contentRes.getContentAsString(), Response.class);         // 데이터를 읽어서 Response 객체에 담는다.
                String id = response.getId();
                String text = "";

                if(response != null) {
                    ResultData result = new ResultData();
                    result.setResult(text);
                    Request request = httpClient.newRequest("127.0.0.1:1234/output").method(HttpMethod.POST);
//                    httpClient.newRequest(nextURL).header("x-requestId", xRequestId).method(HttpMethod.GET).send();   // to add header
                    request.header(HttpHeader.CONTENT_TYPE, "application/json");
                    request.content(new StringContentProvider(new Gson().toJson(result),"utf-8"));
                    ContentResponse res = request.send();
                }
                httpClient.stop();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args){

    }
}

class Response{
    String id;
    String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class ResultData{
    String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

package org.algo.davidyu.jetty;

import com.google.gson.Gson;
import org.eclipse.jetty.http.HttpHeader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

public class MyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        //Request
        String requestURL = req.getRequestURL().toString();                     // request URL
        String contextPath = req.getContextPath();                              // context path
        String requestURI = req.getRequestURI();                                // request URI
        req.getHeader("");                                                // get Header

        Map<String,String[]> map = req.getParameterMap();                       // request parameter
        Iterator iter = map.keySet().iterator();

        // Response
        ResponseData responseData = new ResponseData("SUCCESS");
        Gson gson = new Gson();
        res.setStatus(200);
        res.setHeader("Content-Type", "application/json");
        // HttpHeader에 setHeader에 필요한 name 값 포함하고 있음
        res.getWriter().write("Hello!");
        res.getWriter().write(gson.toJson(responseData));
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String result = "";
        String command = req.getRequestURI().toString().split("/")[1];
        String queueName = req.getRequestURI().toString().split("/")[2];

        String bodyStr = readBody(req);
        Gson gson = new Gson();
        RequestData requestData = gson.fromJson(bodyStr, RequestData.class);
    }

    // allow the both request get and post.
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        /* Read Header */
        Enumeration headerNames = req.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String name = (String)headerNames.nextElement();
            String value = req.getHeader(name);
            System.out.println(name + " : " + value + "<br>");
            /**
            * User-Agent : PostmanRuntime/7.29.0<br>
             * Connection : keep-alive<br>
             * Postman-Token : 071c2426-0eda-4199-999c-fd362948f35f<br>
             * Host : localhost:8080<br>
             * Accept-Encoding : gzip, deflate, br<br>
             * Accept :*   /   *  <br >
            * */
        }

        System.out.println(req.getMethod());

        res.setStatus(200);
        res.setHeader("Content-Type", "application/json");
        // HttpHeader에 setHeader에 필요한 name 값 포함하고 있음
        res.getWriter().write("Hello! service");
    }

    public String readBody(HttpServletRequest req){
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream;
        Gson gson = new Gson();
        String body = null;
        RequestData requestBody = null;

        try {
            // extract body
            inputStream = req.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
            body = stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return body;
    }

}

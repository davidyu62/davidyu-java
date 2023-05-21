package org.algo.davidyu.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

public class JettyServer {

    private Server server;
    private ServerConnector httpConnector;
    private ServerConnector httpConnector2;
    private ServletHandler servletHandler;

    public void startUp(){
        server = new Server();

        // create connector
        httpConnector = new ServerConnector(server);
        httpConnector.setHost("127.0.0.1");
        httpConnector.setPort(8080);

        // add if want to listen another port.
        httpConnector2 = new ServerConnector(server);
        httpConnector2.setHost("127.0.0.1");
        httpConnector2.setPort(8081);

        // add connector to server
        server.addConnector(httpConnector);
        server.addConnector(httpConnector2);

        // 2. Servlet Handler
        servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(MyServlet.class, "/mypath");
        server.setHandler(servletHandler);

        // 3. Web Server start
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){

    }
}

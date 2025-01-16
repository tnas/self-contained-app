package io.github.tnas.webapp;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class SimpleWebApp {

    public static void main(String[] args) throws LifecycleException {

        var tomcat = new Tomcat();
        tomcat.setBaseDir("temp");
        tomcat.setPort(8080);
        tomcat.getConnector();

        String contextPath = "/";
        String docBase = new File(".").getAbsolutePath();

        var context = tomcat.addContext(contextPath, docBase);

        Servlet servlet = new HttpServlet() {

            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

                try (PrintWriter writer = resp.getWriter()) {
                    writer.println("<html><title>Welcome</title><body>");
                    writer.println("<h1>Have a Great Day!</h1>");
                    writer.println("</body></html>");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        String servletName = "Servlet1";
        String urlPattern = "/go";

        tomcat.addServlet(contextPath, servletName, servlet);
        context.addServletMappingDecoded(urlPattern, servletName);

        tomcat.start();
        tomcat.getServer().await();
    }
}

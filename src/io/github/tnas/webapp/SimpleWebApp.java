package io.github.tnas.webapp;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class SimpleWebApp {

    public static void main(String[] args) throws LifecycleException {

        var tomcat = new Tomcat();
        tomcat.setBaseDir("temp");
        tomcat.setPort(8080);
        tomcat.getConnector();

        var contextPath = "";
        var docBase = new File(".").getAbsolutePath();

        var context = tomcat.addContext(contextPath, docBase);

        var servlet = new HttpServlet() {

			private static final long serialVersionUID = 1L;

			@Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

                try (var writer = resp.getWriter()) {
                    writer.println("<html><title>Welcome</title><body>");
                    writer.println("<h1>Have a Great Day!</h1>");
                    writer.println("</body></html>");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        var servletName = "Servlet1";
        var urlPattern = "/go";

        tomcat.addServlet(contextPath, servletName, servlet);
        context.addServletMappingDecoded(urlPattern, servletName);

        var webappDirLocation = "webapp"; 
        tomcat.addWebapp("/app", new File(webappDirLocation).getAbsolutePath());
        
        tomcat.start();
        tomcat.getServer().await();
    }
}

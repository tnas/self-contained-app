package io.github.tnas.app;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class Application {

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
                    writer.println("<h1>Bem vindos &agrave; etapa pr&aacute;tica da <b>prova did&aacute;tica</b>!</h1>");
                    writer.println("<h2><ul><li><a href='http://localhost:8080/prova' target='__blank'>Prova Did&aacute;tica</li></ul></h2>");
                    writer.println("</body></html>");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        var servletName = "OlaServlet";
        var urlPattern = "/ola";
        tomcat.addServlet(contextPath, servletName, servlet);
        context.addServletMappingDecoded(urlPattern, servletName);

        tomcat.addWebapp("/prova", new File("webapp/prova").getAbsolutePath());
        tomcat.addWebapp("/engsoftware", new File("webapp/engsoftware").getAbsolutePath());
        
        tomcat.start();
        tomcat.getServer().await();
    }
}

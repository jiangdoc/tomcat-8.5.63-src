package org.mini.tomcat.servlet;

import org.mini.tomcat.Request;
import org.mini.tomcat.Response;

public abstract class HttpServlet implements Servlet{

    public abstract void doGet(Request request, Response response);

    public abstract void doPost(Request request,Response response);


    @Override
    public void service(Request request, Response response) throws Exception {
        if("GET".equalsIgnoreCase(request.getMethod())) {
            doGet(request,response);
        }else{
            doPost(request,response);
        }
    }
}
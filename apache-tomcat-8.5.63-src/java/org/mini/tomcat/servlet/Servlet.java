package org.mini.tomcat.servlet;

import org.mini.tomcat.Request;
import org.mini.tomcat.Response;

public interface Servlet {

        void init() throws Exception;

        void destory() throws Exception;

        void service(Request request, Response response) throws Exception;
}
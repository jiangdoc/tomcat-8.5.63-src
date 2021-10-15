package org.mini.tomcat.servlet;

import org.mini.tomcat.Request;
import org.mini.tomcat.Response;
import org.mini.tomcat.utils.HttpProtocolUtil;

import java.io.IOException;

public class TestServlet extends HttpServlet {
    @Override
    public void doGet(Request request, Response response) {
        String content = "<h1>this is a servlet get</h1>";
        try {
            response.output((HttpProtocolUtil.getHttpHeader200(content.getBytes().length) + content));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(Request request, Response response) {
        doGet(request,response);
    }

    @Override
    public void init() throws Exception {

    }

    @Override
    public void destory() throws Exception {

    }
}
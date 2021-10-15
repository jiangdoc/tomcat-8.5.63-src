package org.mini.tomcat;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.mini.tomcat.utils.HttpProtocolUtil;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

/**
 * @author jiangwenjie
 * @date 2021/3/5
 */
public class BootStrap {

    private static final Log log = LogFactory.getLog(BootStrap.class);

    private Integer port;

    private ServerSocket serverSocket;

    public BootStrap(Integer port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        try {
            BootStrap bootstrap = new BootStrap(8080);
            bootstrap.init();
            bootstrap.load();
            bootstrap.start();
        }catch (Exception e){
            throw new Exception("bootStrap main exception!");
        }

    }

    public void init() throws Exception{
        try{
            serverSocket = new ServerSocket(port);
        }catch (Exception e){
            throw new Exception("new serverSocket exception!");
        }

    }
    private void load() {

    }
    public void start() throws Exception{
        if (Objects.isNull(serverSocket)){
            init();
        }
        while(true){
            final Socket accept = serverSocket.accept();
            dispatch(accept);
            close(accept);
        }


    }

    public void close(Socket accept) throws Exception{
        accept.close();
    }

    public void dispatch(Socket accept)throws Exception{
        // 判断是否是静态资源
        if (true) {

            // 封装 Request 对象和 Response 对象
            Request request = new Request(accept.getInputStream());

            //封装返回对象。
            Response response = new Response(accept.getOutputStream());

            //返回静态资源
            response.outputHtml(request.getUrl());
        }else{
            String data = "this is my tomcat!";
            final byte[] response = (HttpProtocolUtil.getHttpHeader200(data.length()) + data).getBytes();
            accept.getOutputStream().write(response);
        }
    }
}

package org.mini.tomcat;

import lombok.Data;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import java.io.InputStream;

/**
 * @author jiangwenjie
 * @date 2021/3/5
 */
@Data
public class Request {

    private static final Log log = LogFactory.getLog(Request.class);
    /**
     * 请求类型：POST,GET
     */
    private String method;
    /**
     * 请求url:/index/index.html
     */
    private String url;

    private InputStream inputStream;

    public Request(InputStream inputStream) throws Exception {
        this.inputStream = inputStream;
        // 解析输入流
        parseRequestStream();
    }

    private void parseRequestStream() throws Exception{
        try {
            // 从输入流中获取请求信息
            int count = 0;
            while (count == 0) {
                count = inputStream.available();
            }

            byte[] bytes = new byte[count];
            inputStream.read(bytes);

            String inputStr = new String(bytes);
            // 获取第一行请求头信息
            String firstLineStr = inputStr.split("\\n")[0];  // GET / HTTP/1.1

            String[] strings = firstLineStr.split(" ");

            this.method = strings[0];
            this.url = strings[1];

            log.info("=====>>method:" + method);
            log.info("=====>>url:" + url);
        }catch (Exception e){
            throw new Exception("parse stream exception:",e);
        }
    }
}

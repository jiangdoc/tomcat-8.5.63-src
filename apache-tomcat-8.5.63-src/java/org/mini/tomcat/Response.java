package org.mini.tomcat;

import lombok.Data;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.mini.tomcat.utils.HttpProtocolUtil;
import org.mini.tomcat.utils.StaticResourceUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author jiangwenjie
 * @date 2021/3/5
 */
@Data
public class Response {

    private static final Log log = LogFactory.getLog(Response.class);

    private OutputStream outputStream;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }


    // 使用输出流输出指定字符串
    public void output(String content) throws IOException {
        outputStream.write(content.getBytes());
    }



    /**
     *
     * @param path  url，随后要根据 url 来获取到静态资源的绝对路径，
     *进一步根据绝对路径读取该静态资源文件，最终通过输出流输出
     *
     */
    public void outputHtml(String path) throws IOException {
        // 获取静态资源文件的绝对路径
        String absoluteResourcePath = StaticResourceUtil.getAbsolutePath(path);
        log.info("html absolute path :"+absoluteResourcePath);

        // 输入静态资源文件
        File file = new File(absoluteResourcePath);
        if(file.exists() && file.isFile()) {
            // 读取静态资源文件，输出静态资源
            StaticResourceUtil.outputStaticResource(path,new FileInputStream(file),outputStream);
        }else{
            // 输出 404
            output(HttpProtocolUtil.getHttpHeader404());
        }

    }
}

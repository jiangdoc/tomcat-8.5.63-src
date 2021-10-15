## Welcome to Apache Tomcat!

## 启动参数
 ```
-Dcatalina.home=catalina-home
-Dcatalina.base=catalina-home
-Djava.endorsed.dirs=catalina-home/endorsed
-Djava.io.tmpdir=catalina-home/temp
-Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager
-Djava.util.logging.config.file=catalina-home/conf/logging.properties
-Duser.language=en
```
## 看源码要带着问题
1. tomcat是怎么启动web项目的？
2. 一个网络请求是怎么完成的？
3. tomcat如何创建一个容器的？
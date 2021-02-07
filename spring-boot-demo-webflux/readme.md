spring-boot-demo-webflux 主要演示了 webflux 的基础练习。
________________________________________________
1、修改 pom.xml , 引入 webflux 依赖
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-webflux</artifactId>
    </dependency>
    · webflux 的包，里面默认包含了 spring-boot-starter-reactor-netty 、spring 5 webflux 包。
      也就是说默认是通过 netty 启动的。
2、编写处理器类 Handler 和 路由器类 Router
    CityHandler 和 CityRouter
    测试：浏览器访问 http://localhost:8130/hello 返回 Hello, City!  
3、application.yml 中配置 server.servlet.context-path: /webflux
    测试：浏览器访问 http://localhost:8130/webflux/hello 返回【错误】Whitelabel Error Page
    原因：webflux 因为没有 DispatchServlet，已经不支持 ContextPath 了
    解决：通过 ContextPathFilter 解决实现。但是【不推荐使用】。【推荐使用 nginx】 。
         参考：https://blog.csdn.net/u010390677/article/details/86668479
         论坛上还有其他解决方案：spring boot v2.3版本，配置 spring.webflux.base-path=/your-path
    测试：浏览器访问 http://localhost:8130/webflux/hello 返回 Hello, City!  
    还有各种解决启动容器的解决方案：https://stackoverflow.com/questions/49196368/context-path-with-webflux  
4、按照 SpringMVC 方式编写测试 Controller
    创建 HelloController.java 文件实现测试类的编写。具体请查阅。
    4.1).创建 mono() 方法:
        测试：浏览器访问 http://localhost:8130/webflux/api/mono 返回 hello webflux
    4.2).创建 mono1() 方法:
        测试：浏览器访问 http://localhost:8130/webflux/api/mono1 返回 "hello webflux"
    4.3).创建 flux() 方法:
        测试：浏览器访问 http://localhost:8130/webflux/api/flux 返回 hello webflux springboot
    4.4).创建 flux_data() 方法: 实现服务器推送（实际测试没有这个效果）
        测试：浏览器访问 http://localhost:8130/webflux/api/flux_data
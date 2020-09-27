本 demo 主要演示如何使用 aop 切面对请求进行日志记录，并且记录 UserAgent 信息。  
#-
模块开发流程：  
1、在 pom.xml 中引入相关依赖  
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!-- aop 依赖 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
        </dependency>
        <!-- 解析 UserAgent 信息 -->
        <dependency>
            <groupId>eu.bitwalker</groupId>
            <artifactId>UserAgentUtils</artifactId>
        </dependency>
    </dependencies>
2、创建配置文件 application.yml : 设置端口号 8108 和 logback    
3、创建 logback 文件 : 本次使用不做复杂配置，如果需要复杂配置可以参考 hello-spring-boot.git  
4、创建启动类 DemoLogAopApplication  
5、在 aspectj 文件夹下创建切面类 AopLog  
    切入点 : log() ; 前置操作 : beforeLog() ; 环绕操作 : aroundLog ; 后置操作 : afterReturning
6、在 controller 文件夹下创建测试类 TestController 用于测试  
7、启动本模块进行测试  
    打开浏览器：http://127.0.0.1:8108/demo/test
    查看控制台日志：
    2020-09-27 17:16:48,194 [http-nio-8108-exec-3] INFO  [com.yuansb.demo.log.aop.aspectj.AopLog] AopLog.java:45 - 【请求 URL】：http://127.0.0.1:8108/demo/test  
    2020-09-27 17:16:48,207 [http-nio-8108-exec-3] INFO  [com.yuansb.demo.log.aop.aspectj.AopLog] AopLog.java:46 - 【请求 IP】：127.0.0.1  
    2020-09-27 17:16:48,209 [http-nio-8108-exec-3] INFO  [com.yuansb.demo.log.aop.aspectj.AopLog] AopLog.java:47 - 【请求类名】：com.yuansb.demo.log.aop.controller.TestController，【请求方法名】：test  
    2020-09-27 17:16:48,216 [http-nio-8108-exec-3] INFO  [com.yuansb.demo.log.aop.aspectj.AopLog] AopLog.java:50 - 【请求参数】：{}，  
    2020-09-27 17:16:48,235 [http-nio-8108-exec-3] INFO  [com.yuansb.demo.log.aop.aspectj.AopLog] AopLog.java:64 - 【返回值】：{"who":"me"}  
    2020-09-27 17:16:48,235 [http-nio-8108-exec-3] INFO  [com.yuansb.demo.log.aop.aspectj.AopLog] AopLog.java:79 - 【请求耗时】：19毫秒  
    2020-09-27 17:16:48,249 [http-nio-8108-exec-3] INFO  [com.yuansb.demo.log.aop.aspectj.AopLog] AopLog.java:83 - 【浏览器类型】：CHROME，【操作系统】：WINDOWS_10，【原始User-Agent】：Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36  

    
模块开发流程：  
1、在 pom.xml 中引入依赖包  
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!-- actuator 监控系统健康情况的工具 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!-- security 默认会提供一个基于 HTTP Basic 认证的安全防护策略 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>  
2、创建配置文件 application.yml  
    server:
      port: 8104
      servlet:
        context-path: /demo
    # 若要访问端点信息，需要配置用户名和密码
    spring:
      security:
        user:
          name: yuansb
          password: yuansb@123
    management:
      # 端点信息接口使用的端口，为了和主系统接口使用的端口进行分离
      server:
        port: 8304
        servlet:
          context-path: /sys
      # 端点健康情况，默认值"never"，设置为"always"可以显示硬盘使用情况和线程情况
      endpoint:
        health:
          show-details: always
      # 设置端点暴露的哪些内容，默认["health","info"]，设置"*"代表暴露所有可访问的端点
      endpoints:
        web:
          exposure:
            include: '*'
3、创建启动类 DemoActuatorApplication
4、启动工程进行测试，打开浏览器进行访问    
    未登录需要先登录：yuansb/yuansb@123 (根据yml文件配置)  
    01).url : http://localhost:8304/sys/actuator/health  
    02).url : http://localhost:8304/sys/actuator/info  
    03).url : http://localhost:8304/sys/actuator/configprops  
5、自定义健康指标 CustomHealthIndicator

6.修改 application.yml ，配置 info 信息
    # INFO ENDPOINT CONFIGURATION
    info:
      app:
        name: @project.name@
        description: @project.description@
        version: @project.version@
        encoding: @project.build.sourceEncoding@
        java:
          version: @java.version@
---------------------------------------------
Spring Boot Actuator:健康检查、审计、统计和监控  
1、actuator 的端点分为3类
    01).应用配置类  
        /configprops /autoconfig /beans /env /info /mappings  
    02).应用配置类  
        /dump /health
    03).应用配置类  
        /shutdown
2、actuator 的端点常用解释
    /autoconfig ： 自动化配置报告,可以看出一些自动化配置为什么没有生效，是application.properties的信息更多  
    /beans ： 可以看到定义的所有的bean  
    /configprops ： 可以看到application.properties里面的信息
    /env ： 看到的更侧重硬件和JVM环境方面的配置信息  
    /mappings ： 返回控制器映射报告信息，有哪些请求  
    /info ： 返回application.properties文件中info开头的配置信息  
    /metrics ： 应用的重要信息：内存，堆，CPU核数等（度量指标类）  
    /health ： 实现健康检查,返回值的状态信息
    /dump ： 返回活动线程的信息  
        调用 java.lang.management.ThreadMXBean的 public ThreadInfo[] dumpAllThreads(boolean lockedMonitors, boolean lockedSynchronizers);  
    /shutdown ： 通过在 application.properties 文件中添加 endpoints.shutdown.enabled=true
---------------------------------------------
https://www.jianshu.com/p/481134c3fab7  
https://www.jianshu.com/p/d5943e303a1f  -- 查看该文件进行优化  


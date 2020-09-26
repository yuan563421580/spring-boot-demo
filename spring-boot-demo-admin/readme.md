本 demo 主要演示了 Spring Boot 如何集成 Admin 管控台，监控管理 Spring Boot 应用，
分别为 admin 服务端和 admin 客户端，两个模块。  
------------------------------------------------------------------------------  
运行步骤
1、进入 `spring-boot-demo-admin-server` 服务端，启动管控台服务端程序  
2、进入 `spring-boot-demo-admin-client` 客户端，启动客户端程序，注册到服务端  
3、观察服务端里，客户端程序的运行状态等信息  
    打开浏览器访问：http://127.0.0.1:8106
------------------------------------------------------------------------------  
升级版开发：
Spring Boot Admin 2.1.0 全攻略 - Spring boot Admin结合SC注册中心使用
https://www.fangzhipeng.com/springcloud/2019/01/04/sc-f-boot-admin.html
https://blog.csdn.net/forezp/article/details/86105850
请尽快搭建完成，需要新创建模块 spring-boot-demo-admin-sc 
------------------------------------------------------------------------------  
模块开发流程：  
1、修改 pom.xml
    修改成 pom ： <packaging>pom</packaging>
    引入依赖 ： spring-boot-admin-dependencies
        <dependencyManagement>
            <dependencies>
                <dependency>
                    <groupId>de.codecentric</groupId>
                    <artifactId>spring-boot-admin-dependencies</artifactId>
                    <version>${spring-boot-admin.version}</version>
                    <type>pom</type>
                    <scope>import</scope>
                </dependency>
            </dependencies>
        </dependencyManagement>
2、创建服务端 spring-boot-demo-admin-server
    2.1).在 pom.xml 中引入依赖  
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
            </dependency>
            <!-- admin-server 依赖 -->
            <dependency>
                <groupId>de.codecentric</groupId>
                <artifactId>spring-boot-admin-starter-server</artifactId>
            </dependency>
        </dependencies>
    2.2).创建配置文件 application.yml ： 设定端口号  
    2.3).创建启动类 DemoAdminServerApplication
        通过注解 @EnableAdminServer , 开启 AdminServer 的功能
3、创建客户端 spring-boot-demo-admin-client
    3.1).在 pom.xml 中引入依赖  
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
            </dependency>
            <!-- admin-client的依赖 -->
            <dependency>
                <groupId>de.codecentric</groupId>
                <artifactId>spring-boot-admin-starter-client</artifactId>
            </dependency>
            <!-- security 默认会提供一个基于 HTTP Basic 认证的安全防护策略 -->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-security</artifactId>
            </dependency>
        </dependencies>
        -说明：security 依赖为配合使用，可以不添加该依赖
    3.2).创建配置文件 application.yml  
        server:
          port: 8107
          servlet:
            context-path: /demo
        spring:
          application:
            # Spring Boot Admin展示的客户端项目名，不设置，会使用自动生成的随机id
            name: admin-client
          boot:
            admin:
              client:
                # Spring Boot Admin 服务端地址
                url: http://localhost:8106
                instance:
                  metadata:
                    # 客户端端点信息的安全认证信息
                    user.name: ${spring.security.user.name}
                    user.password: ${spring.security.user.password}
          security:
            user:
              name: yuansb
              password: yuansb@123
        management:
          endpoint:
            health:
              # 端点健康情况，默认值"never"，设置为"always"可以显示硬盘使用情况和线程情况
              show-details: always
          endpoints:
            web:
              exposure:
                # 设置端点暴露的哪些内容，默认["health","info"]，设置"*"代表暴露所有可访问的端点
                include: "*"
    3.3).创建启动类 DemoAdminClientApplication
    3.4).创建首页入口 IndexController ， 可以不创建
    
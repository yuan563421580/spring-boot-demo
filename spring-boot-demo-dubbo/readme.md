·此 demo 主要演示了 Spring Boot 如何集成 Dubbo，demo 分了3个module，
    公共模块 spring-boot-demo-dubbo-common 、
    服务提供方 spring-boot-demo-dubbo-provider 、
    服务调用方 spring-boot-demo-dubbo-consumer 。  
·采用docker方式运行 zookeeper 。
·运行步骤
    进入服务提供方 spring-boot-demo-dubbo-provider 目录，运行 DemoDubboProviderApplication.java
    进入服务调用方 spring-boot-demo-dubbo-consumer 目录，运行 DemoDubboConsumerApplication.java
    打开浏览器输入 http:127.0.0.1:8118/demo-consumer/sayHello ，观察浏览器输出，以及服务提供方和服务调用方的控制台输出日志情况
·参考：阿里云_CentOS7_相关docker按包装Zookeeper操作及后续查询操作记录.docx
________________________________________________
一、先建立一个初始的多模块的父类工程项目
    01)、修改 pom.xml ： 设置成 <packaging>pom</packaging>  
二、创建公共模块 spring-boot-demo-dubbo-common  
    此 module 主要是用于公共部分，主要存放工具类，实体，以及服务提供方/调用方的接口定义。    
    完成后可以先 install 后进行第三步。需要将该模块按照 jar 形式打包（引入）其他模块。  
    01)、文件夹 common.service 下创建 HelloService  
三、创建服务提供方模块 spring-boot-demo-dubbo-provider  
    此 module 主要是服务提供方示例。    
    01)、修改 pom.xml：引入相关依赖：  
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
            </dependency>
            <!-- 引入 dubbo 依赖 -->
            <dependency>
                <groupId>com.alibaba.spring.boot</groupId>
                <artifactId>dubbo-spring-boot-starter</artifactId>
                <version>${dubbo.starter.version}</version>
            </dependency>
            <!-- 引入 common 模块 -->
            <dependency>
                <groupId>${project.parent.groupId}</groupId>
                <artifactId>spring-boot-demo-dubbo-common</artifactId>
                <version>${project.parent.version}</version>
            </dependency>
            <!-- 引入zookeeper 依赖 -->
            <dependency>
                <groupId>com.101tec</groupId>
                <artifactId>zkclient</artifactId>
                <version>${zkclient.version}</version>
            </dependency>
            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <optional>true</optional>
            </dependency>
        </dependencies>
    02)、创建 application.yml
        配置端口信息 8118 和 dubbo 相关信息
    03)、创建启动类 DemoProviderApplication  
        引入注解：spring boot 整合 dubbo 专属的 @EnableDubboConfiguration 注解提供的 dubbo 自动配置。  
四、创建服务调用方模块 spring-boot-demo-dubbo-consumer  
    此 module 主要是服务调用方的示例。  
    01)、修改 pom.xml：引入相关依赖：  
    02)、创建 application.yml 端口信息 8119
    03)、创建启动类 DemoConsumerApplication  
    04)、创建远程调用服务测试类 HelloController  
五、进行工程测试
    01)、先启动服务提供方 spring-boot-demo-dubbo-provider
    02)、再启动服务调用方 spring-boot-demo-dubbo-consumer
    03)、浏览器访问： http:127.0.0.1:8119/demo-consumer/sayHello
        返回结果：say hello to: yuansb
        控制台日志：2020-10-19 15:53:02.688  INFO 10384 --- [nio-8119-exec-1] c.y.d.d.c.controller.HelloController     : i'm ready to call someone......
                   2020-10-19 15:53:02.690  INFO 13260 --- [:20880-thread-7] c.y.d.d.p.service.HelloServiceImpl       : someone is calling me......
六、特殊说明：
    安装 zookeeper 和 查看注册，详见其他文档  
    阿里云_CentOS7_相关docker按包装Zookeeper操作及后续查询操作记录.docx  

    
    
    
    
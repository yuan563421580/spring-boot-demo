此 demo 主要演示了如何使用 Spring Boot 集成 Zookeeper 结合 AOP 实现分布式锁。
本 demo 比较生疏，采用反向 debug 方式方式进行学习。
________________________________________________
1、修改 pom.xml , 主要注意引入第三方 zookeeper  
    <dependency>
        <groupId>org.apache.curator</groupId>
        <artifactId>curator-recipes</artifactId>
        <version>4.1.0</version>
    </dependency>  
2、创建配置文件 application.yml  
    配置端口 8121 ， 配置 zk  
3、创建 config 文件夹，实现 Zookeeper 配置类，具体看文件说明  
4、创建 annotation 文件夹，实现注解，具体看文件说明    
5、创建 aspectj 文件夹，实现使用 aop 切面记录请求日志信息，具体看文件说明    
6、在 test 文件夹下实现 DemoZookeeperApplicationTests 中测试方法  
7、建议反向场景学习，看一下具体实现  
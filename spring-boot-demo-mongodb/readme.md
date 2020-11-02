此 demo 主要演示了如何使用 Spring Boot 集成 MongoDB，使用官方的 starter 实现增删改查。  
________________________________________________
1、修改 pom.xml , 引入 mongodb 依赖  
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-mongodb</artifactId>
    </dependency>
2、创建配置文件 application.yml  
    配置端口 8125 ， 配置 mongodb
3、编写相关代码，进行测试  
________________________________________________
问题：
1.错误：MongoSocketReadException: Exception receiving message
  解决：安装 mongodb , 将 hostIP 注释掉 或者 放开 0.0.0.0
2.错误：org.springframework.data.mongodb.UncategorizedMongoDbException: Command failed with error 13 (Unauthorized): 
'command update requires authentication' on server 139.129.100.87:27017. The full response is 
{ "ok" : 0.0, "errmsg" : "command update requires authentication", "code" : 13, "codeName" : "Unauthorized" }; 
  解决：配置文件中将 mongodb 中 username 和 password 配置在 uri 中


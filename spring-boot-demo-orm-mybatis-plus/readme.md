此 demo 演示了 Spring Boot 如何集成 mybatis-plus
________________________________________________

1、修改 pom.xml , 引入相关依赖
    <dependencies>
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.1.0</version>
        </dependency>
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-generator</artifactId>
            <version>3.1.0</version>
        </dependency>
        <dependency>
            <groupId>org.freemarker</groupId>
            <artifactId>freemarker</artifactId>
        </dependency>
    </dependencies>
    说明：MyBatis-Plus 从 3.0.3 之后移除了代码生成器与模板引擎的默认依赖，需要手动添加相关依赖：
         代码生成器：mybatis-plus-generator
         模板引擎依赖：freemarker  
2、创建配置文件 application.yml  
    配置端口、数据库连接、mybatis-plus 等相关配置  
3、创建配置文件夹 config ， 用于统一存放配置文件  
    3.1)、创建分页插件 MybatisPlusConfig  
        具体详见文件实现，固定写法不需要修改。  
    3.2)、创建代码生成器 MyBatisPlusCodeGenerator
        具体详见文件实现，固定写法只需要修改配置路径即可。  
4、修改测试类 OrmUserController 实现测试方法
5、启动程序进行测试  
    http:127.0.0.1:8113/demo/user/list
    http:127.0.0.1:8113/demo/user/page
    http:127.0.0.1:8113/demo/user/pageWrapper
    http:127.0.0.1:8113/demo/user/byId/3
    http:127.0.0.1:8113/demo/user/insert
    http:127.0.0.1:8113/demo/user/update  
    
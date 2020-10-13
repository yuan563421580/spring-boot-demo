此 demo 主要演示了 Spring Boot 如何集成原生 swagger ，自动生成 API 文档。
参考：https://www.cnblogs.com/yichunguo/p/12665857.html
     https://mp.weixin.qq.com/s/0-c0MAgtyOeKx6qzmdUG0w
________________________________________________

1、修改 pom.xml , 引入相关依赖  
    <dependencies>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>${swagger.version}</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>${swagger.version}</version>
        </dependency>
    </dependencies>
2、创建配置文件 application.yml 配置端口 8114    
3、创建启动类 DemoSwaggerApplication  
4、创建配置文件夹 config ， 用于统一存放配置文件  
    创建配置类 Swagger2Config 实现 Swagger2 配置，通过注解 @Configuration 和 @EnableSwagger2  
5、初步整合完毕，启动程序进行测试  
    http://127.0.0.1:8114/demo/swagger-ui.html 可以正常访问  
6、创建 common 文件夹，实现通用类编写  
    6.1)、通用API接口返回 ApiResponse  
    6.2)、通用 dataType 属性使用 DataType  
        方便在 @ApiImplicitParam 的 dataType 属性使用。    
    6.3)、通用 paramType 属性使用 ParamType  
        方便在 @ApiImplicitParam 的 paramType 属性使用。  
7、创建实体类 User （entity 文件夹下创建）  
8、创建用户测试类 UserController 实现对应的测试方法  
________________________________________________
swagger资料：
1. swagger 官方网站：https://swagger.io/  
2. swagger 官方文档：https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Getting-started  
3. swagger 常用注解：https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations  
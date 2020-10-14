此 demo 主要演示如何集成第三方的 swagger 来替换原生的 swagger，美化文档样式。本 demo 使用 swagger-spring-boot-starter 集成。
部分实体类可以参考模块 spring-boot-demo-swagger  
________________________________________________

1、修改 pom.xml , 引入相关依赖  
    <dependencies>
        <dependency>
            <groupId>com.battcn</groupId>
            <artifactId>swagger-spring-boot-starter</artifactId>
            <version>${battcn.swagger.version}</version>
        </dependency>
    </dependencies>
2、创建配置文件 application.yml 配置端口 8115  
    实现类似于 Swagger2Config 的配置相关  
3、创建启动类 DemoSwaggerBeautyApplication  
4、参考上一个模块 创建相关实体类、测试类  
5、启动工程进行本环节测试
    http://127.0.0.1:8115/demo/swagger-ui.html  

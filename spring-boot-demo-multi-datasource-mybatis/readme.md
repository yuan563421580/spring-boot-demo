Spring Boot 如何集成 Mybatis 的多数据源。可以自己基于AOP实现多数据源，
这里基于 Mybatis-Plus 提供的一个优雅的开源的解决方案来实现。   
________________________________________________
1、修改 pom.xml , 引入相关依赖  
    <dependencies>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>dynamic-datasource-spring-boot-starter</artifactId>
            <version>2.5.0</version>
        </dependency>
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.1.0</version>
        </dependency>
    </dependencies>
2、创建配置文件 application.yml , 配置多数据源
    spring:
      datasource:
        dynamic:
          datasource:
3、创建启动类 DemoMultiDatasourceMybatisApplication
4、编写业务相关
    01)、创建实体类 User ， 具体实现看实现类
    02)、创建数据访问层 UserMapper ， 具体实现看实现类
    03)、创建数据服务层 UserService
    04)、创建数据服务层 实现 UserServiceImpl ， 实现数据源切换
    05)、修改 启动类 ， 增加 @MapperScan
5、编写测试用例，进行测试
________________________________________________
主库 建议 只执行 INSERT UPDATE DELETE 操作。
从库 建议 只执行 SELECT 操作。
生产环境需要搭建 主从复制。
    


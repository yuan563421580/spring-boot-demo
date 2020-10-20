此 demo 主要演示了 Spring Boot 项目打包成传统的 war 包程序。
________________________________________________
1、修改 pom.xml , 初始按照正常设置。
2、创建配置文件 application.yml , 配置端口 8120  
3、创建启动类 DemoWarApplication 
4、修改 pom.xml , 适配打 war 包  
    4.1)、<packaging>war</packaging>
    4.2)、排除内置的tomcat  
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-tomcat</artifactId>
                </exclusion>
            </exclusions>
        </dependency>  
    4.3)、添加servlet-api的依赖  
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <scope>provided</scope>
        </dependency>
5、修改启动类 DemoWarApplication , 适配打 war 包  
    4.1)、类 DemoWarApplication 继承 SpringBootServletInitializer  
    4.2)、类 DemoWarApplication 重写 config 方法  
        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
            return builder.sources(DemoWarApplication.class);
        }  
6、打包查看 mvn install
    target 文件夹下 spring-boot-demo-install-war-0.0.1-SNAPSHOT.war
7、部署：直接放在 tomcat 目录下得 webapps 下，启动 tomcat 即可
8、使用 idea 配置 tomcat 启动
    Edit Configurations -> 点击[+]按钮 -> tomcat server -> local ->
    ·修改名称Name 、Application Server、端口号Http port 等信息
    ·选择 Deployment -> 点击[+]按钮 -> 选中需要的 war 包
    -> apply -> 启动 -> 观察日志 
    
    



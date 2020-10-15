本 demo 主要演示了如何容器化一个  Spring Boot 项目。通过 `Dockerfile` 的方式打包成一个 images 。
参考：https://www.cnblogs.com/zmsn/p/11697575.html
     https://blog.csdn.net/qr457535344/article/details/102944078
    -
    https://www.cnblogs.com/Howinfun/p/11658516.html
    https://blog.csdn.net/funtaster/article/details/83274727
    https://www.cnblogs.com/zmsn/p/11697575.html
    -
    https://blog.csdn.net/weixin_38705082/article/details/93890478?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.channel_param
________________________________________________

一、先建立一个初始的 web 工程项目
    01)、修改 pom.xml , 引入 web 依赖  
    02)、创建 application.yml , 配置端口 8080  
    03)、创建启动类 DemoDockerApplication  
    04)、创建测试类 HelloController  
    05)、启动工程访问测试 ： http://127.0.0.1:8080/demo/hello
二、手动打包方式（保证有一台 linux 主机，同时安装 docker）  
    01)、按照 doc 中 Dockerfile_Linux.txt 文档 拷贝内容创建 Dockerfile 文件 ， 用于上传 linux  
    02)、将工程 spring-boot-demo-docker 进行打包  
        在 Terminal 中输入命令 mvn clean package 进行打 jar 包，在 target 文件夹下查看  
            spring-boot-demo-docker.jar  
    03)、将 Dockerfile 和 spring-boot-demo-docker.jar 上传至 linux 中同一文件夹下  
    04)、使用命令 docker build -t spring-boot-demo-docker . 将jar打包为镜像
    05)、使用命令查看镜像 ： docker images 或 docker images | grep spring-boot-demo-docker  
    06)、使用命令启动容器：docker run -d -p 8080:8080 --name myDemoDocker spring-boot-demo-docker:latest
    07)、进行测试：http://112.126.62.163:8080/demo/hello  
三、自动打包方式测试超时，后续查询资料优化补充  
        
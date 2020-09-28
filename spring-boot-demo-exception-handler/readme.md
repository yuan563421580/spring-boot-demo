此 demo 演示了如何在Spring Boot中进行统一的异常处理，包括了两种方式的处理：
第一种对常见API形式的接口进行异常处理，统一封装返回格式；
第二种是对模板页面请求的异常处理，统一处理错误页面。
-----------------------------------------------------------------------
1、修改 pom.xml , 引入相关依赖
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
    </dependencies>
2、创建配置文件 application.yml 
    配置端口 、引入 thymeleaf
3、创建启动类 DemoExceptionHandlerApplication
4、创建枚举类 Status ： 实现状态码封装 （文件夹 constant 下创建）  
5、创建异常基类 BaseException 继承 RuntimeException （文件夹 exception 下创建）  
    使用注解 @EqualsAndHashCode(callSuper = true) ，类中包含具体讲解
6、创建通用API类 ApiResponse ： 实现统一异常处理 （文件夹 model 下创建）  
7、创建统一异常处理 DemoExceptionHandler ： 不做任何处理，后续使用 （文件夹 handler 下创建）  
    使用注解 @ControllerAdvice 对所有的 Controller 进行加强
8、实现 API 形式的接口进行异常处理
    本环节和样例中有差异，额外实现了 runtimeExceptionHandler() 方法，具体原因阅读文件说明
    8.1)、创建 JsonException 继承 BaseException , 用于 JSON 异常
    8.2)、在 DemoExceptionHandler 中实现 jsonExceptionHandler() 方法  和 runtimeExceptionHandler() 方法
        @ExceptionHandler(value = jsonExceptionHandler.class) ： 定义拦截的异常类  
    8.3)、创建 TestJsonController 测试 API 形式 的 全局异常演示
    -启动工程进行本环节测试：
    http://127.0.0.1:8111/demo/testJson
    http://127.0.0.1:8111/demo/testJson?num=0
    http://127.0.0.1:8111/demo/testJson?num=1
9、实现模板页面请求的异常处理
    9.1)、创建 PageException 继承 BaseException , 用于页面异常
    9.2)、在 DemoExceptionHandler 中实现 pageExceptionHandler() 方法  
    9.3)、创建 error.html 用于跳转的页面
    9.4)、创建 TestPageController 测试 模板页面请求的异常处理
    -启动工程进行本环节测试：
    http://127.0.0.1:8111/demo/testPage

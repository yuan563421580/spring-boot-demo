此 demo 主要演示了 Spring Boot 如何使用原生提供的异步任务支持，实现异步执行任务。
____
1、修改 pom.xml , 引入 spring boot 的 web 依赖和 test 依赖  
2、创建配置文件 application.yml , 配置 task 相关信息
spring:
  task:
    execution:
      pool:
        # 最大线程数
        max-size: 16
        # 核心线程数
        core-size: 16
        # 存活时间
        keep-alive: 10s
        # 队列大小
        queue-capacity: 100
        # 是否允许核心线程超时
        allow-core-thread-timeout: true
      # 线程名称前缀
      thread-name-prefix: async-task-
3、创建启动类 DemoAsyncApplication , 通过注解 [ @EnableAsync ] 开启异步的支持  
4、在 task 文件夹下创建 TaskFactory , 任务工厂 , 其他说明请直接阅读文件
    编写异步执行任务，使用注解 [ @Async ]； 编写同步执行任务  
5、编写测试模块 TaskFactoryTest 继承 DemoAsyncApplicationTests  
    分别编写[测试异步任务]和[测试同步任务], 分别运行观察数据  
6、运行数据  
   6.1).异步任务运行结果：  
       asyncTask1开始执行，当前线程名称【async-task-1】
       asyncTask3开始执行，当前线程名称【async-task-3】
       asyncTask2开始执行，当前线程名称【async-task-2】
       asyncTask2执行成功，当前线程名称【async-task-2】
       asyncTask3执行成功，当前线程名称【async-task-3】
       asyncTask1执行成功，当前线程名称【async-task-1】
       异步任务全部执行结束，总耗时：5108 毫秒
   6.2).同步任务运行结果：
       task1开始执行，当前线程名称【main】
       task1执行成功，当前线程名称【main】
       task2开始执行，当前线程名称【main】
       task2执行成功，当前线程名称【main】
       task3开始执行，当前线程名称【main】
       task3执行成功，当前线程名称【main】
       同步任务全部执行结束，总耗时：10079 毫秒
    
    
    

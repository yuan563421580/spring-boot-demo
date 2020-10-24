此 demo 主要演示了 Spring Boot 如何集成 RabbitMQ，
并且演示了基于直接队列模式、分列模式、主题模式、延迟队列的消息发送和接收。 
-
·高级消息队列协议(AMQP)是一种平台无关的、用于面向消息中间件的线级协议。
 Spring AMQP 项目将 Spring 的核心概念应用到基于 AMQP 的消息传递解决方案的开发中。
 Spring Boot 为通过 RabbitMQ 使用 AMQP 提供了一些便利，包括spring-boot-starter-amqp 。
·RabbitMQ 是一个轻量级、可靠、可伸缩、可移植的消息代理，基于AMQP协议。
 Spring 使用 RabbitMQ 通过 AMQP 协议进行通信。
 因为，Spring Boot 为通过 RabbitMQ 使用 AMQ P提供了一些便利。
 所以，与其说是 Spring Boot 整合 RabbitMQ，即 Spring Boot 使用 AMQP。  
________________________________________________
1、修改 pom.xml , 引入 RabbitMQ 依赖  
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-amqp</artifactId>
    </dependency>
2、创建配置文件 application.yml , 配置 RabbitMQ 信息
    spring.rabbitmq.*
3、创建启动类 DemoRabbitMqApplication
4、创建 RabbitMqConsts 为 RabbitMq 常量池（文件夹 constants 下）  
5、创建 RabbitMqConfig 为 RabbitMq 配置（文件夹 config 下）
6、创建 MessageStruct 为 测试消息体（文件夹 message 下）
7、创建 handler 处理器文件夹
    7.1) DirectQueueOneHandler 为 直接队列1 处理器
    7.2) QueueTwoHandler 为 队列2 处理器
    7.3) QueueThreeHandler 为 队列3 处理器
    7.4) DelayQueueHandler 为 延迟队列处理器 处理器
8、创建测试用例 DemoRabbitMqApplicationTests
9、现有流程测试：
    启动工程 -> 运行一个测试用例 -> 观察日志  
    9.1) 直接队列1 测试：
        Run:Tests:消息发送成功:correlationData(null),ack(true),cause(null)
        Services: 直接队列1，手动ACK，接收消息：{"message":"direct message"}
    9.2) 延迟队列 测试：
        Run:Tests:消息丢失:exchange(delay.mode),route(delay.queue),replyCode(312),replyText(NO_ROUTE) ...
                  消息发送成功:correlationData(null),ack(true),cause(null)
                  消息丢失:exchange(delay.mode),route(delay.queue),replyCode(312),replyText(NO_ROUTE) ...
                  消息发送成功:correlationData(null),ack(true),cause(null)
                  消息丢失:exchange(delay.mode),route(delay.queue),replyCode(312),replyText(NO_ROUTE) ...
                  消息发送成功:correlationData(null),ack(true),cause(null)
        Services: 延迟队列，手动ACK，接收消息：{"message":"delay message,  delay 2s, 2020-10-24 18:58:25"}
                  延迟队列，手动ACK，接收消息：{"message":"delay message, delay 5s, 2020-10-24 18:58:25"}
                  延迟队列，手动ACK，接收消息：{"message":"delay message,  delay 8s, 2020-10-24 18:58:26"}
________________________________________________
遇到一些坑，主要是因为用户名、vhost导致的。属于安装范畴，可以查看安装手册有详细说明。
________________________________________________
Spring Boot + RabbitMQ 配置参数解释
属性文件：org.springframework.boot.autoconfigure.amqp.RabbitProperties
Config:
-- [ base ] --
spring.rabbitmq.host: 服务Host
spring.rabbitmq.port: 服务端口
spring.rabbitmq.username: 登陆用户名
spring.rabbitmq.password: 登陆密码
spring.rabbitmq.virtual-host: 连接到rabbitMQ的vhost
spring.rabbitmq.addresses: 指定client连接到的server的地址，多个以逗号分隔(优先取addresses，然后再取host)
spring.rabbitmq.requested-heartbeat: 指定心跳超时，单位秒，0为不指定；默认60s
spring.rabbitmq.publisher-confirms: 是否启用【发布确认】
spring.rabbitmq.publisher-returns: 是否启用【发布返回】
spring.rabbitmq.connection-timeout: 连接超时，单位毫秒，0表示无穷大，不超时
spring.rabbitmq.parsed-addresses:
-- [ ssl ] --
spring.rabbitmq.ssl.enabled: 是否支持ssl
spring.rabbitmq.ssl.key-store: 指定持有SSL certificate的key store的路径
spring.rabbitmq.ssl.key-store-password: 指定访问key store的密码
spring.rabbitmq.ssl.trust-store: 指定持有SSL certificates的Trust store
spring.rabbitmq.ssl.trust-store-password: 指定访问trust store的密码
spring.rabbitmq.ssl.algorithm: ssl使用的算法，例如，TLSv1.1
-- [ cache ] --
spring.rabbitmq.cache.channel.size: 缓存中保持的channel数量
spring.rabbitmq.cache.channel.checkout-timeout: 当缓存数量被设置时，从缓存中获取一个channel的超时时间，单位毫秒；如果为0，则总是创建一个新channel
spring.rabbitmq.cache.connection.size: 缓存的连接数，只有是CONNECTION模式时生效
spring.rabbitmq.cache.connection.mode: 连接工厂缓存模式：CHANNEL 和 CONNECTION
-- [ listener ] --
spring.rabbitmq.listener.simple.auto-startup: 是否启动时自动启动容器
spring.rabbitmq.listener.simple.acknowledge-mode: 表示消息确认方式，其有三种配置方式，分别是none、manual和auto；默认auto
spring.rabbitmq.listener.simple.concurrency: 最小的消费者数量
spring.rabbitmq.listener.simple.max-concurrency: 最大的消费者数量
spring.rabbitmq.listener.simple.prefetch: 指定一个请求能处理多少个消息，如果有事务的话，必须大于等于transaction数量.
spring.rabbitmq.listener.simple.transaction-size: 指定一个事务处理的消息数量，最好是小于等于prefetch的数量.
spring.rabbitmq.listener.simple.default-requeue-rejected: 决定被拒绝的消息是否重新入队；默认是true（与参数acknowledge-mode有关系）
spring.rabbitmq.listener.simple.idle-event-interval: 多少长时间发布空闲容器时间，单位毫秒
spring.rabbitmq.listener.simple.retry.enabled: 监听重试是否可用
spring.rabbitmq.listener.simple.retry.max-attempts: 最大重试次数
spring.rabbitmq.listener.simple.retry.initial-interval: 第一次和第二次尝试发布或传递消息之间的间隔
spring.rabbitmq.listener.simple.retry.multiplier: 应用于上一重试间隔的乘数
spring.rabbitmq.listener.simple.retry.max-interval: 最大重试时间间隔
spring.rabbitmq.listener.simple.retry.stateless: 重试是有状态or无状态
-- [ template ] --
spring.rabbitmq.template.mandatory: 启用强制信息；默认false
spring.rabbitmq.template.receive-timeout: receive() 操作的超时时间
spring.rabbitmq.template.reply-timeout: sendAndReceive() 操作的超时时间
spring.rabbitmq.template.retry.enabled: 发送重试是否可用 
spring.rabbitmq.template.retry.max-attempts: 最大重试次数
spring.rabbitmq.template.retry.initial-interval: 第一次和第二次尝试发布或传递消息之间的间隔
spring.rabbitmq.template.retry.multiplier: 应用于上一重试间隔的乘数
spring.rabbitmq.template.retry.max-interval: 最大重试时间间隔
________________________________________________
https://www.meiwen.com.cn/subject/tfukrctx.html
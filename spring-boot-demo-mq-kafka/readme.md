此 demo 主要演示了如何使用 Spring Boot 集成 Zookeeper 结合 AOP 实现分布式锁。
________________________________________________
1、修改 pom.xml , 引入 kafka 依赖  
    <dependency>
        <groupId>org.springframework.kafka</groupId>
        <artifactId>spring-kafka</artifactId>
    </dependency>
2、创建配置文件 application.yml  
    配置端口 8123 ， 配置 kafka
    -说明：
    · bootstrap-servers：kafka服务器地址(可以多个)  
    · consumer.group-id:指定一个默认的组名  
    · auto-offset-reset：自动偏移量
        earliest ：当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，从头开始消费
        latest ：当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，消费新产生的该分区下的数据
        none ：topic各分区都存在已提交的offset时，从offset后开始消费；只要有一个分区不存在已提交的offset，则抛出异常
    · consumer.key-deserializer和consumer.value-deserializer是消费者key/value反序列化
    · producer.key-deserializer和producer.value-deserializer是生产者key/value序列化
        在使用Kafka发送接收消息时，生产者 producer 端需要序列化，消费者 consumer 端需要反序列化，
        由于网络传输过来的是 byte[]，只有反序列化后才能得到生产者发送的真实的消息内容。这样消息才能进行网络传输。
        StringSerializer 是内置的字符串序列化方式。
        StringDeserializer 是内置的字符串反序列化方式。
        -
        在 org.apache.kafka.common.serialization 源码包中还提供了多种类型的序列化和反序列化方式。
        要自定义序列化方式，需要实现接口 Serializer。
        要自定义反序列化方式，需要实现接口 Deserializer。   
3、创建启动类 DemoMqKafkaApplication
4、创建 KafkaConsts 为 kafka 常量池（文件夹 constants 下）  
5、创建 KafkaConfig 为 kafka 配置类（文件夹 config 下）  
6、创建 KafkaController 为 测试类
7、启动程序进行测试
    http:127.0.0.1:8123/demo/sendKafka    
    之后观察控制台日志，查询时候正确打印。
________________________________________________
报错：
01).[错误]：java.lang.IllegalStateException: No group.id found in consumer config, 
            container properties, or @KafkaListener annotation; 
            a group.id is required when group management is used.
    [解决]：配置 consumer.group-id 可以解决。  
01).[错误]：org.apache.kafka.common.config.ConfigException: 
            Invalid value  for configuration auto.offset.reset: 
            String must be one of: latest, earliest, none
    [解决]：配置 auto-offset-reset 可以解决。  
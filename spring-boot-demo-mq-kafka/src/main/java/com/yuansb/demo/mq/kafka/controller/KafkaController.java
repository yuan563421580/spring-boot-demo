package com.yuansb.demo.mq.kafka.controller;

import com.yuansb.demo.mq.kafka.constants.KafkaConsts;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试类
 *
 *  KafkaTemplate 这个类包装了个生产者 Producer， 来提供方便的发送数据到 kafka 的主题 topic 里面。
 *  kafkaTemplate.send(String topic, K key, V data), 第一个入参是主题，第二个入参是发送的对象，第三个入参是发送的数据。
 */
@RestController
@Slf4j
public class KafkaController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 测试发送
     * @return
     */
    @GetMapping("/sendKafka")
    public String sendKafka() {
        int iMax = 20;
        for (int i = 1; i < iMax; i++) {
            kafkaTemplate.send(KafkaConsts.TOPIC_TEST,"key_" + i, "data_" + i );
        }
        return "success";
    }

}

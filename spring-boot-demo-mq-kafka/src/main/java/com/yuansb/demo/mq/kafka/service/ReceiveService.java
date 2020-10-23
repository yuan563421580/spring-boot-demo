package com.yuansb.demo.mq.kafka.service;

import com.yuansb.demo.mq.kafka.constants.KafkaConsts;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 监听接收设备
 *
 * 通过 @KafkaListener 注解配置用户监听 topics
 */
@Service
@Slf4j
public class ReceiveService {

    @KafkaListener(topics = KafkaConsts.TOPIC_TEST)
    public void receive(List<ConsumerRecord<?, ?>> records) {
        log.info("ReceiveService.receive Listener Thread ID: " + Thread.currentThread().getId());
        log.info("ReceiveService.receive records size " +  records.size());

        for (ConsumerRecord<?, ?> record : records) {
            Optional<?> kafkaMessage = Optional.ofNullable(record.value());
            log.info("ReceiveService.receive Received: " + record);
            if (kafkaMessage.isPresent()) {
                String topic = record.topic();
                Object key = record.key();
                Object message = record.value();
                log.info("ReceiveService.receive 收到消息: {}- {}:{}", topic, key, message);
            }
        }

    }

}

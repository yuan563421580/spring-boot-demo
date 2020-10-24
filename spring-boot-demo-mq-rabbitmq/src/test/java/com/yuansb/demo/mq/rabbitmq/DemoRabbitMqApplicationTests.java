package com.yuansb.demo.mq.rabbitmq;

import cn.hutool.core.date.DateUtil;
import com.yuansb.demo.mq.rabbitmq.constants.RabbitMqConsts;
import com.yuansb.demo.mq.rabbitmq.message.MessageStruct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试用例
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoRabbitMqApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 测试直接模式发送
     */
    @Test
    public void sendDirect() {
        rabbitTemplate.convertAndSend(RabbitMqConsts.DIRECT_MODE_QUEUE_ONE, new MessageStruct("direct message"));
    }

    /**
     * 测试分列模式发送
     */
    @Test
    public void sendFanout() {
        rabbitTemplate.convertAndSend(RabbitMqConsts.FANOUT_MODE_QUEUE, "", new MessageStruct("fanout message"));
    }

    /**
     * 测试主题模式发送1
     */
    @Test
    public void sendTopic1() {
        rabbitTemplate.convertAndSend(RabbitMqConsts.TOPIC_MODE_QUEUE, "queue.aaa.bbb", new MessageStruct("topic message"));
    }

    /**
     * 测试主题模式发送2
     */
    @Test
    public void sendTopic2() {
        rabbitTemplate.convertAndSend(RabbitMqConsts.TOPIC_MODE_QUEUE, "ccc.queue", new MessageStruct("topic message"));
    }

    /**
     * 测试主题模式发送3
     */
    @Test
    public void sendTopic3() {
        rabbitTemplate.convertAndSend(RabbitMqConsts.TOPIC_MODE_QUEUE, "3.queue", new MessageStruct("topic message"));
    }

    /**
     * 测试延迟队列发送
     */
    @Test
    public void sendDelay() {
        rabbitTemplate.convertAndSend(RabbitMqConsts.DELAY_MODE_QUEUE, RabbitMqConsts.DELAY_QUEUE, new MessageStruct("delay message, delay 5s, " +
                DateUtil.date()), message -> {
            message.getMessageProperties().setHeader("x-delay", 5000);
            return message;
        });
        rabbitTemplate.convertAndSend(RabbitMqConsts.DELAY_MODE_QUEUE, RabbitMqConsts.DELAY_QUEUE, new MessageStruct("delay message,  delay 2s, " +
                DateUtil.date()), message -> {
            message.getMessageProperties().setHeader("x-delay", 2000);
            return message;
        });
        rabbitTemplate.convertAndSend(RabbitMqConsts.DELAY_MODE_QUEUE, RabbitMqConsts.DELAY_QUEUE, new MessageStruct("delay message,  delay 8s, " +
                DateUtil.date()), message -> {
            message.getMessageProperties().setHeader("x-delay", 8000);
            return message;
        });
    }

}

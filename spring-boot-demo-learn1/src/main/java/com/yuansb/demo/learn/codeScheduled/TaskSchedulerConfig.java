package com.yuansb.demo.learn.codeScheduled;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 配置类
 *
 * 通过 @EnableScheduling 注解开启对计划任务的支持。
 */
@Configuration
@ComponentScan("com.yuansb.demo.learn.codeScheduled")
@EnableScheduling
public class TaskSchedulerConfig {
}

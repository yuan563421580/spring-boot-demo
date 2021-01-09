package com.yuansb.demo.taskExecutor;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Spring 通过任务执行器（TaskExecutor）来实现多线程和并发编程。
 * 使用 ThreadPoolTaskExecutor 可实现一个基于线程池的 TaskExecutor。
 *
 * 利用 @EnableAsync 注解开启异步任务支持
 * 配置类实现 AsyncConfigurer 接口并重写 getAsyncExecutor() 方法，
 *      并返回一个 ThreadPoolTaskExecutor，这样我们就获得了一个基于线程池 TaskExecutor 。
 */
@Configuration
@ComponentScan("com.yuansb.demo.taskExecutor")
@EnableAsync
public class TaskExecutorConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(25);
        taskExecutor.initialize();
        return taskExecutor;
    }

}

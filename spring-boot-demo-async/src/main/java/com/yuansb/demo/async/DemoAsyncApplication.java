package com.yuansb.demo.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 启动类
 *
 * 通过注解 @EnableAsync 开启异步的支持
 *  spring为了提升开发人员的开发效率，使用 @EnableAsync 来开启异步的支持，使用 @Async 来对某个方法进行异步执行。
 */
@EnableAsync
@SpringBootApplication
public class DemoAsyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoAsyncApplication.class, args);
    }

}

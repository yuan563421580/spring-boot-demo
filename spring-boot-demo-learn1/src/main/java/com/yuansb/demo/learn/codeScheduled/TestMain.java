package com.yuansb.demo.learn.codeScheduled;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试运行类
 */
public class TestMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(TaskSchedulerConfig.class);
    }

}

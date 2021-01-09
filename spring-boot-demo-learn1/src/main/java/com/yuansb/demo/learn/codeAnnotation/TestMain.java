package com.yuansb.demo.learn.codeAnnotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试运行类
 */
public class TestMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(DemoConfig.class);

        DemoService demoService = context.getBean(DemoService.class);

        demoService.outputResult();

        context.close();
    }

}

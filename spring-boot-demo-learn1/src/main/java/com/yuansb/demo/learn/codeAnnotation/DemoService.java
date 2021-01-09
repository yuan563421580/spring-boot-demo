package com.yuansb.demo.learn.codeAnnotation;

import org.springframework.stereotype.Service;

/**
 * 演示服务 Bean
 */
@Service
public class DemoService {

    public void outputResult() {
        System.out.println("从组合注解配置照样获得的bean");
    }

}

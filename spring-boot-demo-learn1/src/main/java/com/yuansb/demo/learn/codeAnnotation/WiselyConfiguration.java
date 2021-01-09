package com.yuansb.demo.learn.codeAnnotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.*;

/**
 * 示例组合注解
 *
 * 1.组合 @Configuration 元注解
 * 2.组合 @ComponentScan 元注解
 * 3.覆盖 value 参数
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration // 1
@ComponentScan // 2
public @interface WiselyConfiguration {

    String [] value() default {}; //3

}

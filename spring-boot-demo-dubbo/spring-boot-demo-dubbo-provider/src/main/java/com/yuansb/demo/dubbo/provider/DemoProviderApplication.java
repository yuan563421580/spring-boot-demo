package com.yuansb.demo.dubbo.provider;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * spring boot 整合 dubbo 专属的 @EnableDubboConfiguration 注解提供的 dubbo 自动配置。
 */
@EnableDubboConfiguration //开启 dubbo 的自动配置
@SpringBootApplication
public class DemoProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoProviderApplication.class, args);
    }

}

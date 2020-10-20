package com.yuansb.demo.war;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 启动类
 *
 *      若需要打成 war 包，则需要写一个类继承 {@link SpringBootServletInitializer}
 *      并重写 {@link SpringBootServletInitializer#configure(SpringApplicationBuilder)}
 */
@SpringBootApplication
public class DemoWarApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DemoWarApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoWarApplication.class);
    }

}

package com.yuansb.demo.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * 启动类
 * @EnableWebFlux 开启 WebFlux
 */
@SpringBootApplication
@EnableWebFlux
public class DemoWebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoWebfluxApplication.class, args);
    }

}

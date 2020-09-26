package com.yuansb.demo.admin.server;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * 通过注解 @EnableAdminServer , 开启 AdminServer 的功能
 */
@EnableAdminServer
@SpringBootApplication
public class DemoAdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoAdminServerApplication.class, args);
    }

}

package com.yuansb.demo.multi.datasource.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 */
@SpringBootApplication
@MapperScan(basePackages = "com.yuansb.demo.multi.datasource.mybatis.mapper")
public class DemoMultiDatasourceMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMultiDatasourceMybatisApplication.class, args);
    }

}

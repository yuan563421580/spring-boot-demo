package com.yuansb.demo.orm.mybatis.plus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * 通过注解 @MapperScan 添加mybatis的包扫描路径
 */
@SpringBootApplication
@MapperScan("com.yuansb.demo.orm.mybatis.plus.mapper")
public class DemoOrmMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoOrmMybatisPlusApplication.class, args);
    }

}

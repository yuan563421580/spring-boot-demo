package com.yuansb.demo.task.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 启动器
 */
@MapperScan(basePackages = {"com.yuansb.demo.task.quartz.mapper"})
@SpringBootApplication
public class DemoTaskQuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoTaskQuartzApplication.class, args);
    }

}

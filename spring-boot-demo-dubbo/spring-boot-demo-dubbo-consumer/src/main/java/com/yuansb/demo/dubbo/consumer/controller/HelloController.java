package com.yuansb.demo.dubbo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yuansb.demo.dubbo.common.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello 服务 API
 *
 * 说明：
 *      @Reference 是 dubbo 的注解，也是注入，他一般注入的是分布式的远程服务的对象，需要 dubbo 配置使用。
 */
@RestController
@Slf4j
public class HelloController {

    @Reference
    private HelloService helloService;

    @GetMapping("/sayHello")
    public String sayHello(@RequestParam(defaultValue = "yuansb") String name) {
        log.info("i'm ready to call someone......");
        return helloService.sayHello(name);
    }
}

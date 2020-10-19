package com.yuansb.demo.dubbo.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.yuansb.demo.dubbo.common.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Hello 服务实现
 *
 * 注意：
 *      @Service 注解使用的是 Dubbo 提供的而不是 Spring 提供的。
 *
 */
@Service
@Component
@Slf4j
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        log.info("someone is calling me......");
        return "say hello to: " + name;
    }

}

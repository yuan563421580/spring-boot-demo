package com.yuansb.demo.helloworld.controller;

import cn.hutool.core.util.StrUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试类
 *
 * http:127.0.0.1:8101/demo/sayHello
 * http://127.0.0.1:8101/demo/sayHello?who=World
 */
@RestController
public class HelloWorldController {

    @GetMapping("/sayHello")
    public String sayHello(@RequestParam(name = "who", required = false) String who) {
        if (StrUtil.isBlank(who)) {
            who = "World";
        }
        return StrUtil.format("Hello, {}!", who);
    }

}

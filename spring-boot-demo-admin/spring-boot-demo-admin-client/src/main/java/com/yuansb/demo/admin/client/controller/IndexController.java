package com.yuansb.demo.admin.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页
 */
@RestController
public class IndexController {

    @GetMapping(value = {"", "/"})
    public String index() {
        return "This is a Spring Boot Admin Client.";
    }

}

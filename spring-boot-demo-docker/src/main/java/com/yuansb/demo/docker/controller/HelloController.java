package com.yuansb.demo.docker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello Controller
 */
@RestController
@RequestMapping
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello,From Docker!";
    }

}

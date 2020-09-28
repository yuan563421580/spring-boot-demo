package com.yuansb.demo.exception.handler.controller;

import com.yuansb.demo.exception.handler.constant.Status;
import com.yuansb.demo.exception.handler.exception.PageException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 页面异常演示
 */
@Controller
public class TestPageController {

    @GetMapping("/testPage")
    public ModelAndView testPage() {
        throw new PageException(Status.UNKNOWN_ERROR);
    }

}

package com.yuansb.demo.exception.handler.controller;

import com.yuansb.demo.exception.handler.constant.Status;
import com.yuansb.demo.exception.handler.exception.JsonException;
import com.yuansb.demo.exception.handler.model.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局异常演示
 */
@RestController
public class TestJsonController {

    @GetMapping("/testJson")
    public ApiResponse testJson(Integer num) {
        //演示需要，实际上参数是否为空通过 @RequestParam(required = true)  就可以控制
        if (num == null) {
            //throw new JsonException(1001, "num不能为空");
            throw new JsonException(Status.NUM_IS_NULL);
        }

        int i = 10 / num;
        ApiResponse response = ApiResponse.ofSuccess(i);
        return response;
    }

}

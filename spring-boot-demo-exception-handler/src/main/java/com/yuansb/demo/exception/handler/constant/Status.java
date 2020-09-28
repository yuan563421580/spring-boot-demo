package com.yuansb.demo.exception.handler.constant;

import lombok.Getter;

/**
 * 状态码封装
 */
@Getter
public enum Status {

    /**
     * 操作成功
     */
    OK(200, "操作成功"),

    /**
     * 未知异常
     */
    UNKNOWN_ERROR(500, "服务器出错啦"),

    /**
     * 号码不能为空
     */
    NUM_IS_NULL(1001, "NUM不能为空");

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 内容
     */
    private String message;

    Status(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}

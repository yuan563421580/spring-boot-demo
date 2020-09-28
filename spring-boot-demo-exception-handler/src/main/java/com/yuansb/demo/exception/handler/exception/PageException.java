package com.yuansb.demo.exception.handler.exception;

import com.yuansb.demo.exception.handler.constant.Status;
import lombok.Getter;

/**
 * 页面异常
 */
@Getter
public class PageException extends BaseException {

    public PageException(Status status) {
        super(status);
    }

    public PageException(Integer code, String message) {
        super(code, message);
    }

}

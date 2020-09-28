package com.yuansb.demo.exception.handler.exception;

import com.yuansb.demo.exception.handler.constant.Status;
import lombok.Getter;

/**
 * JSON异常
 */
@Getter
public class JsonException extends BaseException {

    public JsonException(Status status) {
        super(status);
    }

    public JsonException(Integer code, String message) {
        super(code, message);
    }

}

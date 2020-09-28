package com.yuansb.demo.exception.handler.handler;

import com.yuansb.demo.exception.handler.exception.JsonException;
import com.yuansb.demo.exception.handler.exception.PageException;
import com.yuansb.demo.exception.handler.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 统一异常处理
 *
 *  @ControllerAdvice
 *      使用统一异常处理来抽取出这一部分的代码(其实是 aop 增强方式) 使用 @ControllerAdvice 对所有的 Controller 进行加强
 *  @ExceptionHandler
 *      注解 @ExceptionHandler 定义拦截的异常类
 *
 *  说明：不实现 @ExceptionHandler(value = RuntimeException.class)
 *      运行时异常有的时候捕获：例：10/0
 */
@ControllerAdvice
@Slf4j
public class DemoExceptionHandler {

    private static final String DEFAULT_ERROR_VIEW = "error";

    /**
     * 统一 json 异常处理
     *
     * @param exception JsonException
     * @return 统一返回 json 格式
     */
    @ExceptionHandler(value = JsonException.class)
    @ResponseBody
    public ApiResponse jsonExceptionHandler(JsonException exception) {
        log.error("【JsonException】:{}", exception.getMessage());
        return ApiResponse.ofException(exception);
    }

    /**
     * 捕获 RuntimeException 异常
     * @param request
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ApiResponse runtimeExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        RuntimeException exception = (RuntimeException) e;
        log.error("【RuntimeException】:{}", exception.getMessage());
        return ApiResponse.of(400, exception.getMessage(), null);
    }

    /**
     * 统一页面 异常处理
     *
     * @param exception PageException
     * @return 统一跳转到异常页面
     */
    @ExceptionHandler(value = PageException.class)
    public ModelAndView pageExceptionHandler(PageException exception) {
        log.error("【DemoPageException】:{}", exception.getMessage());
        ModelAndView view = new ModelAndView();
        view.addObject("message", exception.getMessage());
        view.setViewName(DEFAULT_ERROR_VIEW);
        return view;
    }

}

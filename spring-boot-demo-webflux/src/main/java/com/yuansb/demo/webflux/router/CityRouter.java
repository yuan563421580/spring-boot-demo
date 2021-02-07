package com.yuansb.demo.webflux.router;

import com.yuansb.demo.webflux.handler.CityHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

/**
 * 新建城市（City）例子的路由类 CityRouter
 */
@Configuration
public class CityRouter {

    @Bean
    public RouterFunction<ServerResponse> routeCity(CityHandler cityHandler) {

        /**
         * RouterFunctions 对请求路由处理类，即将请求路由到处理器。
         * 这里将一个 GET 请求 /hello 路由到处理器 cityHandler 的 helloCity 方法上。
         * 跟 Spring MVC 模式下的 HandleMapping 的作用类似。
         *
         * RouterFunctions.route(RequestPredicate, HandlerFunction) 方法，对应的入参是请求参数和处理函数，
         * 如果请求匹配，就调用对应的处理器函数。
         */

        return RouterFunctions
                .route(RequestPredicates.GET("/hello")
                                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                        cityHandler::helloCity);
    }

}

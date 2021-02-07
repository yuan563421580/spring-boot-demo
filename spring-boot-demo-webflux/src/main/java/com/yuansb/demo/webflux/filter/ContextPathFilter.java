package com.yuansb.demo.webflux.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * ContextPath 问题解决 不配置过滤器会报错误：Whitelabel Error Page
 *
 * spring mvc 有 ContextPath 的配置选项，
 * webflux 因为没有 DispatchServlet，已经不支持 ContextPath 了，
 * 一般来说都是在 nginx 统一配置路径转发就好了。
 *
 * 本地调试时可能就需要稍微注意下了，要么本地也装个 nginx 和 线上环境保持一致，要么就做差异化配置，
 * 还有种方法，通过 WebFilter 的方式做一层 ContextPath 的转发，不过有一定风险，不推荐使用。
 */

//所有/contextPath前缀的请求都会自动去除该前缀
@Component
public class ContextPathFilter implements WebFilter {

    @Autowired
    private ServerProperties serverProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String contextPath = serverProperties.getServlet().getContextPath();
        String requestPath = exchange.getRequest().getPath().pathWithinApplication().value();
        if(contextPath != null && requestPath.startsWith(contextPath)){
            requestPath = requestPath.substring(contextPath.length());
        }
        return chain.filter(exchange.mutate().request(exchange.getRequest().mutate().path(requestPath).build()).build());
    }

}

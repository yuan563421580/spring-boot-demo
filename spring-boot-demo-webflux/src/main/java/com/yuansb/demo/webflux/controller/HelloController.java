package com.yuansb.demo.webflux.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 编写 WebFlux 的 Controller 可以沿用 SpringMVC 的那一套，
 * 但是返回结果需要改成 Mono 或者 Flux。
 * Mono 最多发射一个数据，Flux 可以发射多个数据
 *
 * WebFlux 应用中，所有数据都应该以 Mono、Flux 的形式表示，这样才能带来最好的性能和高吞吐量。
 *  Mono 和 Flux 这两种数据模型是 WebFlux 的核心。
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class HelloController {

    /**
     * 测试 API mono() 方法
     *
     * mono 接口这种执行模式是同步非阻塞的，
     *  也就是 Mono 产生数据，和 Mono 与 Http 之间的订阅消费都是在同一个线程。
     *  图解：img/momo.jpg
     *
     * @return
     */
    @GetMapping("/mono")
    public Mono<String> mono() {
        return Mono.just("hello webflux");
    }

    /**
     * 测试 API mono() 方法
     * 用于验证 mono() 方法描述的 同一个线程中 的问题。
     *
     * 查看打印日志：
     *  INFO 15260 --- [ctor-http-nio-2] c.y.d.w.controller.HelloController       : --doOnSubscribe-->MonoSink
     *  INFO 15260 --- [ctor-http-nio-2] c.y.d.w.controller.HelloController       : --create Mono-->MonoSink
     *  INFO 15260 --- [ctor-http-nio-2] c.y.d.w.controller.HelloController       : --doOnNext-->hello webflux
     * Mono 的【创建】和【消费】都是都是在 [ctor-http-nio-2] 这个线程中
     * 发现【订阅动作】在【创建动作】之前，最后是【消费】，那么为什么会这样呢
     *
     * @return
     */
    @GetMapping("/mono1")
    public Mono<Object> mono1() {
        return Mono.create(monoSink -> {
            log.info("--create Mono-->{}", monoSink);
            monoSink.success("hello webflux");
        }).doOnSubscribe(subscription -> {
            //当订阅者去订阅发布者的时候，该方法会调用
            log.info("--doOnSubscribe-->{}", subscription);
        }).doOnNext(o -> {
            //当订阅者收到数据时，改方法会调用
            log.info("--doOnNext-->{}", o);
        });
    }

    /**
     * 测试 API flux() 方法 发射多个数据
     *
     * @return
     */
    @GetMapping("/flux")
    public Flux<String> flux() {
        return Flux.just("hello ", "webflux ", "spring", "boot");
    }


    /**
     * 服务器推送(SSE - >Server Send Event)
     *  效果就是每秒会给浏览器推送数据。实际测试没有这个效果。
     * 注：需要指定 MediaType
     *
     * @return
     */
    @GetMapping(value = "/flux_data", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    private Flux<String> flux_data() {
        Flux<String> result = Flux
                .fromStream(IntStream.range(1, 5)
                        .mapToObj(i -> {
                            try {
                                TimeUnit.SECONDS.sleep(1);
                            } catch (InterruptedException e) {
                            }
                            return "flux data -- " + i;
                        })
                );
        return result;
    }

}

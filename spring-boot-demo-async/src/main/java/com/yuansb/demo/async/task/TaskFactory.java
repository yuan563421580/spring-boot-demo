package com.yuansb.demo.async.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 任务工厂
 *
 * Future 理解：
 *      Java 在并发方面引入了 「 将来 」( Future ) 这个概念。
 *      把所有不在主线程执行的代码都附加了将来这个灵魂。主线程只负责其它并发线程的创建、启动、监视和处理并发线程完成任务或发生异常时的回调。
 *      其它情况，则交给并发线程自己去处理。而双方之间的沟通，就是通过一个个被称之为 「 将来 」 的类出处理。
 *      Future 定义在 java.util.concurrent 包中，这是一个接口，自 Java 1.5 以来一直存在的接口，用于处理异步调用和处理并发编程。
 * AsyncResult 理解：
 *      AsyncResult是异步方式，异步主要用于调用的代码需要长时间运行，才能返回结果的时候，可以不阻塞调用者。
 * @Async 理解：
 *      在Spring中，基于@Async标注的方法，称之为异步方法；
 *      这些方法将在执行的时候，将会在独立的线程中被执行，调用者无需等待它的完成，即可继续其他的操作。
 */
@Component
@Slf4j
public class TaskFactory {

    /**
     * 模拟5秒的异步任务
     */
    @Async
    public Future<Boolean> asyncTask1() throws InterruptedException {
        doTask("asyncTask1", 5);
        return new AsyncResult<>(Boolean.TRUE);
    }

    /**
     * 模拟2秒的异步任务
     */
    @Async
    public Future<Boolean> asyncTask2() throws InterruptedException {
        doTask("asyncTask2", 2);
        return new AsyncResult<>(Boolean.TRUE);
    }

    /**
     * 模拟3秒的异步任务
     */
    @Async
    public Future<Boolean> asyncTask3() throws InterruptedException {
        doTask("asyncTask3", 3);
        return new AsyncResult<>(Boolean.TRUE);
    }


    /**
     * 模拟5秒的同步任务
     */
    public void task1() throws InterruptedException {
        doTask("task1", 5);
    }

    /**
     * 模拟2秒的同步任务
     */
    public void task2() throws InterruptedException {
        doTask("task2", 2);
    }

    /**
     * 模拟3秒的同步任务
     */
    public void task3() throws InterruptedException {
        doTask("task3", 3);
    }

    /**
     * 模拟业务进行线程睡眠 传入时间（秒）
     * @param taskName
     * @param time
     * @throws InterruptedException
     */
    private void doTask(String taskName, Integer time) throws InterruptedException {
        log.info("{}开始执行，当前线程名称【{}】", taskName, Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(time);
        log.info("{}执行成功，当前线程名称【{}】", taskName, Thread.currentThread().getName());
    }

}

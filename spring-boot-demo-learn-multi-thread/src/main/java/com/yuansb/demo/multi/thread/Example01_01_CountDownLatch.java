package com.yuansb.demo.multi.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 需求功能：我们要读取处理 6 个文件，这 6 个任务都是没有执行顺序依赖的任务，
 *          但是我们需要返回给用户的时候将这几个文件的处理的结果进行统计整理。
 *
 * 需求实现：为此我们定义了一个线程池和 count 为 6 的 CountDownLatch 对象 。
 *          使用线程池处理读取任务，每一个线程处理完之后就将 count-1，
 *          调用CountDownLatch对象的 await() 方法，直到所有文件读取完之后，才会接着执行后面的逻辑。
 *
 * 可以继续使用 CompletableFuture 进行程序的优化：Example01_02_CompletableFuture
 */
public class Example01_01_CountDownLatch {

    /**
     * countDownLatch 相关知识点了解：
     *  countDownLatch 这个类使一个线程等待其他线程各自执行完毕后再执行。
     *      是通过一个计数器来实现的，计数器的初始值是线程的数量。每当一个线程执行完毕后，计数器的值就减 1，
     *      当计数器的值为 0 时，表示所有线程都执行完毕，然后在闭锁上等待的线程就可以恢复工作了。
     *  countDownLatch 类中只提供了一个构造器：
     *      //参数count为计数值
     *      public CountDownLatch(int count) {  };
     *  countDownLatch 类中有三个方法是最重要的：
     *      //调用 await() 方法的线程会被挂起，它会等待直到 count 值为 0 才继续执行
     *      public void await() throws InterruptedException { };
     *      //和 await() 类似，只不过等待一定的时间后 count 值还没变为 0 的话就会继续执行
     *      public boolean await(long timeout, TimeUnit unit) throws InterruptedException { };
     *      //将 count 值减 1
     *      public void countDown() { };
     */

    /**
     * ThreadPoolExecutor 相关知识点了解：
     *  三个关闭方法 shutdown()、shutdownNow()、awaitTermination()
     *  · shutdown() ：
     *      将线程池状态置为SHUTDOWN,并不会立即停止：停止接收外部submit的任务；内部正在跑的任务和队列里等待的任务，会执行完。
     *  · shutdownNow() ：
     *      ~ 将线程池状态置为STOP。企图立即停止，事实上不一定：跟shutdown()一样，先停止接收外部提交的任务，忽略队列里等待的任务，
     *        尝试将正在跑的任务interrupt中断，返回未执行的任务列表。
     *      ~ 它试图终止线程的方法是通过调用Thread.interrupt()方法来实现的，但是这种方法的作用有限，
     *        如果线程中没有sleep 、wait、Condition、定时锁等应用, interrupt()方法是无法中断当前的线程的。
     *        所以，ShutdownNow()并不代表线程池就一定立即就能退出，它也可能必须要等待所有正在执行的任务都执行完成了才能退出。
     *      ~ 但是大多数时候是能立即退出的。
     *  · awaitTermination() ：
     *      ~ 接收人timeout和TimeUnit两个参数，用于设定超时时间及单位。
     *        当等待超过设定时间时，会监测ExecutorService是否已经关闭，若关闭则返回true，否则返回false。
     *        一般情况下会和shutdown方法组合使用。
     */

    // 处理文件的数量
    private static final int threadCount = 6;

    public static void main(String[] args) throws InterruptedException{

        Example01_01_CountDownLatch example = new Example01_01_CountDownLatch();

        // 创建一个具有固定线程数量的线程池（推荐使用构造方法创建）
        // ExecutorService threadPool = Executors.newFixedThreadPool(10);

        // 使用手动方式创建线程池（手动创建线程池效果会更好）
        //  参考：https://blog.csdn.net/weixin_43826242/article/details/105589098
        //      corePoolSize核心线程数量8
        //      maximumPoolSize线程池容纳的最大线程数量10，maximumPoolSize > corePoolSize 的情况下才创建新线程
        //      keepAliveTime空闲线程的等待时间(默认好，毫秒)，超过这个时间线程就会退出，直到线程数等于corePoolSize
        //      unit 为 TimeUnit.SECONDS, 单位秒，是keepAliveTime的时间单位
        //      缓冲队列：当线程数超过了corePoolSize就会进入缓冲队列：new LinkedBlockingQueue<>(10)
        //          ArrayBlockingQueue : 使用确定长度数组构成的FIFO队列
        //          LinkedBlockingQueue : 基于链表的FIFO队列
        //          SynchronousQueue : 内部只能包含一个元素的队列
        //      线程创建工厂：是用来创建线程的。默认new Executors.DefaultThreadFactory();
        //      线程饱和策略：有以下4种，均是ThreadPoolExecutor类的成员。本次：new ThreadPoolExecutor.AbortPolicy()
        //          AbortPolicy 丢弃任务，抛运行时异常
        //          CallerRunsPolicy 执行任务
        //          DiscardPolicy 忽视，什么都不会发生
        //          DiscardOldestPolicy 从队列中踢出最先进入队列（最后一个执行）的任务
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                10, 20, 1, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        List<Map> fileLists = new ArrayList<>();
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            threadPool.execute(() -> {
                try {
                    // 处理文件的业务操作 每一个等待5秒模拟业务处理的逻辑
                    Map map = example.dealFile(threadNum);
                    fileLists.add(map);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // 标识一个文件已经被完成
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        threadPool.shutdown();
        //测试打印结果
        fileLists.stream()
                .forEach(System.out::println);

        System.out.println("finish");
    }


    public Map dealFile(int index){
        System.out.println("开始处理第 " + index + " 个文件");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("完成处理第 " + index + " 个文件");

        Map map = new HashMap();
        map.put("file" + index, "file success");
        return map;
    }


}

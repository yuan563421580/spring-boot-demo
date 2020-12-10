package com.yuansb.demo.multi.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 学习 CompletableFuture 使用例子
 */
public class DemoCompletableFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        supplyAsyncMethod();
        //runAsyncMethod();
        //supplyAsyncMethod2();
        //supplyAsyncOfMethod();
        //instanceMethod();
        //instanceMethod2();
        //whenMethod();
        //whenMethod2();
        //thenMethod();
        //thenCombineMethod();
        //thenRunMethod();
    }

    /**
     * CompletableFuture supplyAsync的使用例子
     */
    public static void supplyAsyncMethod() throws ExecutionException, InterruptedException {
        /*Java 线程池
        Java通过Executors提供四种线程池，分别为：
        newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
        newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
        newScheduledThreadPool 创建一个周期线程池，支持定时及周期性任务执行。
        newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。*/
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println("executorService 是否为守护线程 :" + Thread.currentThread().isDaemon());
                return null;
            }
        });

        final CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("this is lambda supplyAsync");
            System.out.println("supplyAsync 是否为守护线程 " + Thread.currentThread().isDaemon());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("this lambda is executed by forkJoinPool");
            return "result1";
        });

        final CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("this is task with executor");
            System.out.println("supplyAsync 使用 executorService 时是否为守护线程 " + Thread.currentThread().isDaemon());
            return "result2";
        }, executorService);
        System.out.println(completableFuture.get());
        System.out.println(future.get());
        executorService.shutdown();

        /**
         * 正常运行结果
         * executorService 是否为守护线程 :false
         * this is lambda supplyAsync
         * this is task with executor
         * supplyAsync 使用 executorService 时是否为守护线程 false
         * supplyAsync 是否为守护线程 true
         * this lambda is executed by forkJoinPool
         * result1
         * result2
         */

        /**
         * 注释掉 completableFuture.get() 和 future.get() 的 返回结果
         *
         * executorService 是否为守护线程 :false
         * this is lambda supplyAsync
         * this is task with executor
         * supplyAsync 使用 executorService 时是否为守护线程 false
         * supplyAsync 是否为守护线程 true
         *
         * 对比 发现没有输出 this lambda is executed by forkJoinPool
         */

        /**
         * 结论：
         *  先进行 runAsyncMethod() 验证测试后看结论
         *
         *  造成这个原因是因为Daemon。因为completableFuture这套使用异步任务的操作都是创建成了守护线程。
         *  那么我们没有调用get方法不阻塞这个主线程的时候。主线程执行完毕。所有线程执行完毕就会导致一个问题，就是守护线程退出。
         *  那么我们没有执行的代码就是因为主线程不再跑任务而关闭导致的。可能这个不叫问题，因为在开发中我们主线程常常是一直开着的。
         *  但是这个小问题同样让我想了好久。下面我们开一个非守护线程，可以看到程序执行顺利。
         */

    }

    /**
     * CompletableFuture runAsync的使用例子
     * 主要是为了验证 supplyAsync 方法
     */
    public static void runAsyncMethod() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CompletableFuture completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("runAsync 是否为守护线程 " + Thread.currentThread().isDaemon());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("this is runAsync");
        });

        final CompletableFuture completableFuture2 = CompletableFuture.runAsync(() -> {
            System.out.println("runAsync 使用 executorService 时是否为守护线程 " + Thread.currentThread().isDaemon());
            System.out.println("this is two runAsync");
        }, executorService);

        System.out.println(completableFuture.get());
        System.out.println(completableFuture2.get());
        executorService.shutdown();
    }

    /**
     * 证实守护线程在其他非守护线程全部退出的情况下不继续执行例子
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void supplyAsyncMethod2() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        final CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("this is lambda supplyAsync");
            System.out.println("supplyAsync 是否为守护线程 " + Thread.currentThread().isDaemon());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("this lambda is executed by forkJoinPool");
            return "result1";
        });

        final CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("this is task with executor");
            System.out.println("supplyAsync 使用 executorService 时是否为守护线程 " + Thread.currentThread().isDaemon());
            return "result2";
        }, executorService);

        // 有非守护线程运行。那么守护线程就不会退出。程序正确返回
        executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println("executorService 是否为守护线程 :" + Thread.currentThread().isDaemon());
                TimeUnit.SECONDS.sleep(3);
                return null;
            }
        });
        executorService.shutdown();

        /**
         * 运行结果
         *
         * this is lambda supplyAsync
         * supplyAsync 是否为守护线程 true
         * this is task with executor
         * supplyAsync 使用 executorService 时是否为守护线程 false
         * executorService 是否为守护线程 :false
         * this lambda is executed by forkJoinPool
         */
    }

    /**
     * allOf & anyOf
     * 这两个方法的入参是一个 completableFuture 组
     * allOf 就是所有任务都完成时返回。但是是个 void 的返回值。
     * anyOf 是当入参的 completableFuture 组中有一个任务执行完毕就返回。返回结果是第一个完成的任务的结果。
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void supplyAsyncOfMethod() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> futureOne = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "futureOneResult";
        });

        final CompletableFuture<String> futureTwo = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "futureTwoResult";
        });

        //anyDoneFuture 结果为 1 临时加入用于测试泛型不一致情况
        /*final CompletableFuture<Integer> futureThree = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 1;
        });*/

        //anyOfFuture 结果为 futureOneResult
        //CompletableFuture anyOfFuture = CompletableFuture.anyOf(futureOne, futureTwo);
        //System.out.println("anyOfFuture 结果为 " + anyOfFuture.get());

        //allOfFuture 结果为 null
        //CompletableFuture allOfFuture = CompletableFuture.allOf(futureOne, futureTwo);
        //System.out.println("allOfFuture 结果为 " + allOfFuture.get());

        List<CompletableFuture> futureList = new ArrayList<>();
        futureList.add(futureOne);
        futureList.add(futureTwo);
        //futureList.add(futureThree);

        //anyDoneFuture 结果为 futureOneResult
        CompletableFuture anyDoneFuture = CompletableFuture.anyOf(futureList.toArray(new CompletableFuture[futureList.size()]));
        System.out.println("anyDoneFuture 结果为 " + anyDoneFuture.get());

        //CompletableFuture allDoneFuture = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()]));
        //System.out.println("allDoneFuture 结果为 " + allDoneFuture.get());
    }

    /**
     * getNow() 取值方法，这个就比较特殊了。
     * 这个方法是执行这个方法的时候任务执行完了就返回任务的结果，如果任务没有执行完就返回你的入参。
     */
    public static void instanceMethod() throws InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                //模拟耗时任务
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "result";
        });
        String nowResult = future.getNow("nowResult");
        System.out.println("当前结果 " + nowResult);
        // 主线程死之前执行完成
        TimeUnit.SECONDS.sleep(4);

        /**
         * 测试结果：当前结果 nowResult
         */
    }

    /**
     * join方法跟线程的join用法差不多。
     */
    public static void instanceMethod2() throws InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                //模拟耗时任务
                TimeUnit.SECONDS.sleep(2);
                System.out.println("one task");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "result";
        });
        //future.join 将主线程阻塞住
        future.join();
        System.out.println("test");
        // 主线程死之前执行完成
        TimeUnit.SECONDS.sleep(4);

        /**
         * 测试结果：
         * one task
         * test
         */
    }

    /**
     * whenXXX，在一个任务执行完成之后调用的方法。
     *
     * 这个有三个名差不多的方法。whenComplete、whenCompleteAsync、还有一个是 whenCompleteAsync 用自定义 Executor
     */
    public static void whenMethod() throws InterruptedException {
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("this is first task");
            //异常
            int a = 1 / 0;
            return "first";
        });
        //Thread.sleep(400);
        completableFuture.whenComplete((s, e) -> {
            try {
                Thread.sleep(100);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            // s , e 会有个 null 。表示结果和异常
            System.out.println("正确完成输出结果 " + s);
            System.out.println(e);
        });
        // 注意 test 的输出顺序
        System.out.println("test");
        Thread.sleep(1000);

        /**
         * 首先看一下这个whenComplete实例方法。这个就是任务执行完毕调用的，传入一个action。
         * 这个方法的执行线程是当前线程，意味着会阻塞当前线程。下面图中test的输出跟whenComplete方法运行的线程有关。
         * 运行到main线程就会阻塞test的输出。运行的是completableFuture线程则不会阻塞住test的输出。
         */

        /**
         * 运行结果
         * this is first task
         * 正确完成输出结果 null
         * java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
         * test
         */

        /**
         * 注释掉Thread.sleep(400);
         * this is first task
         * test
         * 正确完成输出结果 null
         * java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
         */

        /**
         * 根据测试得出的结论是：如果调用whenComplete的中途，还发生了其他事情，图中的主线程的sleep(400)；
         * 导致completableFuture这个任务执行完毕了，那么就使用主线程调用。
         * 如果调用的中途没有发生其他任务且在触碰到whenComplete方法时completableFuture这个任务还没有彻底执行完毕
         * 那么就会用completableFuture这个任务所使用的线程。
         */
    }

    public static void whenMethod2() throws InterruptedException {
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("this is first task");
            //异常
            int a = 1 / 0;
            return "first";
        });
        // whenCompleteAsync 异步执行
        completableFuture.whenCompleteAsync((s, e) -> {
            try {
                Thread.sleep(100);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            // s , e 会有个 null 。表示结果和异常
            System.out.println("正确完成输出结果 " + s);
            System.out.println(e);
        });
        // 注意 test 的输出顺序
        System.out.println("test");
        Thread.sleep(1000);

        /**
         * this is first task
         * test
         * 正确完成输出结果 null
         * java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
         */
    }

    /**
     * 这个thenCompose就是一个任务执行完之后可以用它的返回结果接着执行的方法。
     * 方法返回的是另一个你期盼泛型的结果、compose理解就是上一个任务结果是then的一部分。
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void thenMethod() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
           return "first";
        });

        CompletableFuture<Integer> future = completableFuture.thenCompose(s -> CompletableFuture.supplyAsync(() -> {
            System.out.println("上一个任务返回的结果：" + s);
            System.out.println("future" + Thread.currentThread());
            return 1;
        }));
        Thread.sleep(1000);
        System.out.println(future.get());

        /**
         * 上一个任务返回的结果：first
         * futureThread[ForkJoinPool.commonPool-worker-1,5,main]
         * 1
         */
    }

    /**
     * 这个combine的理解就是结合两个任务的结果。
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static void thenCombineMethod() throws InterruptedException, ExecutionException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            return "first";
        });

        Thread.sleep(300);

        CompletableFuture<String> combine = completableFuture.thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "----2");
            try {
                //不加sleep代码还是main线程执行的。
                //加上就是 Thread[ForkJoinPool.commonPool-worker-1,5,main]
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return 2222;
        }), (s1, s2) -> {
            System.out.println(Thread.currentThread());
            return s1 + s2;
        });
        System.out.println(combine.get());

        /**
         * Thread[ForkJoinPool.commonPool-worker-1,5,main]----2
         * Thread[ForkJoinPool.commonPool-worker-1,5,main]
         * first2222
         */
        /**
         * Thread[ForkJoinPool.commonPool-worker-1,5,main]----2
         * Thread[main,5,main]
         * first2222
         */
    }

    /**
     * thenRun 就是这个任务运行完，再运行下一个任务。感觉像是join了一下。
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static void thenRunMethod() throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread());
            try {
                Thread.sleep(4000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "first";
        });

        future.thenRun(() -> {
            System.out.println(Thread.currentThread());
            System.out.println("run task");
        });
        Thread.sleep(6000);

        /**
         * Thread[ForkJoinPool.commonPool-worker-1,5,main]
         * Thread[ForkJoinPool.commonPool-worker-1,5,main]
         * run task
         */
    }

}


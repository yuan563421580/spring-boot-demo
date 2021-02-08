package com.yuansb.demo.multi.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 学习 CompletableFuture 使用例子
 */
public class DemoCompletableFuture1 {

    /**
     * CompletableFuture 字面翻译过来，就是“可完成的Future”。
     *  同传统的 Future 相比较，CompletableFuture 能够主动设置计算的结果值（主动终结计算过程，即 completable），
     *  从而在某些场景下主动结束阻塞等待。
     *  而 Future 由于不能主动设置计算结果值，一旦调用 get() 进行阻塞等待，要么当计算结果产生，要么超时，才会返回。
     *
     * CompletableFuture 是如何被主动完成的。调用了 complete 方法。
     * CompletableFuture 的 join() 和 get()
     *      共性：join() 和 get() 方法都是用来获取 CompletableFuture 异步之后的返回值，会阻塞线程。
     *      区别：join() 方法抛出的是 uncheck 异常（即未经检查的异常),不会强制开发者抛出。
     *           get() 方法抛出的是经过检查的异常，ExecutionException, InterruptedException 需要用户手动处理（抛出或者 try catch）。
     *
     * supplyAsync创建：
     *      CompletableFuture.supplyAsync() 也可以用来创建 CompletableFuture 实例。
     *      通过该函数创建的 CompletableFuture 实例会【异步】执行当前传入的计算任务。
     *      在调用端，则可以通过 get() 或 join() 获取最终计算结果。
     *      supplyAsync有两种签名：
     *          · public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
     *            只需传入一个 Supplier 实例（一般使用 lambda 表达式），此时框架会默认使用 ForkJoin 的线程池来执行被提交的任务。
     *          · public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)
     *            可以指定自定义的线程池，然后将任务提交给该线程池执行。
     *
     * runAsync创建：
     *      CompletableFuture.runAsync() 也可以用来创建CompletableFuture实例。
     *      与 supplyAsync() 不同的是，runAsync() 传入的任务要求是 Runnable 类型的，所以没有返回值。
     *      因此，runAsync 适合创建不需要返回值的计算任务。
     *      runAsync()也有两种签名：
     *          · public static CompletableFuture<Void> runAsync(Runnable runnable)
     *          · public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)
     *
     *
     * 常见的使用方式：
     *  同 Future 相比，CompletableFuture 最大的不同是支持流式（Stream）的计算处理，多个任务之间，可以前后相连，从而形成一个计算流。
     *    比如：任务 1 产生的结果，可以直接作为任务 2 的入参，参与任务2的计算，以此类推。
     *
     * CompletableFuture 中常用的流式连接函数包括：
     *  其中，带 Async 后缀的函数表示需要连接的后置任务会被单独提交到线程池中，从而相对前置任务来说是异步运行的。除此之外，两者没有其他区别。
     *  · thenApply 和 thenApplyAsync
     *  · thenAccept 和 thenAcceptAsync
     *  · thenRun 和 thenRunAsync
     *  · thenCombine 和 thenCombineAsync
     *  · thenCompose 和 thenComposeAsync
     *  · whenComplete 和 whenCompleteAsync
     *  · handle 和 handleAsync
     *  ~ thenApply / thenAccept / thenRun放在一起，这几个连接函数之间的【唯一区别】是提交的任务类型不一样：
     *      · thenApply 提交的任务类型需遵从 Function 签名，也就是有入参和返回值，其中入参为前置任务的结果。
     *      · thenAccept 提交的任务类型需遵从 Consumer 签名，也就是有入参但是没有返回值，其中入参为前置任务的结果。
     *      · thenRun 提交的任务类型需遵从 Runnable 签名，即没有入参也没有返回值。
     *      需要注意的是，通过 thenApply / thenAccept / thenRun 连接的任务，当且仅当前置任务计算完成时，才会开始后置任务的计算。
     *      因此，这组函数主要用于连接前后有依赖的任务链。
     *  ~ thenCombine : 同前面一组连接函数相比，thenCombine 最大的不同是连接任务可以是一个独立的 CompletableFuture
     *    （或者是任意实现了 CompletionStage 的类型），从而允许前后连接的两个任务可以并行执行（后置任务不需要等待前置任务执行完成），
     *    最后当两个任务均完成时，再将其结果同时传递给下游处理任务，从而得到最终结果。
     *    一般，在连接任务之间互相不依赖的情况下，可以使用 thenCombine 来连接任务，从而提升任务之间的并发度。
     *    注意：thenAcceptBoth、thenAcceptBothAsync、runAfterBoth、runAfterBothAsync的作用与 thenCombine 类似，
     *          唯一不同的地方是任务类型不同，分别是BiConsumer、Runnable。
     *  ~ thenCompose : 用于两个任务之间有前后依赖关系，但是连接任务又是独立的 CompletableFuture。
     *      thenCompose 方式的主要目的就是解决：将这种嵌套模式展开，使其没有那么多层级。
     *      （也可以将 thenCompose 的作用类比于 stream 接口中的 flatMap，因为他们都可以将类型嵌套展开）。
     *  ~ whenComplete : 主要用于注入任务完成时的回调通知逻辑。这个解决了传统 future 在任务完成时，无法主动发起通知的问题。
     *      前置任务会将计算结果或者抛出的异常作为入参传递给回调通知函数。
     *  ~ handle : handle 与 whenComplete 的作用有些类似，但是 handle 接收的处理函数有返回值，而且返回值会影响最终获取的计算结果。
     *
     * 取多个任务的结果 ：两个非常简单的静态方法：allOf() 和 anyOf() 方法。
     *  ~ allOf 聚合了多个 CompletableFuture 实例，所以它是没有返回值的。这也是它的一个缺点。
     *  ~ anyOf 也非常容易理解，就是只要有任意一个 CompletableFuture 实例执行完成就可以了。
     *
     * either 方法 ：如果 anyOf(...) 只需要处理两个 CompletableFuture 实例，那么也可以使用 xxxEither() 来处理。
     *      实际测试要求两个 CompletableFuture<T> 中的泛型保持一致，否则会报错。
     */


    public static void main(String[] args) {
        test12();
    }


    /**
     * CompletableFuture 是如何被主动完成的例子
     *
     * 调用了 complete 方法，最终的打印结果是“manual test”，而不是"test"。
     */
    public static void test1() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
           try {
               Thread.sleep(1000L);
               return "test";
           } catch (Exception e) {
               return "failed test";
           }
        });
        future.complete("manual test");
        System.out.println(future.join());
    }

    /**
     * 构造函数创建
     *
     * 由于新创建的 CompletableFuture 还没有任何计算结果，这时调用 join，当前线程会一直阻塞在这里。
     */
    public static void test2() {
        CompletableFuture<String> future = new CompletableFuture<>();
        String result = future.join();
        System.out.println(result);
    }

    /**
     * 使用 supplyAsync 创建 CompletableFuture 的示例。
     *
     *  异步任务中会打印出 “compute test”，并返回"test"作为最终计算结果。
     *  所以，最终的打印信息为 “get result: test”。
     */
    public static void test3() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("compute test");
            return "test";
        });
        String result = future.join();
        System.out.println("get result : " + result);
    }

    /**
     * 使用 runAsync() 创建 CompletableFuture 的示例。
     *
     *  异步任务中会打印出 “compute test”，
     *  由于任务没有返回值，所以最后的打印结果是 "get result: null"。
     */
    public static void test4() {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("compute test");
        });
        System.out.println("get result : " + future.join());
    }

    /**
     * thenApply 测试例子。
     *
     * future1 通过调用 thenApply 将后置任务连接起来，并形成 future2。
     * 该示例的最终打印结果为 result : 11，
     *
     * 可见程序在运行中，future1 的结果计算出来后，会传递给通过 thenApply 连接的任务，从而产生 future2 的最终结果为 1+10=11。
     * 当然，在实际使用中，我们理论上可以无限连接后续计算任务，从而实现链条更长的流式计算。
     */
    public static void test5() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("compute 1");
            return 1;
        });
        CompletableFuture<Integer> future2 = future1.thenApply((p) -> {
            System.out.println("compute 2");
            return p + 10;
        });
        System.out.println("result : " + future2.join());
    }

    /**
     * thenCombine 测试例子。
     *
     * future1 和 future2 为独立的 CompletableFuture 任务，他们分别会在各自的线程中并行执行，
     * 然后 future1 通过 thenCombine 与 future2 连接，
     * 并且以 lambda 表达式传入处理结果的表达式，该表达式代表的任务会将 future1 与 future2 的结果作为入参并计算他们的和。
     *
     * 因此，上面示例代码中，最终的打印结果是result : 11。
     */
    public static void test6() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("compute 1");
            return 1;
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("compute 2");
            return 10;
        });
        CompletableFuture<Integer> futureR = future1.thenCombine(future2, (r1, r2) -> r1 + r2);
        System.out.println("result : " + futureR.join());
    }

    /**
     * thenCompose 测试例子。
     *
     * 使用 thenApply 来实现 用作对比
     *      future2 的类型变成了 CompletableFuture 嵌套，而且在获取结果的时候，也需要嵌套调用join或者get。
     *      这样，当连接的任务越多时，代码会变得越来越复杂，嵌套获取层级也越来越深。
     *
     * 使用 thenCompose 来实现
     *      使用了 thenCompose 后，future2 不再存在 CompletableFuture 类型嵌套了，从而比较简洁的达到了我们的目的。
     *      thenCompose 方式的主要目的就是解决：将这种嵌套模式展开，使其没有那么多层级。
     */
    public static void test7() {

        // 使用 thenApply 来实现
        /*CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("compute 1");
            return 1;
        });
        CompletableFuture<CompletableFuture<Integer>> future2 =
                future1.thenApply((r) -> CompletableFuture.supplyAsync(() -> r + 10));
        System.out.println(future2.join().join());*/


        // 使用 thenCompose 来实现
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("compute 1");
            return 1;
        });
        CompletableFuture<Integer> future2 =
                future1.thenCompose((r) -> CompletableFuture.supplyAsync(() -> r + 10));
        System.out.println(future2.join());
    }

    /**
     * whenComplete 测试例子。
     *
     *  返回结果：1
     *  future2 获得的结果是前置任务的结果，whenComplete中的逻辑不会影响计算结果。
     */
    public static void test8() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("compute 1");
            return 1;
        });
        CompletableFuture<Integer> future2 = future1.whenComplete((r, e) -> {
            if (e != null) {
                System.out.println("compute failed");
            } else {
                System.out.println("received result is : " + r);
            }
        });
        System.out.println(future2.join());
    }

    /**
     * handle 测试例子。
     *
     *  返回结果：11
     *  说明经过handle计算后产生了新的结果。
     */
    public static void test9() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("compute 1");
            return 1;
        });
        CompletableFuture<Integer> future2 = future1.handle((r, e) -> {
            if (e != null) {
                System.out.println("compute failed");
                return r;
            } else {
                System.out.println("received result is : " + r);
                return r + 10;
            }
        });
        System.out.println(future2.join());
    }

    /**
     * allOf() 方法例子。
     *
     * 返回结果是 null 。
     */
    public static void test10() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "resultA");
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 666);
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "resultC");
        CompletableFuture<Void> futureR = CompletableFuture.allOf(future1, future2, future3);
        // 所以这里的 join() 将阻塞，直到所有的任务执行结束
        System.out.println(futureR.join());
    }

    /**
     * anyOf() 方法例子。
     * 顺便测试 get() 方法的例子。需要手动抛出异常。
     *
     * 返回结果是 resultA 。
     */
    public static void test11() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "resultA");
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 666);
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "resultC");
        CompletableFuture<Object> futureR = CompletableFuture.anyOf(future1, future2, future3);
        try {
            Object obj = futureR.get();
            System.out.println(obj);
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    /**
     * either 方法测试例子。
     *
     * 返回结果111
     */
    public static void test12() {

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 111);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 666);
        CompletableFuture<Integer> future = future1.applyToEither(future2, result -> result);
        System.out.println(future.join());

    }

}

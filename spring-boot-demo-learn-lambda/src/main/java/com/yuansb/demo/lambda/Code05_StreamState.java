package com.yuansb.demo.lambda;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * stream 中间操作：有状态操作
 *      增加并行测试例子
 */
public class Code05_StreamState {

    public static void main(String[] args) {

        // 取前面2个元素
        List<String> limitN = Stream.of("Monkey", "Lion", "Giraffe", "Lemur")
                .limit(2)
                .collect(Collectors.toList());
        // [Monkey, Lion]
        System.out.println(limitN);

        // 跳过前面2个元素
        List<String> skipN = Stream.of("Monkey", "Lion", "Giraffe", "Lemur")
                .skip(2)
                .collect(Collectors.toList());
        // [Giraffe, Lemur]
        System.out.println(skipN);

        // 去掉重复的元素
        List<String> uniqueAnimals = Stream.of("Monkey", "Lion", "Giraffe", "Lemur", "Lion")
                .distinct()
                .collect(Collectors.toList());
        // [Monkey, Lion, Giraffe, Lemur]
        System.out.println(uniqueAnimals);

        // 将元素排序的操作
        List<String> alphabeticOrder = Stream.of("Monkey", "Lion", "Giraffe", "Lemur")
                .parallel()
                .sorted()
                .collect(Collectors.toList());
        // [Giraffe, Lemur, Lion, Monkey]
        System.out.println(alphabeticOrder);

        Stream.of("Monkey", "Lion", "Giraffe", "Lemur")
                .parallel()
                .sorted()
                .collect(Collectors.toList());

        /**
         * 并行操作不要进行有状态的相关操作
         *  当不写的时候，默认是串行的：.sequential()
         *  并行需要写 .parallel() , 当元素很多时有状态操作如果并行结果会不准确
         *      Stream.of("Monkey", "Lion", ... , "Giraffe", "Lemur")
         *                 .parallel()
         *                 .sorted()
         *                 .collect(Collectors.toList());
         *
         * 通常情况下，parallel()能够很好的利用 CPU 的多核处理器，达到更好的执行效率和性能，建议使用。
         * 但是有些特殊的情况下，parallel 并不适合。
         *
         *  ·数据源易拆分：从处理性能的角度，parallel()更适合处理 ArrayList，而不是 LinkedList。
         *              因为 ArrayList 从数据结构上讲是基于数组的，可以根据索引很容易的拆分为多个。
         *  ·适用于无状态操作：每个元素的计算都不得依赖或影响任何其他元素的计算的运算场景。
         *  ·基础数据源无变化：从文本文件里面边读边处理的场景，不适合parallel()并行处理。
         *              parallel()一开始就容量固定的集合，这样能够平均的拆分、同步处理。
         */

        long count = Stream.of(1, 11, 99, 22, 8, 19, 3, 31, 6, 47, 15, 2, 7, 18, 26, 14, 42)
                .parallel()
                .filter(n -> n >= 15)
                .count();
        System.out.println(count);

    }

}

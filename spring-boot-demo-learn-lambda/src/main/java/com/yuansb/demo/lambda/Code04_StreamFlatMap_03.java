package com.yuansb.demo.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * stream 中间操作：无状态操作
 */
public class Code04_StreamFlatMap_03 {

    public static void main(String[] args) {
        List<String> worlds = Arrays.asList("hello", "world");

        worlds.stream()
                .map(w -> Arrays.stream(w.split("")))
                .forEach(System.out::println);

        /*worlds.stream()
                .map(w -> w.split(""))
                .forEach(System.out::println);*/

        /**
         * 打印结果为2个管道流
         * java.util.stream.ReferencePipeline$Head@7699a589
         * java.util.stream.ReferencePipeline$Head@58372a00
         * 打印结果为2个数组
         * [Ljava.lang.String;@7699a589
         * [Ljava.lang.String;@58372a00
         *
         *  是因为 worlds 是一个数组，w.split("") 之后还是一个数组，数组里面包含数组，
         *  相当于一个二位数组 List<List<String>>
         * 结论：管道流中还有管道流（数组中还有数组）时 map() 无法进行处理
         */

        /**
         * 使用 flatMap() 解决这个问题
         * flatMap() 可以帮助我们处理多维数组
         * h
         * e
         * l
         * l
         * o
         * w
         * o
         * r
         * l
         * d
         */
        worlds.stream()
                .flatMap(w -> Arrays.stream(w.split("")))
                .forEach(System.out::println);
    }

}

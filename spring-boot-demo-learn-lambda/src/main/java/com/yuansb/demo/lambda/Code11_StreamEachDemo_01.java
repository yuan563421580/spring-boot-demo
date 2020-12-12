package com.yuansb.demo.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * ForEach 和 ForEachOrdered
 */
public class Code11_StreamEachDemo_01 {

    /**
     * 只是希望将Stream管道流的处理结果打印出来，而不是进行类型转换，
     * 我们就可以使用forEach()方法或forEachOrdered()方法。
     *
     * 用例结论：
     *  · parallel() 函数表示对管道中的元素进行并行处理，而不是串行处理，这样处理速度更快。
     *      但是这样就有可能导致管道流中后面的元素先处理，前面的元素后处理，也就是元素的顺序无法保证
     *  · forEachOrdered 从名字上看就可以理解，虽然在数据处理顺序上可能无法保障，
     *      但是 forEachOrdered 方法可以在元素输出的顺序上保证与元素进入管道流的顺序一致。
     */
    public static void main(String[] args) {

        List<String> strings = Arrays.asList("Monkey", "Lion", "Giraffe", "Lemur", "Lion");

        /**
         * Giraffe
         * Lion
         * Lemur
         * Lion
         * Monkey
         */
        strings.stream()
                .parallel()
                .forEach(System.out::println);

        /**
         * Monkey
         * Lion
         * Giraffe
         * Lemur
         * Lion
         */
        strings.stream()
                .parallel()
                .forEachOrdered(System.out::println);

    }

}

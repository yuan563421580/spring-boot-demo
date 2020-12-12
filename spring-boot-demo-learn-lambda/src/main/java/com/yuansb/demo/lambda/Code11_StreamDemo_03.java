package com.yuansb.demo.lambda;

import java.util.IntSummaryStatistics;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 其他常用方法
 */
public class Code11_StreamDemo_03 {

    public static void main(String[] args) {

        /***判断管道中是否包含2，结果是: true*/
        boolean containsTwo = IntStream.of(1, 2, 3)
                .anyMatch(i -> i == 2);
        System.out.println(containsTwo);

        /***管道中元素数据总计count()，结果是: 4*/
        long count = Stream.of("Monkey", "Lion", "Giraffe", "Lemur")
                .count();
        System.out.println(count);

        /***管道中元素数据累加sum()，结果是: 6*/
        int sum = IntStream.of(1, 2, 3)
                .sum();
        System.out.println(sum);

        /***管道中元素数据平均值average()，结果是: OptionalDouble[2.0]*/
        OptionalDouble average = IntStream.of(1, 2, 3)
                .average();
        System.out.println(average);

        /***管道中元素数据最大值max()，结果是: OptionalInt[3]*/
        OptionalInt max = IntStream.of(1, 2, 3)
                .max();
        System.out.println(max);

        /***全面的统计结果summaryStatistics(): IntSummaryStatistics{count=3, sum=6, min=1, average=2.000000, max=3}*/
        IntSummaryStatistics intSummaryStatistics = IntStream.of(1, 2, 3)
                .summaryStatistics();
        System.out.println(intSummaryStatistics);
    }

}

package com.yuansb.demo.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * stream 中间操作：无状态操作
 */
public class Code04_StreamMap_01 {

    public static void main(String[] args) {

        /**
         * map()函数
         *  主要作用将数据是从一种格式转换成另外一种格式，或者从一种类型转换成另外一种类型，
         *  是针对集合类中的元素进行操作。
         */

        List<String> alpha = Arrays.asList("Monkey", "Lion", "Giraffe", "Lemur");

        // 不使用 stream 管道流的写法
        List<String> alphaUser = new ArrayList<>();
        for (String s : alpha) {
            alphaUser.add(s.toUpperCase());
        }
        //[MONKEY, LION, GIRAFFE, LEMUR]
        System.out.println(alphaUser);

        //使用 stream 管道流的写法
        List<String> collectAlphaUser = alpha.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        //[MONKEY, LION, GIRAFFE, LEMUR]
        System.out.println(collectAlphaUser);

        /**
         * String::toUpperCase 方法引用
         * 可以改成写法 s -> s.toUpperCase()
         * 就是对集合中的每一个元素进行处理
         * 如果复杂操作可以 s -> { s.doFirst(); s.doSecond(); ... }
         */
        List<String> collectAlphaUser1 = alpha.stream()
                .map(s -> s.toUpperCase())
                .collect(Collectors.toList());

        /**
         * 流 mapToInt() 是一个中间操作 ，取字符串的长度
         *   mapToDouble()
         *   mapToLong()
         *   mapToObj()
         */
        Stream.of("Monkey", "Lion", "Giraffe", "Lemur")
                .mapToInt(String::length)
                .forEach(System.out::println);

    }

}

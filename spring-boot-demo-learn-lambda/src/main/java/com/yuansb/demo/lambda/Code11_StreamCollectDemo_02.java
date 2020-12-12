package com.yuansb.demo.lambda;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;

/**
 * 元素的收集collect
 */
public class Code11_StreamCollectDemo_02 {

    /**
     * java Stream 最常见的用法就是：
     *  一、将集合类转换成管道流，二、对管道流数据处理，三、将管道流处理结果在转换成集合类。
     * 那么collect()方法就为我们提供了这样的功能：将管道流处理结果在转换成集合类。
     *
     *  · Collectors.toSet() 方法收集 Stream 的处理结果，将所有元素收集到 Set 集合中。
     *  · Collectors.toList() 方法收集 Stream 的处理结果，将所有元素收集到 List 集合中。
     *  · Collectors.toCollection() 方法通用的收集方式
     *      Collectors.toCollection(LinkedList::new) 实际是调用 LinkedList 的构造函数，将元素收集到 LinkedList。
     *      还可以使用 LinkedHashSet::new , PriorityQueue::new
     *  · toArray(String[]::new) 方法收集 Stream 的处理结果，将所有元素收集到字符串数组中。
     *  · Collectors.toMap() 方法将数据元素收集到 Map 里面，
     *      toMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper)
     *      但是出现一个问题：那就是管道中的元素是作为 key，还是作为 value。
     *      我们用到了一个 Function.identity() 方法，该方法很简单就是返回一个 “ t -> t ”（元素输入就是输出的 lambda 表达式）。
     *      另外使用管道流处理函数 distinct() 来确保 Map 键值的唯一性。
     *  · Collectors.groupingBy() 用来实现元素的分组收集，groupingBy 第一个参数作为分组条件，第二个参数是子收集器。
     */

    public static void main(String[] args) {

        /*************收集为Set**************/
        // [Monkey, Lion, Giraffe, Lemur]
        Set<String> collectToSet = Stream.of("Monkey", "Lion", "Giraffe", "Lemur", "Lion")
                .collect(Collectors.toSet());
        System.out.println(collectToSet);

        /*************收集到List**************/
        // [Monkey, Lion, Giraffe, Lemur, Lion]
        List<String> collectToList = Stream.of("Monkey", "Lion", "Giraffe", "Lemur", "Lion")
                .collect(Collectors.toList());
        System.out.println(collectToList);

        /*************通用的收集方式**************/
        // [Monkey, Lion, Giraffe, Lemur, Lion]
        LinkedList<String> collectToCollection = Stream.of("Monkey", "Lion", "Giraffe", "Lemur", "Lion")
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(collectToCollection);

        /*************收集到Array**************/
        /**
         * Monkey
         * Lion
         * Giraffe
         * Lemur
         * Lion
         */
        String[] toArray = Stream.of("Monkey", "Lion", "Giraffe", "Lemur", "Lion")
                .toArray(String[]::new);
        Arrays.stream(toArray).forEach(System.out::println);

        /*************收集到Map**************/
        // distinct() 来确保 Map 键值的唯一性
        // Function.identity() 解释为 元素输入就是输出，作为key
        // s -> (int) s.chars().distinct().count() 解释为 输入元素的不同的字母个数，作为value
        Map<String, Integer> collectToMap = Stream.of("Monkey", "Lion", "Giraffe", "Lemur", "Lion")
                .distinct()
                .collect(Collectors.toMap(
                        Function.identity(),
                        s -> (int) s.chars().distinct().count()
                ));
        // {Monkey=6, Lion=4, Lemur=5, Giraffe=6}
        System.out.println(collectToMap);

        /*************分组收集groupingBy**************/
        // groupingBy第一个参数作为分组条件，第二个参数是子收集器。
        // {G=[Giraffe], L=[Lion, Lemur, Lion], M=[Monkey]}
        Map<Character, List<String>> groupingByList = Stream.of("Monkey", "Lion", "Giraffe", "Lemur", "Lion")
                .collect(Collectors.groupingBy(
                        s -> s.charAt(0)
                ));
        System.out.println(groupingByList);

        // {G=1, L=3, M=1}
        Map<Character, Long> groupingByList2 = Stream.of("Monkey", "Lion", "Giraffe", "Lemur", "Lion")
                .collect(Collectors.groupingBy(
                        s -> s.charAt(0),
                        counting()
                ));
        System.out.println(groupingByList2);
    }

}

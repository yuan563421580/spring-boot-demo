package com.yuansb.demo.lambda;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * stream 源操作
 */
public class Code02_StreamDemo {

    /**
     * stream() 转换成 流
     *      List列表转换成流：List.stream()
     *      String[]数组转换成流Stream.of(String[])
     * filter() 传 流下的 lambda 表达式
     *      filter(s -> s.startsWith("c")) 过滤 c 开头的
     * map() 对每一个数据进行处理
     *      map(String::toUpperCase) 对过滤后的结果全部转换成大写
     *          :: 代表函数引用：本行代码的实际意义就是调用String的toUpperCase()方法
     * sorted() 进行排序，括号中可以写入排序规则
     * collect() 将流转换成集合类
     *      collect(Collectors.toList()) 转换成 list 进行接收
     */

    public static void main(String[] args) {
        List<String> players = Arrays.asList("kobe", "jamas", "curry", "cyyt");

        for(String player : players) {
            if (player.startsWith("L")) {
                String temp = player.toUpperCase();
            }
        }

        /**
         * ·首先使用 stream() 方法将字符串 List 转换为管道流 Stream
         * ·然后进行管道数据处理操作，先用 fliter 函数过滤所有小写 c 开头的字符串，
         *      然后将管道中的字符串转换为大写字母 toUpperCase，
         *      然后调用 sorted 方法排序。
         *  其中还使用到了lambda表达式和函数引用。
         * ·最后使用 collect 函数进行结果处理，将 java Stream 管道流转换为 List。
         */
        List<String> sortedList = players.stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());

        //[CURRY, CYYT]
        System.out.println(sortedList);


        String[] players1 = {"kobe", "jamas", "curry", "cyyt"};
        /**
         * Stream.of() 用于数组转换成流
         */
        List<String> c = Stream.of(players1)
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
        System.out.println(c);


        /**
         * 文件转换成管道流
         * Files.lines()
         */
        /*try {
            Stream<String> lines = Files.lines(Paths.get("file.txt"));
        } catch (Exception e) {
        }*/

    }

}

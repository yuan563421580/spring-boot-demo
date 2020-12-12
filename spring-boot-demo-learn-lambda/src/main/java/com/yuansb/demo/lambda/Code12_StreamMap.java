package com.yuansb.demo.lambda;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * 使用Stream API对Map元素排序
 */
public class Code12_StreamMap {

    /**
     *  · HashMap的merge()函数
     *      该函数应用场景就是当 Key 重复的时候，如何处理 Map 的元素值。这个函数有三个参数：
     *          参数一：向 map 里面 put 的键。
     *          参数二：向 map 里面 put 的值。
     *          参数三：如果键发生重复，如何处理值。可以是一个函数，也可以写成 lambda 表达式。
     */


    public static void main(String[] args) {

        /**学习一下HashMap的merge()函数*/
        String k = "key";
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>() {{
            put(k, 1);
        }};
        Integer merge = hashMap.merge(k, 2, (oldVal, newVal) -> oldVal + newVal);
        System.out.println(merge);
        /**
         * 上述代码分析：
         *  · 我们首先创建了一个 HashMap，并往里面放入了一个键值为 k:1 的元素。
         *  · 当我们调用 merge 函数，往 map里面放入 k:2 键值对的时候。
         *  · k 键发生重复，就执行后面的 lambda 表达式。表达式的含义是：返回旧值 oldVal 加上新值 newVal
         * 结果：map里面只有一项元素那就是k:3。
         *
         * 说明补充：
         *  其实 lambda 表达式很简单：表示匿名函数，箭头左侧是参数，箭头右侧是函数体。
         *  函数的参数类型和返回值，由代码上下文来确定。
         */

        /***************************************************************************/

        /**按Map的键排序函数*/
        // 创建一个Map，并填入数据
        Map<String, Integer> codes = new HashMap<>();
        codes.put("United States", 1);
        codes.put("Germany", 49);
        codes.put("France", 33);
        codes.put("China", 86);
        codes.put("Pakistan", 92);
        // 按照Map的键进行排序
        LinkedHashMap<String, Integer> sortedMap = codes.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (oldVal, newVal) -> newVal,
                                LinkedHashMap::new
                        )
                );
        /*
        China=86
        France=33
        Germany=49
        Pakistan=92
        United States=1
        */
        sortedMap.entrySet().forEach(System.out::println);

        /**
         * 上述代码分析：
         *  · 首先使用 Map.entrySet().stream() 将 Map 类型转换为 Stream 流类型。
         *  · 然后使用 sorted 方法排序，排序的依据是 Map.Entry.comparingByKey()，也就是按照 Map 的键排序。
         *  · 最后用 collect 方法将 Stream 流转成 LinkedHashMap。
         *    其他参数都好说，重点看第三个参数，就是一个 merge 规则的 lambda 表达式，与 merge 方法的第三个参数的用法一致。
         *    由于本例中没有重复的 key，所以新值旧值随便返回一个即可。
         *
         * 说明补充：
         *   请注意使用 LinkedHashMap 来存储排序的结果以保持顺序。
         *   默认情况下，Collectors.toMap() 返回 HashMap。HashMap 不能保证元素的顺序。
         */

        /**按Map的值排序函数*/
        LinkedHashMap<String, Integer> sortedMap2 = codes.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (oldVal, newVal) -> newVal,
                                LinkedHashMap::new
                        )
                );
        /*United States=1
        France=33
        Germany=49
        China=86
        Pakistan=92*/
        sortedMap2.entrySet().forEach(System.out::println);

        /***************************************************************************/

        /**
         * 使用TreeMap按键排序函数
         *
         *   TreeMap 内的元素是有顺序的，所以利用 TreeMap 排序也是可取的一种方法。
         *   需要做的就是创建一个 TreeMap 对象，并将数据从 HashMap put到 TreeMap 中。
         */
        Map<String, Integer> sortedMapByTree = new TreeMap<>(codes);
        // {China=86, France=33, Germany=49, Pakistan=92, United States=1}
        System.out.println(sortedMapByTree);



    }

}

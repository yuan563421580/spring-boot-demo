package com.yuansb.demo.lambda;

import com.yuansb.demo.lambda.model.Employee;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 排序
 */
public class Code07_SortList {

    public static void main(String[] args) {

        // cities是一个字符串数组。注意london的首字母是小写的。
        List<String> cities = Arrays.asList(
                "Milan",
                "london",
                "San Francisco",
                "Tokyo",
                "New Delhi"
        );
        // [Milan, london, San Francisco, Tokyo, New Delhi]
        System.out.println(cities);

        /**
         * 这个CaseInsensitiveComparator是排序方法的实现类，
         * 在compare中先按照字符串长度取短的那个字符串的长度作为条件，
         * 然后循环判断两个字符串的第一个字符的ASCII码大小，做出递增排序，
         * 如果两个字符串第一个字符的ASCII码一致，则判断第二个字符，
         * 以此类推，通过这种方式将字符串通过首字母的ASCII码进行排序。
         *
         * 按照字母表顺序 ，大小写不敏感
         */
        // [london, Milan, New Delhi, San Francisco, Tokyo]
        cities.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println(cities);

        /**
         * 按照自然顺序排序 ，大小写敏感
         */
        // [Milan, New Delhi, San Francisco, Tokyo, london]
        cities.sort(Comparator.naturalOrder());
        System.out.println(cities);

        /**
         * 排序接口实际就是 Comparator 接口的实现类
         * Comparator 接口可以理解为排序器
         */

        /*****************整数类型List排序********************/

        // [6, 2, 1, 4, 9]
        List<Integer> numbers = Arrays.asList(6, 2, 1, 4, 9);
        System.out.println(numbers);

        // 自然排序 [1, 2, 4, 6, 9]
        numbers.sort(Comparator.naturalOrder());
        System.out.println(numbers);

        // 倒序排序 [9, 6, 4, 2, 1]
        numbers.sort(Comparator.reverseOrder());
        System.out.println(numbers);

        /*****************按对象字段对List<Object>排序********************/

        Employee e1 = new Employee(1,23,"M","Rick","Beethovan");
        Employee e2 = new Employee(2,13,"F","Martina","Hengis");
        Employee e3 = new Employee(3,43,"M","Ricky","Martin");
        Employee e4 = new Employee(4,26,"M","Jon","Lowman");
        Employee e5 = new Employee(5,19,"F","Cristine","Maria");
        Employee e6 = new Employee(6,15,"M","David","Feezor");
        Employee e7 = new Employee(7,68,"F","Melissa","Roy");
        Employee e8 = new Employee(8,79,"M","Alex","Gussin");
        Employee e9 = new Employee(9,15,"F","Neetu","Singh");
        Employee e10 = new Employee(10,45,"M","Naveen","Jain");
        List<Employee> employees = Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10);

        // 按照性别和年龄倒序排序
        // sql 写法 : order by gender desc, age desc
        // sort 写法 : .reversed() 只需要写一次放在最后
        employees.sort(
                Comparator.comparing(Employee::getGender)
                .thenComparingInt(Employee::getAge)
                .reversed()
        );
        employees.forEach(System.out::println);

        /**
         * 用法总结
         *
         * 都是正序：不加 reversed()
         * 都是倒序：在最后面加一个 reversed()
         * 先是倒序（ 加 reversed() ），然后正序
         * 先是正序（ 加 reversed() ），然后倒序（ 加 reversed() ）
         */


    }

}

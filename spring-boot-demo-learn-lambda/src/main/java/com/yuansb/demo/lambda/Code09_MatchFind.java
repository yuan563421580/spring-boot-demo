package com.yuansb.demo.lambda;

import com.yuansb.demo.lambda.model.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * stream 查找和匹配元素
 */
public class Code09_MatchFind {

    /**
     * 对数组或者集合类进行操作的时候，经常会遇到这样的需求，比如：
     *      是否包含某一个“匹配规则”的元素
     *      是否所有的元素都符合某一个“匹配规则”
     *      是否所有元素都不符合某一个“匹配规则”
     *      查找第一个符合“匹配规则”的元素
     *      查找任意一个符合“匹配规则”的元素
     */

    /**
     * 匹配规则函数：
     *  · anyMatch，匹配规则函数：判断 Stream 流中是否包含某一个“匹配规则”的元素。这个匹配规则可以是 lambda 表达式或者谓词。
     *  · allMatch 匹配规则函数：判断是够 Stream 流中的所有元素都符合某一个"匹配规则"。
     *  · noneMatch 匹配规则函数：判断是否 Stream 流中的所有元素都不符合某一个"匹配规则"。
     *
     * 元素查找与Optional：
     *  · isPresent() 将在 Optional 包含值的时候返回 true , 否则返回 false 。
     *  · ifPresent(Consumer block) 会在值存在的时候执行给定的代码块。
     *       Consumer 函数式接口；它让你传递一个接收 T 类型参数，并返回 void 的 Lambda 表达式。
     *  · T get() 会在值存在时返回值，否则出一个 NoSuchElement 异常。
     *  · T orElse(T other) 会在值存在时返回值，否则返回一个默认值。
     *  · findFirst() 用于查找第一个符合“匹配规则”的元素，返回值为 Optional 。
     *  · findAny() 用于查找任意一个符合“匹配规则”的元素，返回值为Optional 。
     */

    public static void main(String[] args) {
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

        /*******判断是否存在年龄大于70的*********/
        // 普通写法
        boolean isExistAgeThen70 = false;
        for (Employee employee : employees) {
            if (employee.getAge() > 70) {
                isExistAgeThen70 = true;
                break;
            }
        }
        System.out.println(isExistAgeThen70);

        // stream 流写法 anyMatch : 表达式
        isExistAgeThen70 = employees.stream().anyMatch(e -> e.getAge() > 70);
        System.out.println(isExistAgeThen70);

        // stream 流写法 anyMatch : 谓词
        isExistAgeThen70 = employees.stream().anyMatch(Employee.ageGreaterThen70);
        System.out.println(isExistAgeThen70);

        /*******判断所有年龄大于10的*********/
        boolean allExistAgeThen10 = employees.stream().allMatch(e -> e.getAge() > 10);
        System.out.println("allExistAgeThen10 : " + allExistAgeThen10);

        /*******不存在年龄小于18的*********/
        boolean isExistAgeLess18 = employees.stream().noneMatch(e -> e.getAge() < 18);
        System.out.println("isExistAgeLess18 : " + isExistAgeLess18);

        /*******查找第一个年龄大于40的*********/
        Optional<Employee> employeeOptional = employees.stream()
                .filter(e -> e.getAge() > 40)
                .findFirst();
        // Employee(id=3, age=43, gender=M, firstName=Ricky, lastName=Martin)
        // 查询不到会报错 ：Exception in thread "main" java.util.NoSuchElementException: No value present
        System.out.println(employeeOptional.get());

        /*******判断是否存在年龄大于40的*********/
        boolean isPresent = employees.stream()
                .filter(e -> e.getAge() > 40)
                .findFirst()
                .isPresent();
        System.out.println(isPresent);

        //Employee(id=3, age=43, gender=M, firstName=Ricky, lastName=Martin)
        employees.stream()
                .filter(e -> e.getAge() > 40)
                .findFirst()
                .ifPresent(e -> System.out.println(e));

        /*******查找第一个年龄大于90的，没有查到返回一个默认值*********/
        Employee employee = employees.stream()
                .filter(e -> e.getAge() > 90)
                .findFirst()
                .orElse(new Employee(0, 0, "F", "", ""));
        // Employee(id=0, age=0, gender=F, firstName=, lastName=)
        System.out.println(employee);

        /*******查找任意一个年龄大于40的*********/
        // parallel() 并行，提升效率
        Optional<Employee> anyOptional = employees.stream()
                //.parallel()
                .filter(e -> e.getAge() > 40)
                .findAny();
        // 串行 : Employee(id=3, age=43, gender=M, firstName=Ricky, lastName=Martin)
        // 并行 : Employee(id=7, age=68, gender=F, firstName=Melissa, lastName=Roy)
        System.out.println(anyOptional.get());

    }

}

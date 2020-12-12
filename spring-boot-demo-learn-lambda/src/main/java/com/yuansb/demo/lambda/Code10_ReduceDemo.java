package com.yuansb.demo.lambda;

import com.yuansb.demo.lambda.model.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 集合元素归约
 */
public class Code10_ReduceDemo {

    /**
     * Stream API 为我们提供了 Stream.reduce 用来实现集合元素的归约。
     * reduce 函数有三个参数：
     *  · Identity 标识：一个元素，它是归约操作的初始值，如果流为空，则为默认结果。
     *  · Accumulator 累加器：具有两个参数的函数：归约运算的部分结果和流的下一个元素。
     *  · Combiner 合并器（可选）：当归约并行化时，或当累加器参数的类型与累加器实现的类型不匹配时，用于合并归约操作的部分结果的函数。
     */

    public static void main(String[] args) {

        /*************Integer类型归约************/
        // reduce 初始值为0，累加器可以是 lambda 表达式，也可以是方法引用。结果为21
        // lambda 表达式
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Integer result = numbers.stream()
                .reduce(0, (subtotal, element) -> subtotal + element);
        System.out.println(result);

        // 方法引用
        result = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.println(result);

        /*************String类型归约************/
        // 结果为 abcde
        List<String> letters = Arrays.asList("a", "b", "c", "d", "e");
        String strResult = letters.stream()
                .reduce("", (partialString, element) -> partialString + element);
        System.out.println(strResult);

        strResult = letters.stream()
                .reduce("", (partialString, element) -> partialString.concat(element));
        System.out.println(strResult);

        strResult = letters.stream()
                .reduce("", String::concat);
        System.out.println(strResult);

        /*************复杂对象归约************/
        // 计算所有的员工的年龄总和。结果为 346
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

        // 通过 map() 取出年龄的流数据（原有为 Employee 流数据）
        Integer sumAge = employees.stream()
                .map(e -> e.getAge())
                .reduce(0, (totalAge, element) -> totalAge + element);
        System.out.println(sumAge);

        // parallel() 并行
        sumAge = employees.stream()
                .parallel()
                .map(Employee::getAge)
                .reduce(0, Integer::sum);
        System.out.println(sumAge);

        // parallelStream() 并行流 合并器。结果为346
        // 对于大数据量的集合元素归约计算，更能体现出 Stream 并行流计算的威力。
        sumAge = employees.parallelStream()
                .map(Employee::getAge)
                .reduce(0, Integer::sum, Integer::sum);
        System.out.println(sumAge);

        /**
         * Combiner合并器的使用
         *  除了使用 map 函数实现类型转换后的集合归约，我们还可以用Combiner合并器来实现，这里第一次使用到了Combiner合并器。
         *  因为 Stream 流中的元素是 Employee，累加器的返回值是 Integer，所以二者的类型不匹配。
         *  这种情况下可以使用 Combiner 合并器对累加器的结果进行二次归约，相当于做了类型转换。
         */

        // 合并器 不加合并器报错，合并器将 Employee 流转换成 Integer 类型
        sumAge = employees.stream()
                .reduce(0, (totalAge, emp) -> totalAge + emp.getAge(), Integer::sum);
        System.out.println(sumAge);
    }

}

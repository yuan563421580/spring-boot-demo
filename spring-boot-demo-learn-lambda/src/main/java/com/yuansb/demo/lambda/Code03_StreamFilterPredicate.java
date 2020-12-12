package com.yuansb.demo.lambda;

import com.yuansb.demo.lambda.model.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * stream 源操作
 */
public class Code03_StreamFilterPredicate {

    public static void main(String[] args) {
        Employee e1 = new Employee(1, 23, "M", "Rick", "Beethovan");
        Employee e2 = new Employee(2, 13, "F", "Martina", "Hengis");
        Employee e3 = new Employee(3, 43, "M", "Ricky", "Martina");
        Employee e4 = new Employee(4, 26, "M", "Jon", "Lowman");
        Employee e5 = new Employee(5, 19, "F", "Cristine", "Maria");
        Employee e6 = new Employee(6, 15, "M", "David", "Feezor");
        Employee e7 = new Employee(7, 68, "F", "Melisa", "Roy");
        Employee e8 = new Employee(8, 79, "M", "Alex", "Gussin");
        Employee e9 = new Employee(9, 15, "F", "Neetu", "Singh");
        Employee e10 = new Employee(10, 45, "M", "Naveen", "Jain");

        List<Employee> employees = Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10);

        //复习 StramDemo1 节内容 获取年龄大于70的男性
        /*List<Employee> employeeList = employees.stream()
                .filter(e -> e.getAge() > 70 && e.getGender().equals("M"))
                .collect(Collectors.toList());*/
        //[Employee(id=8, age=79, gender=M, firstName=Alex, lastName=Gussin)]
        //System.out.println(employeeList);

        /**
         * 本节重点是学习 Predicate 谓语
         *  谓语是对主语的描述或说明，指出“做什么”、“是什么”或“怎么样”
         *  什么是谓词逻辑：WHERE和AND限定了主语employee是什么，那么WHERE和AND语句所代表的逻辑就是谓词逻辑
         *          SELECT * FROM employee WHERE age > 70 AND gender = 'M'
         * Stream<T> filter(Predicate<? super T> predicate);
         *
         * 使用谓词的优点是可以实现复用，名称一目了然
         *  且 逻辑使用.and | 或 逻辑使用.or | 非（取反） 逻辑使用.negate()
         *  谓词的写法实际使用中并不多，但是需要会使用该方式
         */

        // 使用定义谓词方式实现上面代码逻辑
        List<Employee> employeeList1 = employees.stream()
                .filter(Employee.ageGreaterThen70
                        .or(Employee.genderM)
                        .negate())
                .collect(Collectors.toList());
        //[Employee(id=8, age=79, gender=M, firstName=Alex, lastName=Gussin)]
        //[Employee(id=2, age=13, gender=F, firstName=Martina, lastName=Hengis), Employee(id=5, age=19, gender=F, firstName=Cristine, lastName=Maria), Employee(id=7, age=68, gender=F, firstName=Melisa, lastName=Roy), Employee(id=9, age=15, gender=F, firstName=Neetu, lastName=Singh)]
        System.out.println(employeeList1);


    }
}

package com.yuansb.demo.lambda;

import com.yuansb.demo.lambda.model.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * stream 中间操作：无状态操作
 */
public class Code04_StreamMap_02 {

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

        // 将员工的年龄加1，将性别M换成male、性别F换成female
        List<Employee> maped = employees.stream()
                .map(e -> {
                    e.setAge(e.getAge() + 1);
                    e.setGender(e.getGender().equals("M") ? "male" : "female");
                    return e;
                })
                .collect(Collectors.toList());
        //[Employee(id=1, age=24, gender=male, firstName=Rick, lastName=Beethovan), Employee(id=2, age=14, gender=female, firstName=Martina, lastName=Hengis), Employee(id=3, age=44, gender=male, firstName=Ricky, lastName=Martina), Employee(id=4, age=27, gender=male, firstName=Jon, lastName=Lowman), Employee(id=5, age=20, gender=female, firstName=Cristine, lastName=Maria), Employee(id=6, age=16, gender=male, firstName=David, lastName=Feezor), Employee(id=7, age=69, gender=female, firstName=Melisa, lastName=Roy), Employee(id=8, age=80, gender=male, firstName=Alex, lastName=Gussin), Employee(id=9, age=16, gender=female, firstName=Neetu, lastName=Singh), Employee(id=10, age=46, gender=male, firstName=Naveen, lastName=Jain)]
        System.out.println(maped);

        /**
         * peek() 是一种特殊的 map() 函数，入参和返回值是相同的 可以使用，因为e是引用数据类型
         */
        List<Employee> peeked = employees.stream()
                .peek(e -> {
                    e.setAge(e.getAge() + 1);
                    e.setGender(e.getGender().equals("M") ? "male" : "female");
                }).collect(Collectors.toList());
        //[Employee(id=1, age=24, gender=male, firstName=Rick, lastName=Beethovan), Employee(id=2, age=14, gender=female, firstName=Martina, lastName=Hengis), Employee(id=3, age=44, gender=male, firstName=Ricky, lastName=Martina), Employee(id=4, age=27, gender=male, firstName=Jon, lastName=Lowman), Employee(id=5, age=20, gender=female, firstName=Cristine, lastName=Maria), Employee(id=6, age=16, gender=male, firstName=David, lastName=Feezor), Employee(id=7, age=69, gender=female, firstName=Melisa, lastName=Roy), Employee(id=8, age=80, gender=male, firstName=Alex, lastName=Gussin), Employee(id=9, age=16, gender=female, firstName=Neetu, lastName=Singh), Employee(id=10, age=46, gender=male, firstName=Naveen, lastName=Jain)]
        System.out.println(peeked);

        //TODO ? stream() 写法会改变元数据的值

    }

}

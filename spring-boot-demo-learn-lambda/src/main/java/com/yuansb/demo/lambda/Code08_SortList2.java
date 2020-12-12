package com.yuansb.demo.lambda;

import com.yuansb.demo.lambda.model.Employee;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 排序 函数式接口
 * 自定义Comparator排序
 */
public class Code08_SortList2 {

    /**
     * 所谓的函数式接口，实际上就是接口里面只能有一个抽象方法的接口。
     * 上一节 SortList 用到的 Comparator 接口就是一个典型的函数式接口，它只有一个抽象方法 compare。
     *
     * 函数式接口的特点:
     *  · 接口有且仅有一个抽象方法，如 Comparator 接口的抽象方法 compare
     *  · 允许定义静态非抽象方法
     *  · 允许定义默认 defalut 非抽象方法（default方法也是java8才有的，见下文）
     *  · 允许 java.lang.Object 中的 public方法，如方法 equals。
     *  · FunctionInterface 注解不是必须的，如果一个接口符合"函数式接口"定义，那么加不加该注解都没有影响。
     *      加上该注解能够更好地让编译器进行检查。如果编写的不是函数式接口，但是加上了 @FunctionInterface，那么编译器会报错。
     *  甚至可以说：函数式接口是专门为 lambda 表达式准备的，lambda 表达式是只实现接口中唯一的抽象方法的匿名实现类。
     *
     * default关键字:
     *  · 在 java8 之前
     *      接口是不能有方法的实现，所有方法全都是抽象方法
     *      实现接口就必须实现接口里面的所有方法
     *      这就导致一个问题：当一个接口有很多的实现类的时候，修改这个接口就变成了一个非常麻烦的事,需要修改这个接口的所有实现类。
     *  · 在java8中这个问题得到了解决，就是default方法
     *      default 方法可以有自己的默认实现，即有方法体。
     *      接口实现类可以不去实现 default 方法，并且可以使用 default 方法。
     *
     * JDK中的函数式接口举例:
     *  · java.lang.Runnable,
     *  · java.util.Comparator,
     *  · java.util.concurrent.Callable
     *  · java.util.function 包下的接口，如 Consumer、Predicate、Supplier 等
     */

    /**
     * 自定义Comparator排序
     * @param args
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

        /**
         * 自定义一个排序器，实现compare函数（函数式接口Comparator唯一的抽象方法）。
         * 返回 0 表示元素相等，-1 表示前一个元素小于后一个元素，1 表示前一个元素大于后一个元素。
         * 这个规则和java 8之前没什么区别。
         */
        employees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if (o1.getAge().equals(o2.getAge())) {
                    return 0;
                }
                return o1.getAge() - o2.getAge() > 0 ? 1 : -1;
            }
        });
        employees.forEach(System.out::println);

        /**如果以lambda表达式简写。箭头左侧是参数，右侧是函数体，参数类型和返回值根据上下文自动判断。*/

        employees.sort((em1, em2) -> {
            if (em1.getAge().equals(em2.getAge())) {
                return 0;
            }
            return em1.getAge() - em2.getAge() > 0 ? 1 : -1;
        });
        employees.forEach(System.out::println);

        /*Employee(id=2, age=13, gender=F, firstName=Martina, lastName=Hengis)
        Employee(id=6, age=15, gender=M, firstName=David, lastName=Feezor)
        Employee(id=9, age=15, gender=F, firstName=Neetu, lastName=Singh)
        Employee(id=5, age=19, gender=F, firstName=Cristine, lastName=Maria)
        Employee(id=1, age=23, gender=M, firstName=Rick, lastName=Beethovan)
        Employee(id=4, age=26, gender=M, firstName=Jon, lastName=Lowman)
        Employee(id=3, age=43, gender=M, firstName=Ricky, lastName=Martin)
        Employee(id=10, age=45, gender=M, firstName=Naveen, lastName=Jain)
        Employee(id=7, age=68, gender=F, firstName=Melissa, lastName=Roy)
        Employee(id=8, age=79, gender=M, firstName=Alex, lastName=Gussin)*/

    }

}

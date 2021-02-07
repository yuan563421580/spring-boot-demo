package com.yuansb.demo.lambda;

public class Code01_LambdaDemo {

    /**
     * 由第8章节引出的第1章节的结论：（具体可以看第8章节中的描述）
     *
     * lambda表达式只适用于函数式接口
     *  Printer 中 如果有 多个方法则不是函数式接口
     */

    /**
     * java8 引入新的注解，@FunctionalInterface
     * 函数式注解 @FunctionalInterface 添加在一个接口上，主要是编译器检查提示作用。
     *  · 注解的作用是检测自定义 functional 接口是否符合要求，编译器会有错误提示；
     *  · 一个接口符合 functional 的要求，不加这个注解也可以正常使用，建议都加上；
     *  · 有且只能有一个抽象方法但可以有多个非抽象方法，简单说就是接口里面default和static的方法是可以有多个的，其他的方法只能有一个。
     *
     * ---
     *
     * function包中重要接口分析：
     * Consumer (java.util.function.Consumer) :
     *      接收一个输入参数 T 类型并不没有返回值；andThen 看源码可以知道是添加一个其后执行的 Consumer 对象。
     * Function (java.util.function.Function) :
     *      接收一个 T 类型参数，返回一个 R 类型的结果。
     *      需要注意的是 compose\andThen 的传入参数和范围参数规则不同，这里的参数类型稍有不慎就会出错，复杂的链路里面排查 bug 是非常麻烦的事。
     * Predicate (java.util.function.Predicate) :
     *      是一个条件判断接口，接收一个 T 参数，返回一个 boolean 值，默认抽象方法 test(t); and\or\negate\isEqual分别对应逻辑与、或、非、相等操作。
     * Supplier (java.util.function.Supplier) :
     *      是一个只有返回没有参数的接口，只有一个方法 get。
     *
     * Consumer<T> Predicate<T> Supplier<T> Function<T, R> BiFunction<T, U, V>，
     *      这里的T U R V并没有特别的定义只是一种约定，就像驼峰命名和泛型中K V E一样。
     *
     * java8 在这几个基础上封装了很多的延伸接口，如BiConsumer、DoubleConsumer、IntConsumer、LongConsumer、ObjIntConsumer等。
     */

    interface Printer {
        void print(String val);
    }

    public void printSomething(String something, Printer printer) {
        printer.print(something);
    }

    public static void main(String[] args) {
        Code01_LambdaDemo code01LambdaDemo = new Code01_LambdaDemo();
        String something = "test lambda";

        /*
        Printer printer = new Printer() {
            @Override
            public void print(String val) {
                System.out.println(something);
            }
        };
        */

        //进行 printer lambda 表达式写法进化 --->--->--->

        //简化1：接口的匿名实现类、
        // 函数相关简化：参数：写到 -> 左侧，用括号()括起
        //            函数体：写到 -> 右侧，用花括号{}括起，中间为函数体实现的功能代码
        /*Printer printer = (String val) -> {
            System.out.println(val);
        };*/

        //简化2：去掉参数类型
        /*Printer printer = (val) -> {
            System.out.println(val);
        };*/

        //简化3：去掉参数括号()，前提是只有一个参数
        /*Printer printer = val -> {
            System.out.println(val);
        };*/

        //简化4：去掉函数体花括号{}，前提是函数体只有一行代码
        //Printer printer = val -> System.out.println(val);

        //lambdaDemo1.printSomething(something, printer);

        //彻底简化为1行代码
        code01LambdaDemo.printSomething(something, val -> System.out.println(val));


        //结论：lambda表达式的参数类型和返回值类型都可以省略，程序会根据接口的定义和返回值的定义自动的推断lambda表达式传入的数据类型。

        //补充：如果实现的接口不包含任何参数，可以() -> {} 即可
        //例子：void print()  :  () -> {System.out.println("test lambda without param");}

    }
}

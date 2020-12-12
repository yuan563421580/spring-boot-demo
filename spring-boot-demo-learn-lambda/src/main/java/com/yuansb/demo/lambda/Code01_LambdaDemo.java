package com.yuansb.demo.lambda;

public class Code01_LambdaDemo {

    /**
     * 由第8章节引出的第1章节的结论：（具体可以看第8章节中的描述）
     *
     * lambda表达式只适用于函数式接口
     *  Printer 中 如果有 多个方法则不是函数式接口
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

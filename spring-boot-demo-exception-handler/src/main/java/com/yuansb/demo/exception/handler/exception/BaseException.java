package com.yuansb.demo.exception.handler.exception;

import com.yuansb.demo.exception.handler.constant.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 异常基类
 *
 *  @EqualsAndHashCode(callSuper = true) 说明
 *      该注解用于子类对象之间进行比较的时候，不加该注解的影响：子类对象属性值一致，但其继承的父类对象属性值不一致，在比较的时候会出现比较结果不对的情况。
 *
 *      ·此注解会生成 equals(Object other) 和 hashCode() 方法。
 *      ·它默认使用非静态，非瞬态的属性
 *      ·可通过参数 exclude 排除一些属性
 *      ·可通过参数 of 指定仅使用哪些属性
 *      ·它默认仅使用该类中定义的属性且不调用父类的方法
 *
 *      实现 equals, hashCode 方法是在编程生活中再常见不过的一个东西了，那么自然 @EqualsAndHashCode 这个 annotation 就成为了一个非常方便的工具。
 *      默认情况下，被这个 annotation 标注的 class 会用到除了 static，transient 修饰的所有属性作为判断标准，当然和之前的 annotation 一样，
 *      可以使用 exclude 选项除掉不想要的属性。也可以通过callSuper包含父类的equals 和 hashCode。
 *      *当然如果你的 class 没有继承任何其他的 class，你却写了callSuper，那么会收获一个编译报错。
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {

    private Integer code;
    private String message;

    public BaseException(Status status) {
        super(status.getMessage());
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

}

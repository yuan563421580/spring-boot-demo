package com.yuansb.demo.multi.datasource.mybatis.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 实体类
 *
 *  @Data / @NoArgsConstructor / @AllArgsConstructor / @Builder 都是 lombok 注解
 *      @Data 注解的主要作用是提高代码的简洁，使用这个注解可以省去代码中大量的get()、 set()、 toString()等方法。
 *      @NoArgsConstructor 注解在类上，为类提供一个无参的构造方法。
 *      @AllArgsConstructor 注解在类上，为类提供一个全参的构造方法。
 *      @Builder声明实体，表示可以进行Builder方式初始化。
 *  @TableName("multi_user") 是 Mybatis-Plus 注解，主要是当实体类名字和表名不满足 驼峰和下划线互转 的格式时，用于表示数据库表名
 *  @TableId(type = IdType.ID_WORKER) 是 Mybatis-Plus 注解，主要是指定主键类型，这里我使用的是 Mybatis-Plus 基于 twitter 提供的 雪花算法
 */

@Data
@TableName("multi_user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {

    private static final long serialVersionUID = -1923859222295750467L;

    /**
     * 主键
     */
    @TableId(type = IdType.ID_WORKER)
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

}

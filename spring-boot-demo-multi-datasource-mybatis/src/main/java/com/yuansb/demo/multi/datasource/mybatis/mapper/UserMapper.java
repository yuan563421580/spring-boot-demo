package com.yuansb.demo.multi.datasource.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuansb.demo.multi.datasource.mybatis.model.User;

/**
 * 数据访问层
 *  不需要建对应的xml，只需要继承 BaseMapper 就拥有了大部分单表操作的方法了。
 */
public interface UserMapper extends BaseMapper<User> {
}

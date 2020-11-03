package com.yuansb.demo.multi.datasource.mybatis.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuansb.demo.multi.datasource.mybatis.mapper.UserMapper;
import com.yuansb.demo.multi.datasource.mybatis.model.User;
import com.yuansb.demo.multi.datasource.mybatis.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 数据服务层 实现
 *
 * @DS: 注解在类上或方法上来切换数据源，方法上的 @DS 优先级大于类上的 @DS
 * baseMapper: mapper 对象，即 UserMapper， 获得CRUD功能
 * 默认走从库: @DS(value = "slave")在类上，默认走从库，除非在方法在添加@DS(value = "master")才走主库
 */
@Service
@DS("slave")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 类上 {@code @DS("slave")} 代表默认从库，在方法上写 {@code @DS("master")} 代表默认主库
     * @param user
     */
    @DS("master")
    @Override
    public void addUser(User user) {
        baseMapper.insert(user);
    }

}

package com.yuansb.demo.multi.datasource.mybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuansb.demo.multi.datasource.mybatis.model.User;

/**
 * 数据服务层
 */
public interface UserService extends IService<User> {

    /**
     * 添加 User
     *
     * @param user
     */
    void addUser(User user);

}

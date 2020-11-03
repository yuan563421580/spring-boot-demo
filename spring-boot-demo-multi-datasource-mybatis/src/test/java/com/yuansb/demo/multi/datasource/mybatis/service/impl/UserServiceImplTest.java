package com.yuansb.demo.multi.datasource.mybatis.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuansb.demo.multi.datasource.mybatis.DemoMultiDatasourceMybatisApplicationTests;
import com.yuansb.demo.multi.datasource.mybatis.model.User;
import com.yuansb.demo.multi.datasource.mybatis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 测试主从数据源
 */
@Slf4j
public class UserServiceImplTest extends DemoMultiDatasourceMybatisApplicationTests {

    @Autowired
    private UserService userService;

    /**
     * 主从库添加
     */
    @Test
    public void addUser() {
        User userMaster = User.builder().name("主库添加").age(20).build();
        userService.addUser(userMaster);

        User userSlave = User.builder().name("从库添加").age(20).build();
        userService.save(userSlave);
    }

    /**
     * 从库查询
     */
    @Test
    public void testListUser() {
        List<User> list = userService.list(new QueryWrapper<>());
        log.info("【list】= {}", JSONUtil.toJsonStr(list));
    }

}

package com.yuansb.demo.orm.mybatis.plus.controller;


import cn.hutool.core.lang.Assert;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuansb.demo.orm.mybatis.plus.model.OrmUser;
import com.yuansb.demo.orm.mybatis.plus.service.IOrmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * Spring Boot Demo Orm 系列示例表 前端控制器
 * </p>
 *
 * @author yuansb
 * @since 2020-10-10
 */
@RestController
@RequestMapping("/user")
public class OrmUserController {

    @Autowired
    private IOrmUserService userService;

    /**
     * 查询全部列表
     * @return
     */
    @GetMapping(value = "/list")
    public JSONObject testList() {
        List<OrmUser> lists = userService.list();
        JSONObject result = new JSONObject();
        result.put("user", lists);
        return result;
    }

    /**
     * 分页查询
     * @return
     */
    @GetMapping(value = "/page")
    public JSONObject testPage() {
        IPage<OrmUser> user = userService.page(new Page<>(1, 1));
        JSONObject result = new JSONObject();
        result.put("user", user);
        return result;
    }

    /**
     * 分页查询 根据条件查询
     * @return
     */
    @GetMapping(value = "/pageCondition")
    public JSONObject testPageCondition() {
        QueryWrapper<OrmUser> queryWrapper = new QueryWrapper<>();
        //queryWrapper.eq("phone_number", "17300000001");
        //链式
        queryWrapper.like("name", "user").or().eq("phone_number", "18600000001").orderByDesc("id");
        IPage<OrmUser> user = userService.page(new Page<>(1, 2), queryWrapper);
        JSONObject result = new JSONObject();
        result.put("user", user);
        return result;
    }

    /**
     * 根据ID查询记录
     * @param userId
     * @return
     */
    @GetMapping(value = "/byId/{userId}")
    public JSONObject testById(@PathVariable Integer userId) {
        System.out.println("userId: " + userId);
        OrmUser user = userService.getById(userId);
        JSONObject result = new JSONObject();
        result.put("user", user);
        return result;
    }

    /**
     * 测试插入
     * @return
     */
    @GetMapping(value = "/insert")
    public JSONObject testInsert() {
        OrmUser user = new OrmUser();
        user.setName("test_2");
        user.setPassword("6c6bf02c8d5d3d128f34b1700cb1e32c");
        user.setSalt("fcbdd0e8a9404a5585ea4e01d0e4d7a0");
        user.setEmail("test_2@xkcoding.com");
        user.setPhoneNumber("18600000002");
        boolean flag = userService.save(user);

        JSONObject result = new JSONObject();
        result.put("flag", flag+"");
        return result;
    }

    /**
     * 测试修改
     * @return
     */
    @GetMapping(value = "/update")
    public JSONObject testUpdate() {
        OrmUser user = userService.getById(4L);
        Assert.notNull(user);
        user.setName("test_2_new");
        boolean flag = userService.updateById(user);
        Assert.isTrue(flag);
        OrmUser newUser = userService.getById(4L);

        JSONObject result = new JSONObject();
        result.put("flag", flag+"");
        result.put("user", newUser);
        return result;
    }

}

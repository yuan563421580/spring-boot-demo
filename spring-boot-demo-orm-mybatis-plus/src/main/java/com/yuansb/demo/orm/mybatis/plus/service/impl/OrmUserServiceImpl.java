package com.yuansb.demo.orm.mybatis.plus.service.impl;

import com.yuansb.demo.orm.mybatis.plus.model.OrmUser;
import com.yuansb.demo.orm.mybatis.plus.mapper.OrmUserMapper;
import com.yuansb.demo.orm.mybatis.plus.service.IOrmUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Spring Boot Demo Orm 系列示例表 服务实现类
 * </p>
 *
 * @author yuansb
 * @since 2020-10-10
 */
@Service
public class OrmUserServiceImpl extends ServiceImpl<OrmUserMapper, OrmUser> implements IOrmUserService {

}

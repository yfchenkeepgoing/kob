package com.kob.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kob.backend.pojo.User;
import org.apache.ibatis.annotations.Mapper;

// Mybatis-plus帮助我们写了很多增删改查的sql语句，通过继承的方式集成这些语句
@Mapper
public interface UserMapper extends BaseMapper<User> {
}

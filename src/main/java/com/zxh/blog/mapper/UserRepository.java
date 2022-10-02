package com.zxh.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxh.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository extends BaseMapper<User> {
}

package com.zxh.blog.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxh.blog.entity.Animal;
import com.zxh.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository extends BaseMapper<User> {
}

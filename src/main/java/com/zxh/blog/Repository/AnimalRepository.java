package com.zxh.blog.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxh.blog.entity.Animal;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AnimalRepository extends BaseMapper<Animal> {
}

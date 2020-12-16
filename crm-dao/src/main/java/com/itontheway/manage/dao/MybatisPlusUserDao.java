package com.itontheway.manage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itontheway.manage.entity.vo.MybatisPlusUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MybatisPlusUserDao extends BaseMapper<MybatisPlusUser> {

}

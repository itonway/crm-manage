package com.itontheway.manage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itontheway.manage.entity.vo.Org;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: xiegl
 * @desc
 * @Date 2020-11-27 17:32
 */
@Mapper
public interface OrgDao extends BaseMapper<Org> {
}

package com.itontheway.manage.dao;

import com.itontheway.manage.entity.Org;

public interface OrgDao extends B{
    int deleteByPrimaryKey(Long id);

    int insert(Org record);

    int insertSelective(Org record);

    Org selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Org record);

    int updateByPrimaryKey(Org record);
}
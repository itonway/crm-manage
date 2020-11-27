package com.itontheway.manage.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMenuDao extends BaseDao<RoleMenu>{
    List<Menu> findMenuListByRoleIds(@Param("userRoleList") List<UserRole> userRoleList);
}
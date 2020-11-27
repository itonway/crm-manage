package com.itontheway.manage.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleDao extends BaseDao<UserRole> {

    List<UserRole> findUserRoleListByUserId(Long userId);

    List<RoleMenu> findRoleMenuListByRoleIds(@Param("userRoleList") List<UserRole> userRoleList);


}
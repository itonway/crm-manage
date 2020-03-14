package com.itontheway.manage.dao;

import com.itontheway.manage.entity.Menu;
import com.itontheway.manage.entity.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDao extends BaseDao<Menu> {

    List<Menu> findMenuListByRoleIds(@Param("userRoleList") List<UserRole> userRoleList);

}
package com.itontheway.manage.service;

import com.itontheway.manage.entity.Menu;
import com.itontheway.manage.entity.User;
import com.itontheway.manage.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: 公众号 itontheway
 * @description: TODO
 * @date 2020/3/9 12:21
 */
public interface IUserService extends IBaseService<User>{
    List<UserRole> findUserRoleListByUserId(Long userId);
    List<Menu> findMenuListByRoleIds(@Param("userRoleList") List<UserRole> userRoleList);
}

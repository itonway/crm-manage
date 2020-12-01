package com.itontheway.manage.service.impl;

import com.itontheway.manage.dao.MenuDao;
import com.itontheway.manage.dao.UserRoleDao;
import com.itontheway.manage.entity.vo.Menu;
import com.itontheway.manage.entity.vo.User;
import com.itontheway.manage.entity.vo.UserRole;
import com.itontheway.manage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: 公众号 itontheway
 * @description: TODO
 * @date 2020/3/9 12:21
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {
    @Autowired
    UserRoleDao userRoleDao;
    @Autowired
    MenuDao menuDao;

    @Override
    public List<UserRole> findUserRoleListByUserId(Long userId) {
        return userRoleDao.findUserRoleListByUserId(userId);
    }

    @Override
    public List<Menu> findMenuListByRoleIds(List<UserRole> userRoleList) {
        return menuDao.findMenuListByRoleIds(userRoleList);
    }

}

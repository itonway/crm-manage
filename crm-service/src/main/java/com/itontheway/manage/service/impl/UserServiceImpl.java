package com.itontheway.manage.service.impl;

import com.itontheway.manage.dao.MenuDao;
import com.itontheway.manage.dao.UserRoleDao;
import com.itontheway.manage.entity.vo.Menu;
import com.itontheway.manage.entity.vo.User;
import com.itontheway.manage.entity.vo.UserRole;
import com.itontheway.manage.service.IUserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: 公众号 itontheway
 * @description: TODO
 * @date 2020/3/9 12:21
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

    @Resource
    UserRoleDao userRoleDao;

    @Resource
    MenuDao menuDao;

    @Override
    @Cacheable(value = "OperatorLogCache",key = "#userId",
            condition = "#userId != null ")
    public List<UserRole> findUserRoleListByUserId(Long userId) {
        return userRoleDao.findUserRoleListByUserId(userId);
    }

    @Override
    public List<Menu> findMenuListByRoleIds(List<UserRole> userRoleList) {
        return menuDao.findMenuListByRoleIds(userRoleList);
    }

}

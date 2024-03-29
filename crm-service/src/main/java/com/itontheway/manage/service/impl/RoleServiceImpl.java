package com.itontheway.manage.service.impl;

import com.itontheway.manage.common.DataSourceType;
import com.itontheway.manage.entity.vo.Role;
import com.itontheway.manage.service.IRoleService;
import com.itontheway.manage.validator.MyDataSource;
import org.springframework.stereotype.Service;

/**
 * @author: 公众号 itontheway
 * @description: TODO
 * @date 2020/3/9 12:21
 */
@MyDataSource(DataSourceType.SLAVE1)
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements IRoleService {

}

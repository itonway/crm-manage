package com.itontheway.manage.controller;

import com.itontheway.manage.common.DataSourceType;
import com.itontheway.manage.entity.vo.MybatisPlusUser;
import com.itontheway.manage.entity.vo.Role;
import com.itontheway.manage.service.IMybatisPlusUserService;
import com.itontheway.manage.service.IRoleService;
import com.itontheway.manage.validator.MyDataSource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: xiegl
 * @desc 动态数据源
 * @Date 2021-2-5 9:32
 */
@Api(value = "动态数据管理",tags = "动态数据源")
@RestController
@RequestMapping("dynamicDataSource")
public class DynamicDataSourceController {
    @Resource
    IMybatisPlusUserService mybatisPlusUserService;

    @Resource
    IRoleService roleService;

    @RequestMapping(value = "findUserById",method = RequestMethod.GET)
    @ApiOperation(value = "查询用户列表",notes = "查询用户列表",produces = "application/json", httpMethod = "GET")
    @ApiImplicitParams({})
    @MyDataSource(DataSourceType.MASTER)
    public MybatisPlusUser findUserById(){
        MybatisPlusUser user = mybatisPlusUserService.getById(1);
        return user;
    }

    @RequestMapping(value = "findRoleById",method = RequestMethod.GET)
    @ApiOperation(value = "根据角色ID查询角色",notes = "根据角色ID查询角色",produces = "application/json", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", dataType = "int", paramType = "query", required = true)
    })
    public Role findRoleById(Long id){
        Role role = roleService.findById(id);
        return role;
    }

}

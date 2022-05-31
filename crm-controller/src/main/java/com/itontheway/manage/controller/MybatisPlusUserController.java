package com.itontheway.manage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itontheway.manage.entity.vo.MybatisPlusUser;
import com.itontheway.manage.service.IMybatisPlusUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xiegl
 * @desc
 * @Date 2020-12-14 16:13
 */
@Api(value = "MybatisPlus测试",tags = "MybatisPlus测试")
@RestController
@RequestMapping("mybatisPlusUser")
public class MybatisPlusUserController {

    @Resource
    IMybatisPlusUserService mybatisPlusUserService;

    @RequestMapping(value = "findUserList",method = RequestMethod.GET)
    @ApiOperation(value = "查询用户列表",notes = "查询用户列表",produces = "application/json", httpMethod = "GET")
    @ApiImplicitParams({})
    public List<MybatisPlusUser> findUserList(){
        QueryWrapper<MybatisPlusUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",1,2,3);
        List<MybatisPlusUser> list = mybatisPlusUserService.list(queryWrapper);
        return list;
    }


    @RequestMapping(value = "findUserListPage",method = RequestMethod.GET)
    @ApiOperation(value = "分页查询用户列表",notes = "分页查询用户列表",produces = "application/json", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "limit", value = "每页条数", dataType = "int", paramType = "query", required = true)
    })
    public IPage<MybatisPlusUser> findUserListPage(Integer page,Integer limit){
        QueryWrapper<MybatisPlusUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",1,2,3);
        IPage<MybatisPlusUser> iPage = mybatisPlusUserService.page(new Page<>(page, limit), queryWrapper);
        return iPage;
    }

    @RequestMapping(value = "findUserListPage",method = RequestMethod.POST)
    @ApiOperation(value = "批量保存用户信息",notes = "批量保存用户信息",produces = "application/json", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mybatisPlusUser", value = "用户信息", dataType = "object", paramType = "query", required = false)
    })
    public void saveUserPath(){
        MybatisPlusUser mybatisPlusUser = new MybatisPlusUser();
        List<MybatisPlusUser> list = new ArrayList<>();
        list.add(mybatisPlusUser);
        mybatisPlusUserService.saveBatch(list);
    }


}

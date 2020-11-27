package com.itontheway.manage.controller;

import com.alibaba.fastjson.JSON;
import com.itontheway.manage.common.RedisUtils;
import com.itontheway.manage.common.Result;
import com.itontheway.manage.service.IRoleService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 公众号 itontheway
 * @description: 角色管理
 * @date 2020/3/15 12:21
 */
@Api(tags = "角色管理")
@Slf4j
@RestController
@RequestMapping(value = "role")
public class RoleController extends BaseController{
    private static final String ROLE_KEY = "role_"; //角色相关redis key
    @Autowired
    IRoleService roleService;
    @Autowired
    RedisUtils redisUtils;

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/15 17:26
     * @Desc 保存和修改角色
     * @Param [role, bindingResult]
     * @Return void
     **/
    @GetMapping(value = "saveOrUpdateRole")
    @ApiOperation(value = "保存和修改角色",notes = "保存和修改角色",produces = "application/json", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "role", value = "角色", dataType = "object", paramType = "query", required = false)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功", response = Result.class),
            @ApiResponse(code = 500, message = "操作失败", response = Result.class)
    })
    public void saveOrUpdateRole(@Validated Role role, BindingResult bindingResult){
        roleService.saveOrUpdate(role);
        String key = ROLE_KEY + role.getId();
        redisUtils.set(key,role);
    }

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/15 17:35
     * @Desc 根据角色ID查询角色
     * @Param [id]
     * @Return role
     **/
    @RequestMapping(value = "findRoleById",method = RequestMethod.GET)
    @ApiOperation(value = "根据角色ID查询角色",notes = "根据角色ID查询角色",produces = "application/json", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", dataType = "int", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功", response = Result.class),
            @ApiResponse(code = 500, message = "操作失败", response = Result.class)
    })
    public Role findRoleById(Long id){
        String key = ROLE_KEY + id;
        boolean hasKey = redisUtils.hasKey(key);
        if(hasKey){
            String string = JSON.toJSONString(redisUtils.get(key));
            Role role = JSON.parseObject(string, Role.class);
            log.info("从缓存中获取角色:"+role);
            return role;
        }
        Role role = roleService.findById(id);
        redisUtils.set(key,role);
        log.info("从数据库中获取角色:"+role);
        return role;
    }

    @RequestMapping(value = "deleteRoleById",method = RequestMethod.GET)
    @ApiOperation(value = "根据角色ID删除角色",notes = "根据角色ID删除角色",produces = "application/json", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", dataType = "int", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功", response = Result.class),
            @ApiResponse(code = 500, message = "操作失败", response = Result.class)
    })
    public void deleteRoleById(Long id){
        roleService.deleteById(id);
        String key = ROLE_KEY + id;
        boolean hasKey = redisUtils.hasKey(key);
        if(hasKey){
            redisUtils.del(key);
            log.info("从缓存中删除了角色ID:" + id);
        }
    }

}

//package com.itontheway.manage.config;
//
//import com.itontheway.manage.common.Const;
//import com.itontheway.manage.common.EnumUtils;
//import com.itontheway.manage.entity.vo.Menu;
//import com.itontheway.manage.entity.vo.User;
//import com.itontheway.manage.entity.vo.UserRole;
//import com.itontheway.manage.exception.CustomizeException;
//import com.itontheway.manage.service.IUserService;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.collections4.CollectionUtils;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.apache.shiro.util.Assert;
//
//import javax.annotation.Resource;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
///**
// * @author: 公众号 itontheway
// * @description: TODO
// * @date 2020/3/13 20:15
// */
//@Slf4j
//public class ShiroRealm extends AuthorizingRealm {
//
//    @Resource
//    IUserService userService;
//
//    /**
//     * @Author 公众号 itontheway
//     * @Date 2020/3/13 20:16
//     * @Desc 身份认证。即登录通过账号和密码验证登陆人的身份信息
//     *        登录时，调用
//     * @Param [authenticationToken]
//     * @Return org.apache.shiro.authc.AuthenticationInfo
//     **/
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//        String name = token.getUsername();
//        User userParam = new User();
//        userParam.setLoginName(name);
//        User loginUser = userService.findByEntity(userParam);
//        Assert.notNull(loginUser,"【"+name+"】用户不存在");
//        String status = loginUser.getStatus();
//        if(Const.ZERO.equals(status)){
//            throw new CustomizeException(EnumUtils.FAILED.getCode(),"登陆用户【"+loginUser.getLoginName()+"】已被禁用");
//        }
//        //这里要传入User对象，才能在doGetAuthorizationInfo方法中获得对象
//        return new SimpleAuthenticationInfo(loginUser, loginUser.getPassword(), getName());
//    }
//
//    /**
//     * @Author 公众号 itontheway
//     * @Date 2020/3/13 20:16
//     * @Desc 权限认证，即登录过后，每个身份不一定，对应的所能看的页面也不一样
//     *       此方法调用 hasRole,hasPermission的时候才会进行回调.
//     * @Param [principalCollection]
//     * @Return org.apache.shiro.authz.AuthorizationInfo
//     **/
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        Object primaryPrincipal = principals.getPrimaryPrincipal();
//        Assert.notNull(primaryPrincipal,"请登录");
//        User userLogin = (User) primaryPrincipal;
//        Long id = userLogin.getId();
//        User loginUser = userService.findById(id);
//        String status = loginUser.getStatus();
//        if(Const.ZERO.equals(status)){
//            throw new CustomizeException(EnumUtils.FAILED.getCode(),"【"+loginUser.getLoginName()+"】用户已被禁用");
//        }
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        Set<String> roles = new HashSet<>();
//        Set<String> permissions = new HashSet<>();
//        List<UserRole> userRoleList = userService.findUserRoleListByUserId(loginUser.getId());
//        if(CollectionUtils.isEmpty(userRoleList)){
//            log.error("登录人【"+loginUser.getLoginName()+"】用户没有配置对应的角色..");
//            throw new CustomizeException(EnumUtils.FAILED.getCode(),"登录人【"+loginUser.getLoginName()+"】没有配置对应的角色");
//        }
//        for (UserRole userRole : userRoleList) {
//            Long roleId = userRole.getRoleId();
//            roles.add(String.valueOf(roleId));
//        }
//        info.addRoles(roles);//加入角色
//        List<Menu> menuList = userService.findMenuListByRoleIds(userRoleList);
//        if(CollectionUtils.isEmpty(menuList)){
//            log.error("登录人【"+loginUser.getLoginName()+"】对应的角色没有分配菜单权限...");
//            throw new CustomizeException(EnumUtils.FAILED.getCode(),"登录人【"+loginUser.getLoginName()+"】对应的角色没有分配菜单权限");
//        }
//        for (Menu menu : menuList) {
//            String permission = menu.getPermission();
//            permissions.add(permission);
//        }
//        info.addStringPermissions(permissions);
//        return info;
//    }
//}

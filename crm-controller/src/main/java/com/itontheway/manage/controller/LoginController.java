package com.itontheway.manage.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 公众号 itontheway
 * @description: TODO
 * @date 2020/3/13 19:58
 */
@Api(tags = "登录管理")
@Slf4j
@RestController
public class LoginController  extends BaseController{

    @GetMapping("/index")
    public String index() {
        return "登录成功，这是首页...";
    }

    @GetMapping("/error")
    public String error() {
        return "没有权限访问页面，请登录...";
    }

    @GetMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.getSession() != null){
            subject.logout();
        }
        return "退出成功，这是登录页...";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String userName, String password) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        // 执行认证登陆
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return "未知账户";
        } catch (IncorrectCredentialsException ice) {
            return "密码不正确";
        } catch (LockedAccountException lae) {
            return "账户已锁定";
        } catch (ExcessiveAttemptsException eae) {
            return "用户名或密码错误次数过多";
        } catch (AuthenticationException ae) {
            return "用户名或密码不正确！";
        }
        if (subject.isAuthenticated()) {
            return "登录成功";
        } else {
            token.clear();
            return "登录失败";
        }
    }

    @RequiresPermissions("user:show")
    @GetMapping("/showUser")
    public String showUser() {
        return "这是学生信息...";
    }
}

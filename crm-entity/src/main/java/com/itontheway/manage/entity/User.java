package com.itontheway.manage.entity;

import com.itontheway.manage.entity.common.BaseEntity;
import lombok.Data;

/**
 * 用户
 * @author itontheway
 * @since 2020-03-08 22:03:23
 */
@Data
public class User extends BaseEntity {
    //用户名
    private String userName;
    //密码
    private String password;
    //登录名
    private String loginName;
    //年龄
    private String age;
    //性别1男2女
    private String sex;
    //邮箱
    private String email;
    //状态0禁用1可用
    private String status;
    //手机号
    private String mobile;
}
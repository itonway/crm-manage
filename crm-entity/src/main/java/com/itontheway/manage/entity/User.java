package com.itontheway.manage.entity;

import com.itontheway.manage.entity.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author 公众号 itontheway
 * @Date 2020/3/10 20:44
 * @Desc // 用户实体
 **/
@Data
@ApiModel(value = "用户实体类")
public class User extends BaseEntity {
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "登录名")
    private String loginName;
    @ApiModelProperty(value = "年龄")
    private String age;
    @ApiModelProperty(value = "性别1男2女")
    private String sex;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "状态0禁用1可用")
    private String status;
    @ApiModelProperty(value = "手机号")
    private String mobile;
}
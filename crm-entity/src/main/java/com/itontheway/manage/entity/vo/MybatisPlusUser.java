package com.itontheway.manage.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author 公众号 itontheway
 * @Date 2020/3/10 20:44
 * @Desc // 用户实体
 **/
@Data
@ApiModel(value = "用户实体类")
@TableName("t_info_user")
public class MybatisPlusUser implements Serializable {
    private Long id;
    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String userName;
    @ApiModelProperty(value = "登录名")
    @NotNull(message = "登录名不能为空")
    private String loginName;
    @ApiModelProperty(value = "年龄",example = "12")
    @Range(max = 200,min = 0,message = "年龄格式不正确")
    @NotNull(message = "年龄不能为空")
    private Integer age;
    @ApiModelProperty(value = "邮箱")
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
    @ApiModelProperty(value = "性别1男2女")
    private String sex;
    @ApiModelProperty(value = "状态0禁用1可用")
    @TableField(exist = false)
    private String status;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "密码盐值")
    @TableField(exist = false)
    private String salt;
}
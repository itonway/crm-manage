package com.itontheway.manage.entity.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.itontheway.manage.entity.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * 角色
 * @author itontheway
 * @since 2020-03-08 22:08:04
 */
@ApiModel(value = "角色实体类")
@Data
@TableName("t_info_role")
public class Role extends BaseEntity {
    @ApiModelProperty(value = "角色名称")
    @NotBlank(message = "角色名称不能为空")
    @NotEmpty
    @Size(min = 1,max = 50,message = "角色名称不能大于50")
    private String name;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "备注")
    private String remark;
}
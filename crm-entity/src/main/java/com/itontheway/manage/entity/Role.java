package com.itontheway.manage.entity;

import com.itontheway.manage.entity.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 角色
 * @author itontheway
 * @since 2020-03-08 22:08:04
 */
@ApiModel(value = "角色实体类")
@Data
public class Role extends BaseEntity {
    @ApiModelProperty(value = "角色名称")
    @NotBlank(message = "角色名称不能为空")
    @Size(min = 1,max = 50,message = "角色名称不能大于50")
    private String name;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "备注")
    private String remark;
}
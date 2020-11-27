package com.itontheway.manage.entity.vo;

import com.itontheway.manage.common.AutoIncKey;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 角色
 * @author itontheway
 * @since 2020-03-08 22:08:04
 */
@ApiModel(value = "字典实体类")
@Data
public class Dict{
    @AutoIncKey
    @Id
    private long id;
    @ApiModelProperty(value = "字典名称")
    @NotBlank(message = "字典名称不能为空")
    @Size(min = 1,max = 50,message = "字典名称不能大于50")
    private String name;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "备注")
    private String remark;
}
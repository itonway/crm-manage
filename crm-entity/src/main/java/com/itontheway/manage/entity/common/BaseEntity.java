package com.itontheway.manage.entity.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: 公众号 itontheway
 * @description: TODO
 * @date 2020/3/9 12:21
 */
@ApiModel(value = "父类")
@Data
public class BaseEntity  implements Serializable {

    @ApiModelProperty(value = "主键ID")
    private Long id;
    @ApiModelProperty(value = "创建人ID")
    private Long createrId;
    @ApiModelProperty(value = "修改ID")
    private Long updaterId;
    @ApiModelProperty(value = "创建人")
    private String creater;
    @ApiModelProperty(value = "修改人")
    private String updater;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
    @ApiModelProperty(value = "创建时间字符串")
    private String createTimeStr;
    @ApiModelProperty(value = "创建时间结束字符串")
    private String createTimeEnd;
    @ApiModelProperty(value = "修改时间字符串")
    private String updateTimeStr;
}

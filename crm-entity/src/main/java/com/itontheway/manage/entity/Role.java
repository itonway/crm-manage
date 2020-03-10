package com.itontheway.manage.entity;

import com.itontheway.manage.entity.common.BaseEntity;
import lombok.Data;

/**
 * 角色
 * @author itontheway
 * @since 2020-03-08 22:08:04
 */
@Data
public class Role extends BaseEntity {
    //角色名称
    private String name;
    //状态
    private String status;
    //备注
    private String remark;
}
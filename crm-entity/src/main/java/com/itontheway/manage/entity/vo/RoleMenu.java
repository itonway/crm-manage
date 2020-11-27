package com.itontheway.manage.entity.vo;

import com.itontheway.manage.entity.common.BaseEntity;
import lombok.Data;

@Data
public class RoleMenu extends BaseEntity {

    private Long roleId;

    private Long menuId;
}
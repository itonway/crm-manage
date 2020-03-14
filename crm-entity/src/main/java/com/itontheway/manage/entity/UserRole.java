package com.itontheway.manage.entity;

import com.itontheway.manage.entity.common.BaseEntity;
import lombok.Data;

@Data
public class UserRole extends BaseEntity {

    private Long userId;

    private Long roleId;

}
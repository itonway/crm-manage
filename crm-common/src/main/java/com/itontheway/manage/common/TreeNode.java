package com.itontheway.manage.common;

import lombok.Data;

import java.util.List;

/**
 * @Author: xiegl
 * @desc
 * @Date 2020-11-9 17:35
 */
@Data
public class TreeNode {
    private String code;
    private String name;
    private String pCode;
    private String pName;

    private List<TreeNode> childrenList;
}

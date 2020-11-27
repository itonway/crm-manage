package com.itontheway.manage.common;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xiegl
 * @desc list 集合 转换为 tree
 * @Date 2020-11-9 16:59
 */
public class ListConvertTree {
    
    /***
     * @Author: xiegl
     * @desc list to tree
     * @Date 2020-11-9 17:43
     * @Return java.util.List<com.gilite.cn.common.utils.TreeNode>
     * @Param [list]
     */
    public static List<TreeNode> listConvertToTree(List<TreeNode> list){
        List<TreeNode> treeList = new ArrayList<>();
        for (TreeNode treeNode : list) {
            String pCode = treeNode.getPCode();
            if("0".equals(treeNode.getPCode())){
                treeList.add(treeNode);
            }
            List<TreeNode> childrenList = treeNode.getChildrenList();
            for (TreeNode node : list) {
                String pCode1 = node.getPCode();
                if(pCode.equals(pCode1)){
                    if(CollectionUtils.isEmpty(childrenList)){
                        treeNode.setChildrenList(new ArrayList<>());
                    }
                    childrenList.add(node);
                }
            }
        }
        return treeList;
    };
}

package com.jsrf.tree;

import lombok.Data;

/**
 * @author jsrf
 */
@Data
public class TreeNode {
    /**
     * 创建了树的两个分支，声明类型自己本身树，目的是每新建一个分支依旧为树的一个节点
     */
    TreeNode left;
    TreeNode right;
    int data;

    /**
     * data在这里是树节点（或分支）的值
     */
    TreeNode(int data) {
        this.data = data;
        left = right = null;
    }

}

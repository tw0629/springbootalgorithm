package com.tian.algorithm.base_DataStructure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author David Tian
 * @desc
 * @since 2021/8/1 22:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {

    public TreeNode left;

    public TreeNode right;

    public Integer val;

    public TreeNode(Integer val) {
        this.val = val;
    }

    public static TreeNode initBuild(){
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        node1.left=node2;
        node1.right=node3;

        node2.left=node4;
        node2.right=node5;

        node3.left=node6;
        node3.right=node7;

        return node1;
    }
}

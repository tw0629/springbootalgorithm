package com.tian.algorithm.demo;

/**
 * @author David Tian
 * @desc
 * @since 2020-06-02 15:25
 */
/**
 * 二叉树数据结构
 */
public class TreeNode {
    int data;
    TreeNode leftNode;
    TreeNode rightNode;
    public TreeNode() {

    }
    public TreeNode(int d) {
        data=d;
    }

    public TreeNode(TreeNode left,TreeNode right,int d) {
        leftNode=left;
        rightNode=right;
        data=d;
    }

}

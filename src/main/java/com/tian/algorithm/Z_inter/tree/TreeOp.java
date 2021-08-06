package com.tian.algorithm.Z_inter.tree;

import com.tian.algorithm.Z_inter.a_common.TreeNode;

/**
 * @author David Tian
 * @desc
 * @since 2021/8/2 01:19
 */
public class TreeOp {

    /**
     * 查找最近公共祖先
     */
    public int lowestCommonAncestor (TreeNode root, int o1, int o2) {
        // write code here
        if(root==null) {
            return -1;
        }
        if(o1==root.val || o2==root.val) {
            return root.val;
        }

        int left = lowestCommonAncestor(root.left, o1, o2);
        int right = lowestCommonAncestor(root.right,o1,o2);

        if(left ==-1) {
            return right;
        }
        if(right ==-1) {
            return left;
        }

        return root.val;
    }
}

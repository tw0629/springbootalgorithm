package com.tian.algorithm.Z_inter.tree;

import com.tian.algorithm.Z_inter.a_common.TreeNode;

/**
 * @author David Tian
 * @desc
 *
 * 一篇文章带你吃透对称性递归(思路分析+解题模板+案例解读)
 * https://leetcode.cn/problems/shu-de-zi-jie-gou-lcof/solution/yi-pian-wen-zhang-dai-ni-chi-tou-dui-che-uhgs/
 *
 *
 * @since 2021/8/2 01:19
 */
public class TreeOp {

    /**
     * 最近公共祖先
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
        int right = lowestCommonAncestor(root.right, o1, o2);

        //!!!!!!
        if(left ==-1) {
            return right;
        }
        if(right ==-1) {
            return left;
        }

        return root.val;  // !!! 此时root即最近公共祖先
    }

    // 方法二
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left == null) return right;
        if(right == null) return left;
        return root;
    }

    /**
     * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
     *
     * 有效 二叉搜索树定义如下：
     *
     * 节点的左子树只包含 小于 当前节点的数。
     * 节点的右子树只包含 大于 当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }

                // 父大于左子树                            // 父小于右子树
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }

    /**
     * 树的子结构
     *
     * 也包含了 "两个二叉树是否完全相同"
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        //判断根节点，再判断左右节点
        return isSub(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    private boolean isSub(TreeNode A, TreeNode B) {
        //递归终止条件：到达A或B的叶子节点
        //1.如果B为null，说明B都走完了，B都能匹配，返回true
        if (B == null) {
            return true;
        }

        //2.如果A为null，走到这里说明B不为null，则B不能完全匹配，返回false
        //3.A的值与B的值不同，返回false（2与3合并）
        if (A == null || A.val != B.val) {
            return false;
        }

        //走到这里说明A的值与B的值相等，递归各自的左右节点
        return isSub(A.left, B.left) && isSub(A.right, B.right);
    }
    // !!!!!!
    // 优化（上一个方法）
    public boolean isSub2(TreeNode A, TreeNode B){ //这样理解 A:大树一部分  B:小树
        // 先判断B为不为null, 再判断A为不为null
        if(B == null)
            return true;        //子树B遍历到最后的叶子节点
        if(A == null)
            return false;       //已经越过A的叶子节点
        return (A.val == B.val && isSub(A.left, B.left) && isSub(A.right, B.right));
    }
    // https://blog.csdn.net/ly0724ok/article/details/119180148

    /**
     * 两个二叉树是否完全相同
     */
    public boolean isSub3(TreeNode A, TreeNode B){ //这样理解 A:大树一部分  B:小树
        if(A == null && B == null){
            return true;
        }
        if(A == null || B == null){
            return false;
        }
        return (A.val == B.val && isSub(A.left, B.left) && isSub(A.right, B.right));
    }

}

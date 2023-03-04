package com.tian.algorithm.Z_inter.tree;

import com.tian.algorithm.Z_inter.a_common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author David Tian
 * @desc  树的先/中/后序遍历
 * https://www.nowcoder.com/practice/a9fec6c46a684ad5a3abd4e365a9d362?tpId=117&&tqId=37819&rp=1&ru=/ta/job-code-high&qru=/ta/job-code-high/question-ranking
 * @since 2021/8/1 22:44
 */
public class TreeSearch {

    public static void preorder(TreeNode root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            preorder(root.left, list);
            preorder(root.right, list);
        }
    }
    public static void inorder(TreeNode root, List<Integer> list) {
        if (root != null) {
            inorder(root.left, list);
            list.add(root.val);
            inorder(root.right, list);
        }
    }
    public static void postorder(TreeNode root, List<Integer> list) {
        if (root != null) {
            postorder(root.left, list);
            postorder(root.right, list);
            list.add(root.val);
        }
    }

    /* 用stack实现树的遍历

        1 用stack实现树的遍历,顺序左右是反的;
        2 连贯操作
            TreeNode curr = stack.pop();
            list.add(curr.val);
        3 先序遍历和后续遍历相似;
          中序遍历;
    */
    public static void preorderStack(TreeNode root, ArrayList<Integer> list) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            list.add(curr.val);
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
        }
    }

    public static void inorderStack(TreeNode root, ArrayList<Integer> list) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        // 用【while+stack操作】模拟递归
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                //不断往左子树方向走，每走一次就将当前节点保存到栈中
                //这是模拟递归的调用
                stack.push(curr);
                curr = curr.left;
            } else {
                //当前节点为空，说明左边走到头了，从栈中弹出节点并保存
                //然后转向右边节点，继续上面整个过程
                curr = stack.pop();
                list.add(curr.val);
                curr = curr.right;
            }
        }
    }

    public static void postorderStack(TreeNode root, ArrayList<Integer> list) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            list.add(0, curr.val);// 头插法
            if(curr.left != null) {
                stack.push(curr.left);
            }
            if(curr.right != null) {
                stack.push(curr.right);
            }
        }
    }

    public static void main(String[] args) {

        TreeNode treeNode = TreeNode.initBuild();

        ArrayList<Integer> list = new ArrayList<>();
        preorder(treeNode,list);
        System.out.println("preorder:"+list);

        list = new ArrayList<>();
        inorder(treeNode,list);
        System.out.println("inorder:"+list);

        list = new ArrayList<>();
        postorder(treeNode,list);
        System.out.println("postorder:"+list);


        System.out.println("");

        list = new ArrayList<>();
        preorderStack(treeNode,list);
        System.out.println("preorderStack:"+list);

        list = new ArrayList<>();
        inorderStack(treeNode,list);
        System.out.println("inorderStack:"+list);

        list = new ArrayList<>();
        postorderStack(treeNode,list);
        System.out.println("postorderStack:"+list);

        System.out.println("");

    }

}

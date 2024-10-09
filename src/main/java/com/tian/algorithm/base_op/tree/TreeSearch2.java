package com.tian.algorithm.base_op.tree;

import com.tian.algorithm.base_DataStructure.TreeNode2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author David Tian
 * @desc
 * @since 2021/6/16 08:03
 */
public class TreeSearch2 {

    /**
     * 推荐：
     *  广度优先遍历一使用队列实现的
     *  深度优先遍历一递归写法实现的（也是 树的先序遍历）
     */

    // 广度优先遍历是使用队列实现的
    public void broadFirstSearch(TreeNode2 nodeHead) {
        if(nodeHead==null) {
            return;
        }
        Queue<TreeNode2> myQueue=new LinkedList<>();// 双端队列是有顺序的
        myQueue.add(nodeHead);
        while(!myQueue.isEmpty()) {
            // 顺序：头，左，右
            TreeNode2 node=myQueue.poll();
            System.out.print(node.data+" ");
            if(null!=node.leftNode) {
                myQueue.add(node.leftNode);    //深度优先遍历，我们在这里采用每一行从左到右遍历
            }
            if(null!=node.rightNode) {
                myQueue.add(node.rightNode);
            }
        }
    }

    // 深度优先遍历
    public void depthFirstSearch(TreeNode2 nodeHead) {
        if(nodeHead==null) {
            return;
        }
        Stack<TreeNode2> myStack=new Stack<>();
        myStack.add(nodeHead);
        while(!myStack.isEmpty()) {
            TreeNode2 node=myStack.pop();    //弹出栈顶元素
            System.out.print(node.data+" ");
            if(node.rightNode!=null) {
                myStack.push(node.rightNode);    //深度优先遍历，先遍历左边，后遍历右边,栈先进后出
            }
            if(node.leftNode!=null) {
                myStack.push(node.leftNode);
            }
        }

    }

    /**
     * 递归写法一深度优先遍历 (先序遍历)
     * @param node
     */
    public void dfsRecursive(TreeNode2 node){
        if (node!=null)
        {
            System.out.print(node.data+"  ");
            dfsRecursive(node.leftNode);
            dfsRecursive(node.rightNode);
        }
    }

    public static void main(String[] args) {
        TreeNode2 head=new TreeNode2(1);
        TreeNode2 second=new TreeNode2(2);
        TreeNode2 three=new TreeNode2(3);
        TreeNode2 four=new TreeNode2(4);
        TreeNode2 five=new TreeNode2(5);
        TreeNode2 six=new TreeNode2(6);
        TreeNode2 seven=new TreeNode2(7);
        TreeNode2 eight=new TreeNode2(8);
        TreeNode2 nine=new TreeNode2(9);
        head.leftNode=second;
        head.rightNode=three;
        second.leftNode=four;
        second.rightNode=five;
        three.leftNode=six;
        three.rightNode=seven;
        five.leftNode=eight;
        five.rightNode=nine;


        System.out.print("广度优先遍历结果：");
        new TreeSearch2().broadFirstSearch(head);
        System.out.println();
        System.out.print("深度优先遍历结果：");
        new TreeSearch2().depthFirstSearch(head);
        System.out.println();
        System.out.print("递归写法一深度优先遍历结果：");
        new TreeSearch2().dfsRecursive(head);
    }
}

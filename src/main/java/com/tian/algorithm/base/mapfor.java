package com.tian.algorithm.base;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author David Tian
 * @desc
 * @since 2021-05-18 08:52
 */
public class mapfor {

    /**第05讲-广度优先遍历_改造为递归方式
     * ------------------------------------
     * 深度优先遍历的递归相似性很明显。
     * 广度优先遍历可以用递归的解法吗？试把课程中解法改造为递归方式
     * */


    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void main(String[] args) {
        int[][] g = {
                { 0, 1, 1, 0, 0, 0, 0 },
                { 1, 0, 0, 1, 0, 0, 1 },
                { 1, 0, 0, 0, 0, 1, 1 },
                { 0, 1, 0, 0, 1, 0, 0 },
                { 0, 0, 0, 1, 0, 1, 1 },
                { 0, 0, 1, 0, 1, 0, 0 },
                { 0, 1, 1, 0, 1, 0, 0 } };

        Set tag = new HashSet(); 										// 保存已经遍历的节点
        List layer = new ArrayList();									// 等待遍历
        layer.add(0);													// 初始态，从0号开始遍历

        widthTravel(g, tag, layer);										// 调用广度优先遍历方法
    }

    /**
     * 广度优先遍历方发
     * */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public final static void widthTravel(int[][] g, Set tag, List lst) {// 3个参量，邻接表矩阵，标记，待遍历lst
        int node = (Integer) lst.get(0);								// 取得0号元素
        System.out.println(node);										// 立即输出这个元素
        tag.add(node);													// 加入到标记中
        lst.remove(0);													// 同时把该元素从lst中删除

        // 处理这个节点紧跟的所有孩子
        for (int i = 0; i < g[node].length; i++) {						// 第node行所有的元素进行查找
            if (g[node][i] == 1 										// 表示着两个节点间是有连接线的
                    && tag.contains(i) == false							// 排除已经遍历过的节点，在tag中判断是否包含这个节点
                    && lst.indexOf(i) < 0) {							// 不同的节点可能有相同的孩子，判断i节点是否已经在lst中了，<0即=-1，找不到。
                lst.add(i);												// 加入lst中等待遍历
            }
        }
        if (lst.isEmpty() == false) {									// 如果lst不为空，进行递归
            widthTravel(g, tag, lst);									// 递归自己
        }
    }


    //原文链接：https://blog.csdn.net/u011925500/article/details/20220879
}

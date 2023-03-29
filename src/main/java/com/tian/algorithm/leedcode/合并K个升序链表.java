package com.tian.algorithm.leedcode;

import com.tian.algorithm.Z_inter.a_common.ListNode;

/**
 * @author David Tian
 *
 * 字节面试 二轮
 *
 * 链接：https://leetcode.cn/problems/vvXgSW/solution/san-chong-xie-fa-you-jian-dao-nan-by-fen-lbyv/
 * @since 2022/12/13 00:03
 */
public class 合并K个升序链表 {

    /**
     * 合并K个升序链表  和 合并两个升序链表
     *
     */

    public static ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) {
            return null;
        }
        if(lists.length == 1) {
            return lists[0];
        }
        return merge(lists, 0, lists.length - 1);
    }

    // 分治
    public static ListNode merge(ListNode[] lists, int start, int end) {
        if(start == end) {
            return lists[start];
        }
        int mid = start + (end - start >> 1);
        ListNode left = merge(lists, start, mid);
        ListNode right = merge(lists, mid + 1, end);
        return merge2Lists(left, right);
    }

    // 组合
    public static ListNode merge2Lists(ListNode node1, ListNode node2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while(node1 != null && node2 != null) {
            if(node1.data < node2.data) {
                cur.next = node1;
                node1 = node1.next;
                cur = cur.next;
            } else {
                cur.next = node2;
                node2 = node2.next;
                cur = cur.next;
            }
        }
        while(node1 != null) {
            cur.next = node1;
            node1 = node1.next;
            cur = cur.next;
        }
        while(node2 != null) {
            cur.next = node2;
            node2 = node2.next;
            cur = cur.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode listNode1 = ListNode.initBuild();
        ListNode listNode2 = ListNode.initBuild();
        ListNode listNode3 = ListNode.initBuild();
        ListNode[] lists = {listNode1,listNode2,listNode3};

        ListNode node = mergeKLists(lists);
        ListNode.print(node);
        System.out.println();
    }

}

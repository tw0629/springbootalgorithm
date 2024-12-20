package com.tian.algorithm.leedcode;

import com.tian.algorithm.base_DataStructure.ListNode;

import java.util.List;

/**
 * @author David Tian
 * @desc
 * @since 2022/12/18 16:10
 */
public class a23_合并K个升序链表 {
    /*
    给你一个链表数组，每个链表都已经按升序排列。
    请你将所有链表合并到一个升序链表中，返回合并后的链表。

    输入：lists = [[1,4,5],[1,3,4],[2,6]]
    输出：[1,1,2,3,4,4,5,6]

    输入：lists = []
    输出：[]

    输入：lists = [[]]
    输出：[]
    */
    // 推荐
    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode res = null;
        for (ListNode list: lists) {
            res = twoMerge(res, list);
        }
        return res;
    }

    // 同理数组类型也一样 参考归并排序：public static void merge(int[] arr,int low,int mid,int high,int[] tmp)
//    public static int[] mergeKLists(List<int[]> lists) {
//        int[] res = null;
//        for (int[] arr: lists) {
//            //res = twoMergeArray(res, arr);
//        }
//        return res;
//    }

    // 有点问题
    public static ListNode mergeKLists2(ListNode[] lists, int l, int h) {
        if (l >= h) {
            return lists[l];
        }
        int mid = l + (h - l) / 2;
        ListNode l1 = mergeKLists2(lists, l, mid);
        ListNode l2 = mergeKLists2(lists, mid + 1, h);
        return twoMerge(l1, l2);
    }


    /**
     * 先合并两个链表
     */
    private static ListNode twoMerge(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.data < l2.data) {
                tail.next = l1;
                //tail.next = new ListNode(l1.data);
                l1 = l1.next;
            } else {
                tail.next = l2;
                //tail.next = new ListNode(l2.data);
                l2 = l2.next;
            }
            tail = tail.next;
        }

        tail.next = l1 == null? l2: l1;

        return dummyHead.next;
    }
    private static ListNode twoMerge2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        if (l1.data < l2.data) {
            l1.next = twoMerge2(l1.next, l2);
            return l1;
        }else {
            l2.next = twoMerge2(l1, l2.next);
            return l2;
        }
    }

    // 组合
    public static ListNode twoMerge3(ListNode node1, ListNode node2) {
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

        ListNode node2 = mergeKLists2(lists,0,lists.length-1);
        ListNode.print(node2);
        System.out.println();
    }


}

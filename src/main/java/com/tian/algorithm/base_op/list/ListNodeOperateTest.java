package com.tian.algorithm.base_op.list;

import com.tian.algorithm.base_DataStructure.ListNode;

/**
 * @author David Tian
 * @desc
 * @since 2021/8/1 16:13
 */
public class ListNodeOperateTest {

    /**
     * ListNode操作测试
     */

    // 遍历
    public static void op1(ListNode head) {
        ListNode cur = head;
        while(cur != null){
            System.out.print(cur.data + " ");
            ListNode next = cur.next;
            cur = next;
        }

        System.out.println();

        cur = head;
        while(cur != null){
            System.out.print(cur.data + " ");
            cur = cur.next;
        }

        ListNode.print(cur);

    }

    // 第一个
    public static void op2(ListNode head) {
        ListNode res = null;
        head.next = res;
        res = head;

        ListNode.print(res);

    }

    /**
     * 说明他们(变量)都是 指向的是同一个链表
     * @param head
     */
    public static void op3(ListNode head) {
        ListNode.print(head);


        ListNode cur = head;
        cur = cur.next.next; // 向后移动两个

        ListNode nur = cur.next; // 向后移动一个
        nur.next = null; // 向后移动一个

        ListNode.print(head);
        ListNode.print(cur);
        ListNode.print(nur);

    }

    public static void main(String[] args) {
        ListNode head = ListNode.initBuild();
        op1(head);

        ListNode head2 = ListNode.initBuild();
        op2(head2);

        ListNode head3 = ListNode.initBuild();
        op3(head3);
        System.out.println();

    }

}

package com.tian.algorithm.Z_test;

/**
 * @author David Tian
 * @desc
 * @since 2021/8/1 16:13
 */
public class ListNodeOperate2 {

    public static void main(String[] args) {
        ListNode head = ListNode.initBuild();
        op1(head);

        ListNode head2 = ListNode.initBuild();
        op2(head2);

        ListNode head3 = ListNode.initBuild();
        op3(head3);

    }

    public static void op1(ListNode head) {
        ListNode cur = head;
        while(cur != null){
            ListNode next = cur.next;
            cur = next;
        }

        ListNode.print(cur);

    }

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
        cur = cur.next.next;

        ListNode nur = cur.next;
        nur.next = null;

        ListNode.print(head);
        ListNode.print(cur);
        ListNode.print(nur);

    }



}

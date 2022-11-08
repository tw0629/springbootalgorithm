package com.tian.algorithm.leedcode;


import com.tian.algorithm.Z_inter.a_common.ListNode;

/**
 * @author David Tian
 * @desc
 * @since 2022/10/6 18:23
 */
public class a2_两数相加 {

    /**
     * for循环写法
     * note：维护一个进位变量t
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(-1);
        ListNode pre = dummyHead;

        int t = 0;
        while (l1 != null || l2 != null || t != 0) {
            if (l1 != null) {
                t += l1.data;
                l1 = l1.next;
            }
            if (l2 != null) {
                t += l2.data;
                l2 = l2.next;
            }
            pre.next = new ListNode(t % 10);
            pre = pre.next;
            t /= 10;
        }

        return dummyHead.next;
    }

    /**
     * 递归写法
     * note：维护一个变量a
     */
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2, int a) {
        if(l1==null&&l2==null) {
            return a==0 ? null : new ListNode(a);
        }
        if(l1 !=null){a += l1.data;l1 = l1.next;}
        if(l2 !=null){a += l2.data;l2 = l2.next;}
        return new ListNode(a%10, addTwoNumbers2(l1, l2,a/10));
    }

    /**
     * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
     * 输出：[8,9,9,9,0,0,0,1]
     *
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     */
    public static void main(String[] args) {
        ListNode listNode11 = new ListNode(9);
        ListNode listNode12 = new ListNode(9);
        listNode12.next = listNode11;
        ListNode listNode13 = new ListNode(9);
        listNode13.next = listNode12;
        ListNode listNode14 = new ListNode(9);
        listNode14.next = listNode13;
        ListNode listNode15 = new ListNode(9);
        listNode15.next = listNode14;
        ListNode listNode16 = new ListNode(9);
        listNode16.next = listNode15;
        ListNode listNode17 = new ListNode(9);
        listNode17.next = listNode16;

        ListNode listNode21 = new ListNode(9);
        ListNode listNode22 = new ListNode(9);
        listNode22.next = listNode21;
        ListNode listNode23 = new ListNode(9);
        listNode23.next = listNode12;
        ListNode listNode24 = new ListNode(9);
        listNode24.next = listNode23;


        ListNode listNode = addTwoNumbers(listNode17, listNode24);
        System.out.print("    ");
        ListNode.print(listNode17);
        System.out.print("    ");
        ListNode.print(listNode24);
        ListNode.print(listNode);


        ListNode listNode1 = addTwoNumbers2(listNode17, listNode24, 0);
        ListNode.print(listNode24);

    }
}

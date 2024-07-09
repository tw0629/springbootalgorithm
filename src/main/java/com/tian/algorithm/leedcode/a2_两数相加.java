package com.tian.algorithm.leedcode;


import com.tian.algorithm.base_DataStructure.ListNode;
import com.tian.algorithm.base_op.list.ReverseList;

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
        while (l1 != null || l2 != null || t != 0) { // t != 0
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
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2, int t) {
        if(l1==null&&l2==null) {
            return t==0 ? null : new ListNode(t);
        }
        if(l1 !=null){
            t += l1.data;
            l1 = l1.next;
        }
        if(l2 !=null){
            t += l2.data;
            l2 = l2.next;
        }

        //return new ListNode(t%10, addTwoNumbers2(l1, l2,t/10)); 拆成下面
        ListNode nextNode = addTwoNumbers2(l1, l2, t / 10);
        ListNode listNode = new ListNode(t % 10);
        listNode.next = nextNode;
        return listNode;
    }

    /**
     * 输入：l1 = [2,4,5], l2 = [5,6,7]
     * 输出：[8,1,2]
     */
    public static ListNode addTwoNumbers3(ListNode l1, ListNode l2, int a) {
        ListNode resList1 = ReverseList.reverseList(l1);
        ListNode resList2 = ReverseList.reverseList(l2);
        ListNode listNode = addTwoNumbers(resList1, resList2);
        return ReverseList.reverseList(listNode);
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
        ListNode listNode13 = new ListNode(9);
        ListNode listNode14 = new ListNode(9);
        ListNode listNode15 = new ListNode(9);
        ListNode listNode16 = new ListNode(9);
        ListNode listNode17 = new ListNode(9);
        listNode11.next = null;
        listNode12.next = listNode11;
        listNode13.next = listNode12;
        listNode14.next = listNode13;
        listNode15.next = listNode14;
        listNode16.next = listNode15;
        listNode17.next = listNode16;

        ListNode listNode21 = new ListNode(9);
        ListNode listNode22 = new ListNode(9);
        ListNode listNode23 = new ListNode(9);
        ListNode listNode24 = new ListNode(9);
        listNode21.next = null;
        listNode22.next = listNode21;
        listNode23.next = listNode12;
        listNode24.next = listNode23;


        ListNode.print(listNode17);
        System.out.print("    ");
        ListNode.print(listNode24);
        System.out.print("    ");

        ListNode listNode = addTwoNumbers(listNode17, listNode24);
        ListNode.print(listNode);
        System.out.println("    ");

        ListNode listNode1 = addTwoNumbers2(listNode17, listNode24,0);
        ListNode.print(listNode1);
        System.out.println("    ");

        System.out.println("========= addTwoNumbers3 =======");
        // ?? 竟然看不出什么问题来 日后再看
        // ?? 竟然看不出什么问题来 日后再看
        // ?? 竟然看不出什么问题来 日后再看
        ListNode listNode2 = addTwoNumbers3(listNode17, listNode24, 0);
        ListNode.print(listNode2);
        System.out.println();

    }
}

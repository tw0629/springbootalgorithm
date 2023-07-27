package com.tian.algorithm.Z_inter.list;

import com.tian.algorithm.Z_inter.a_common.ListNode;
import com.tian.algorithm.Z_inter.a_common.Node;

/**
 * @author David Tian
 * @desc
 * @since 2021/8/1 20:55
 */
public class ReverseList {

    /**
     * ListNode找中间值
     * （类似：是否有环）
     */
    private ListNode findMid(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next;
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    // 只需要举例一组简单数，即：1->2->3
    // 反转链表
    public static ListNode reverseList(ListNode head) {
        ListNode res = null;//指向当前节点的前驱
        ListNode next = null;//指向当前节点的后驱
        while (head != null) {
            next = head.next;// 1 1和4对应                      // next：2->3  head:1->2->3
            //当前节点的后驱指向前驱
            head.next = res; // 2 改变 head.next 为【新head】    // head:1->res
            res = head; // 3 将【新head】重新 赋给pre             // head:1->res res:1
            //处理下一个节点
            head = next;// 4 1和4对应                           // head:2->3 res:1
        }
        return res;
    }

    //用递归的方法反转链表
    /**
     * 递归过程： head.next.next = head;
     *          head.next = null;
     *
     * 1->2->3->4->5
     *
     * 1->2->3->4
     *          ^
     *          5
     * 1->2->3
     *       ^
     *       4
     *       ^
     *       5
     * 1->2
     *    ^
     *    3
     *    ^
     *    4
     *    ^
     *    5
     *
     * 5 -> 4 -> 3 -> 2 -> 1
     */
    public static ListNode reverseList2(ListNode head){
        if (head == null || head.next == null) {
            return head;
        }
        //递归反转子lian链表
        ListNode newList = reverseList2(head.next);
        //第三张图
        head.next.next = head;
        head.next = null;
        return newList;
    }

    public static void op3(ListNode head) {

        ListNode cur = head;
        cur = cur.next.next;

        ListNode nur = cur;
        nur.next = null;

        ListNode.print(head);
        ListNode.print(cur);
        ListNode.print(nur);
    }

    public static void main(String[] args) {
        ListNode listNode = ListNode.initBuild();
        ListNode r1 = reverseList(listNode);
        ListNode listNode2 = ListNode.initBuild();
        ListNode r2 = reverseList2(listNode2);
        ListNode listNode3 = ListNode.initBuild();
        ListNode.print(r1);
        ListNode.print(r2);

        System.out.println();

        ListNode listNode4 = ListNode.initBuild();
        op3(listNode4);
        System.out.println();

    }

}

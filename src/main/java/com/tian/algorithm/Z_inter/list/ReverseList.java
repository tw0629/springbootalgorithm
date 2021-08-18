package com.tian.algorithm.Z_inter.list;

import com.tian.algorithm.Z_inter.a_common.ListNode;
import com.tian.algorithm.Z_inter.a_common.Node;

/**
 * @author David Tian
 * @desc
 * @since 2021/8/1 20:55
 */
public class ReverseList {

    //反转单链表
    public static ListNode reverseList(ListNode head) {
        ListNode next = null;//指向当前节点的后驱
        ListNode pre = null;//指向当前节点的前驱
        while (head != null) {
            next = head.next;
            //当前节点的后驱指向前驱
            head.next = pre;
            pre = head;
            //处理下一个节点
            head = next;
        }
        return pre;
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
    public static Node reverseList2(Node head){
        if (head == null || head.next == null) {
            return head;
        }
        //递归反转子lian链表
        Node newList = reverseList2(head.next);
        //第三张图
        head.next.next = head;
        head.next = null;
        return newList;
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node.next=node2;
        node2.next=node3;

        ListNode listNode = ListNode.initBuild();
        ListNode listNode1 = reverseList(listNode);

        Node node1 = reverseList2(node);

        //System.out.println(listNode1);
        //ListNode.print(listNode1);
        System.out.println(node1);
    }


}

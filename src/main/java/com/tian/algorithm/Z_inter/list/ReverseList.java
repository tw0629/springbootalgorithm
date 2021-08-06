package com.tian.algorithm.Z_inter.list;

import com.tian.algorithm.Z_inter.a_common.Node;

/**
 * @author David Tian
 * @desc
 * @since 2021/8/1 20:55
 */
public class ReverseList {

    //反转单链表
    public static Node reverseList(Node head) {
        Node next = null;//指向当前节点的后驱
        Node pre = null;//指向当前节点的前驱
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
}

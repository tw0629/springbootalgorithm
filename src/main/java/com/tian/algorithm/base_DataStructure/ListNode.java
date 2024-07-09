package com.tian.algorithm.base_DataStructure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author David Tian
 * @desc
 * @since 2021/8/1 14:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListNode {

    public ListNode next;

    public Integer data;

    public ListNode(int data) {
        this.data = data;
    }

    public ListNode(int val, ListNode next) {
        this.data = val;
        this.next = next;
    }

    public static ListNode initBuild(){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(7);
        ListNode node8 = new ListNode(8);
        ListNode node9 = new ListNode(9);
        ListNode node10 = new ListNode(10);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        node5.next=node6;
        node6.next=node7;
        node7.next=node8;
        node8.next=node9;
        node9.next=node10;
        node10.next=null;
        return node1;
    }

    public static void print(ListNode head){
        if(head==null){
            return;
        }
        if(head.next==null){
            System.out.println(head.data);
            return;
        }

        System.out.print(head.data+"->");
        while (head.next!=null) {
            head=head.next;

            if(head.next==null){
                System.out.print(head.data);
                continue;
            }
            System.out.print(head.data+"->");
        }
        System.out.println("");
    }
}

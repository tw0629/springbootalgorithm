package com.tian.algorithm.Z_inter.list;

import com.tian.algorithm.Z_inter.a_common.ListNode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author David Tian
 * @desc
 * @since 2021/7/29 20:13
 */
public class ListNodeOperate {

    /**
     * ListNode
     */
    public static void sumMaxChild5(ListNode head) {
        int max = 0;
        int temp= 0;
        ListNode cur = head;
        while(cur!=null){
            temp = Math.max(temp+cur.data, cur.data);
            max  = Math.max(max,temp);
            cur=cur.next;
        }
        System.out.println("ListNode sumMaxChild5: "+ max);
    }


    /**
     * ============================================
     * ============================================
     * ============================================
     *
     * 从头到尾的：每隔几个逆序   solve()
     * 从尾到头的：每隔几个逆序   reverseKGroup()
     */

    /**
     * 实现一个单链表，每K个节点之间为一组进行逆序
     *
     * 从尾部倒着开始,链表局部分组倒序
     *
     * 给定一个单链表的头节点 head,实现一个调整单链表的函数，使得每K个节点之间为一组进行逆序，
     * 并且从链表的尾部开始组起，头部剩余节点数量不够一组的不需要逆序。
     * （不能使用队列或者栈作为辅助）
     */
    public static ListNode solve(ListNode head, int k) {
        ListNode.print(head);

        // 调用逆序函数
        head = reverseList(head);
        ListNode.print(head);

        // 调用每 k 个为一组的逆序函数（从头部开始组起）
        head = reverseKGroup(head, k);
        ListNode.print(head);

        // 在逆序一次
        head = reverseList(head);
        ListNode.print(head);

        return head;
    }

    public static ListNode solve2(ListNode head, int k) {
        ListNode.print(head);

        head = reverseKGroup(head, k);

        ListNode.print(head);

        return head;
    }

    /**
     * 链表局部分组倒序
     *
     * k个为一组逆序   下面注释用举例说明
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        // head/temp: 1->2->3->4->5->6->7->8
        ListNode temp = head;
        for (int i = 1; i < k && temp != null; i++) {
            temp = temp.next;
        }
        // temp: 3->4->5->6->7->8
        //判断节点的数量是否能够凑成一组
        if(temp == null){
            return head;
        }

        // head: 1->2->3->null
        // temp: 3->null
        // t2:   4->5->6->7->8
        ListNode t2 = temp.next;
        temp.next = null;

        // newHead: 3->2->1->null
        // head:    1->null
        // 把当前的组进行逆序
        ListNode newHead = reverseList(head);

        // 尾节点指向下一个翻转的头节点
        // 把之后的节点进行分组逆序
        ListNode newTemp = reverseKGroup(t2, k);
        // 把两部分连接起来
        head.next = newTemp;

        return newHead;
    }

    //逆序单链表
    private static ListNode reverseList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode result = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return result;
    }

    /**
     * https://www.nowcoder.com/practice/650474f313294468a4ded3ce0f7898b9?tpId=117&&tqId=37714&rp=1&ru=/ta/job-code-high&qru=/ta/job-code-high/question-ranking
     * 判断链表中是否有环 方法1
     *
     * 双指针法
     */
    public boolean hasCycle(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;
        //只要在一条赛道上跑，总会有相遇的时候^ ^
        while(p2!=null && p2.next!=null){
            p1 = p1.next;
            p2 = p2.next.next;
            if(p1 == p2){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断链表中是否有环 方法2
     *
     * set法
     */
    public boolean hasCycle2(ListNode head) {
        Set<ListNode> set = new HashSet();
        while (head != null) {
            if (!set.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }



    public static void main(String[] args) {

        int[] a = {-2,1,-3,4,-1,2,1,-5,4};
        /*sumMaxChild(a);
        sumMaxChild2(a);
        sumMaxChild3(a);
        sumMaxChild4(a);*/

        ListNode head = new ListNode(a[0]);
        ListNode cur = head;
        for(int i = 1; i< a.length; i++){
            cur.next = new ListNode(a[i]);
            cur=cur.next;
        }
        sumMaxChild5(head);

        //https://zhuanlan.zhihu.com/p/77573456
        ListNode node = ListNode.initBuild();
        solve( node, 3);

        System.out.println("");

        ListNode node2 = ListNode.initBuild();
        solve2( node2, 3);


    }
}

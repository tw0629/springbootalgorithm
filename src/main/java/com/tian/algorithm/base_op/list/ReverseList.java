package com.tian.algorithm.base_op.list;

import com.tian.algorithm.base_DataStructure.ListNode;

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
            res = head; // 3 将【新head】重新 赋给res             // head:1->res res:1
            //处理下一个节点
            head = next;// 4 1和4对应                           // head:2->3 res:1
        }
        return res;
    }

    //用递归的方法反转链表
    /**
     * 理解：ListNode lastLoa = reverseList2(head.next); //!!! 其实:lastLoa就是最后位置，永远不动了/永远不变了。永远是5的位置，一层层的递归传上去。
     *
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
        ListNode lastLoa= reverseList2(head.next);//!!!其实:newList就是 lastList位置，永远不动了/永远不变了。永远是5的位置，一层层的递归传上去
        //第三张图
        head.next.next = head;
        head.next = null;
        return lastLoa;
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


    /**
     * 区间反转
     * 方法一
     *
     * 给你单链表的头指针 head 和两个整数.left 和 right ，其中.left <= right 。
     * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
     * 链接：https://leetcode.cn/problems/reverse-linked-list-ii
     * 
     * 输入：head = [1,2,3,4,5], left = 2, right = 4
     * 输出：[1,4,3,2,5]
     *
     */
    public static ListNode reverseBetweenMN(ListNode head, int left, int right) {
        // 因为头节点有可能发生变化，使用虚拟头节点可以避免复杂的分类讨论
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode pre = dummyNode;
        // 第 1 步：从虚拟头节点走 left - 1 步，来到 left 节点的前一个节点
        // 建议写在 for 循环里，语义清晰
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        // 第 2 步：从 pre 再走 right - left + 1 步，来到 right 节点
        ListNode rightNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }

        // 第 3 步：切断出一个子链表（截取链表）
        ListNode leftNode = pre.next;
        ListNode curr = rightNode.next;

        // 注意：切断链接
        pre.next = null;
        rightNode.next = null;

        // 第 4 步：同第 206 题，反转链表的子区间
        reverseList(leftNode);

        // 第 5 步：接回到原来的链表中
        pre.next = rightNode;
        leftNode.next = curr;

        return dummyNode.next;
    }

    /**
     * 方法二 头插法
     * 想找到m-1,m的这两个位置，依次将m后面的元素逐个插到m-1后面（头插法）
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // 定义一个dummyHead, 方便处理
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        // 初始化指针
        ListNode g = dummyHead;
        ListNode p = dummyHead.next;

        // 将指针移到相应的位置
        for(int step = 0; step < m - 1; step++) {
            g = g.next;
            p = p.next;
        }

        // 头插法插入节点
        for (int i = 0; i < n - m; i++) {
            ListNode removed = p.next;
            p.next = p.next.next;

            removed.next = g.next;
            g.next = removed;
        }

        return dummyHead.next;
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

        ListNode listNode5 = ListNode.initBuild();
        ListNode r5 = reverseBetweenMN(listNode5, 3, 8);
        ListNode.print(r5);
        System.out.println();

    }


}

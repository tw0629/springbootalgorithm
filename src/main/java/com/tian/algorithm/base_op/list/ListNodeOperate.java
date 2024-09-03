package com.tian.algorithm.base_op.list;

import com.tian.algorithm.base_DataStructure.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author David Tian
 * @desc  ListNode反转类型 用递归思想比较好
 * @since 2021/7/29 20:13
 */
public class ListNodeOperate {

    /**
     * 做这种题技巧：
     * 不管是 每K个节点进行逆序 还是 两两相邻交换
     *
     * 方法：递归 + 举例一组简单数    就知道怎么写了
     *
     *      只需要举例一组简单数，即：1->2->3->4->5->6
     *      只要走一次最简单的一组数皆可写出 即：1->2->3->4->5->6
     */

    // 用数字想   1一>2一>3一>4一>5一>6
    /**
     * 给定一个链表，两两交换其中相邻的节点
     */
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode rest = head.next.next;//3

        ListNode newHead = head.next;//2
        newHead.next = head;// 2一>1

        head.next = swapPairs(rest);// // 2一>1一>……
        return newHead;//2
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
    public static ListNode KGroup(ListNode head, int k) {
        ListNode.print(head);

        // 调用逆序函数
        head = reverseList(head); // 因为是要求从尾部开始每几个翻转，所以要先排逆序
        ListNode.print(head);

        // 调用每 k 个为一组的逆序函数（从头部开始组起）
        head = reverseKGroup(head, k);
        ListNode.print(head);

        // 在逆序一次
        head = reverseList(head);
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
        // eg:3
        ListNode temp = head;
        for (int i = 1; i < k && temp != null; i++) { // k：一定要卡 k-1个, 即:(i = 1; i < k)；为了"temp.next = null"操作
            temp = temp.next; // 注意:i从1开始
        }
        // temp: 3->4->5->6->7->8
        //判断节点的数量是否能够凑成一组
        if(temp == null){
            return head;
        }

        // temp: 3->null
        // t2:   4->5->6->7->8 !!!
        // head: 1->2->3->null !!!
        ListNode t2 = temp.next; //!!!
        temp.next = null; //!!!

        // newHead: 3->2->1->null
        // head:    1->null
        // 把当前的组进行逆序
        ListNode newHead = reverseList(head);
        // 尾节点指向下一个翻转的头节点
        // 把之后的节点进行分组逆序
        ListNode newTemp = reverseKGroup(t2, k);
        // 把两部分连接起来
        head.next = newTemp;  // 注意: 此时newHead的尾部就是head

        return newHead;
    }

    //只要走一次最简单的一组数皆可写出 即：1->2->3
    //逆序单链表     （这种方式画图理解简单，图见:com.tian.algorithm.Z_inter.list.ReverseList.reverseList2）
    private static ListNode reverseList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode result = reverseList(head.next);//3   head:2
        head.next.next = head; // 2->3->2
        head.next = null; // 2->null 3->2  // 1->2->null 3->2->null
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

    /**
     * 和最大的子序列  和  ❌❌❌
     * 和最大的子串/子链表    和  ❌❌❌
     *
     * 注意：这种写法同【和最大的子数组】的写法
     * ListNode 只能写子序列；不好描述子串/子链表
     *
     *  （连续子序列）
     *
     *  和最大的子链表
     *     该算法为：连续子链表的和最大
     *         ListNode 可以和最大的子序列；也可以描述和最大的子链表
     */
    public static int sumMaxChild(ListNode head) {
        int max = 0;
        int temp= 0;
        ListNode cur = head;
        while(cur!=null){
            temp = Math.max(temp+cur.data, cur.data);
            max  = Math.max(max,temp);
            cur=cur.next;
        }
        System.out.println("ListNode sumMaxChild5: "+ max);
        return max;
    }

    /**
     * 和最大的子序列  子序列 (这不就是 只挑正数吗)
     * ListNode
     */
    public static ListNode subSequenceSumMax(ListNode head) {
        int max = 0;
        int temp= 0;
        ListNode cur = head;

        ListNode newList = null;
        ListNode tail = null;
        while(cur!=null){
            Integer data = cur.data;
            cur=cur.next;

            if(data>0){
                // 和
                max += data;

                // 序列
                ListNode listNode = new ListNode(data);
                if(newList==null){
                    newList = listNode;
                    tail = listNode;
                    continue;
                }
                tail.next = listNode;
//                tail = tail.next;
                tail = listNode;
            }
        }
        System.out.println("ListNode sumMaxChild5: "+ max);
        System.out.println("newList: "+ newList);

        return newList;
    }


    public static void main(String[] args) {

        int[] a = {-2,1,-3,4,-1,2,1,-5,4};

        int[] array6 = { -2, -3, 4, 3, -2, 1, 5, 7 };
        ListNode node0 = new ListNode(7,null);
        ListNode node1 = new ListNode(5,node0);
        ListNode node2 = new ListNode(1,node1);
        ListNode node3 = new ListNode(-2,node2);
        ListNode node4 = new ListNode(3,node3);
        ListNode node5 = new ListNode(4,node4);
        ListNode node6 = new ListNode(-3,node5);
        ListNode node7 = new ListNode(-2,node6);
        ListNode.print(node7);
        //ListNode newList = subSequenceSumMax(node7);
        sumMaxChild(node7);
        //ListNode.print(newList);



        //https://zhuanlan.zhihu.com/p/77573456

        System.out.println("");

        //ListNode node2 = ListNode.initBuild();
        //solve2( node2, 4);
        //ListNode.print(KGroup(node2,3));
        ListNode nodeInit = ListNode.initBuild();
        ListNode.print(reverseKGroup(nodeInit,3));
        System.out.println("");

    }
}

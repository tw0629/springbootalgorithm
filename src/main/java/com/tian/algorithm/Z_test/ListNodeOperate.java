package com.tian.algorithm.Z_test;

/**
 * @author David Tian
 * @desc
 * @since 2021/7/29 20:13
 */
public class ListNodeOperate {

    public static void main(String[] args) {

        int[] a = {-2,1,-3,4,-1,2,1,-5,4};
        /*sumMaxChild(a);
        sumMaxChild2(a);
        sumMaxChild3(a);
        sumMaxChild4(a);*/

        //https://zhuanlan.zhihu.com/p/77573456
        ListNode node = ListNode.initBuild();
        solve2( node, 3);

    }

    public static void sumMaxChild(int[] a){
        int length = a.length;
        int max = 0;
        for(int i=0; i<length; i++){
            for(int j=0; j<length; j++){
                int result = sum(a, i, j);
                if(result>max){
                  max = result;
                  System.out.println("a下标区间："+i+"-"+j+"，sumMaxChild："+max);
                }
            }
        }
    }
    public static int sum(int[] a, int m, int n){
        int result = 0;
        for(int k=m; k<=n; k++){
            result+=a[k];
        }
        return result;
    }

    public static void sumMaxChild2(int[] a) {

        int[] temp = new int[a.length];
        temp[0] = 0;
        for(int i = 1; i<a.length;i++){
            temp[i] = Math.max(a[i], temp[i-1]+a[i]);
        }

        int sumMax = 0;
        for(int i = 0; i<a.length; i++){
            sumMax = Math.max(sumMax,temp[i]);
        }

        System.out.println("sumMaxChild2: "+ sumMax);
    }

    /**
     * dp dp(i)=max(dp(i-1)+array[i],array[i])
     */
    public static void sumMaxChild3(int[] a) {
        //max就是上面的dp[i]
        int temp = a[0];
        //因为这个dp[i]老是变，所以比如你dp[4]是8 dp[5]就变成-7了，所以需要res保存一下
        int sumMax = a[0];
        for (int i = 1; i < a.length; i++) {
            temp = Math.max(temp + a[i], a[i]);
            sumMax = Math.max(sumMax, temp);
        }
        System.out.println("sumMaxChild3: "+ sumMax);
    }

    public static void sumMaxChild4(int[] a) {
        int max = 0;
        int temp= 0;
        for(int i = 0; i<a.length; i++) {
            temp = temp+a[i] > a[i] ? temp+a[i]:a[i];
            max  = max > temp ? max:temp;
        }
        System.out.println("sumMaxChild4: "+ max);
    }


    /**
     * ============================================
     * ============================================
     * ============================================
     */

    /**
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


    //k个为一组逆序
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head;
        for (int i = 1; i < k && temp != null; i++) {
            temp = temp.next;
        }
        //判断节点的数量是否能够凑成一组
        if(temp == null){
            return head;
        }

        ListNode t2 = temp.next;
        temp.next = null;
        // 把当前的组进行逆序
        ListNode newHead = reverseList(head);
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


}

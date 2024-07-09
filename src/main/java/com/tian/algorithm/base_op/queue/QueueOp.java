package com.tian.algorithm.base_op.queue;

import java.util.Stack;

/**
 * @author David Tian
 * @desc
 * @since 2021/8/2 02:22
 */
public class QueueOp {

    /**
     * 用两个栈实现队列
     */
    public class Solution {
        Stack<Integer> stack1 = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();

        public void push(int node) {
            stack1.push(node);
        }

        public int pop() {
            if (stack2.size() <= 0) {
                while (stack1.size() != 0) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }
    }
}

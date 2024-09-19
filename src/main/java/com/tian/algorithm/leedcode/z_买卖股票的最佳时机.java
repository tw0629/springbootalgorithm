package com.tian.algorithm.leedcode;

/**
 * @author David Tian
 * @desc
 * @since 2022/11/11 10:06
 */
public class z_买卖股票的最佳时机 {

    // 两次遍历+动态方程 就太傻了


    /**
     * 一次遍历
     *  即:最大值-最小值 (且 买入大于卖出)
     */
    public static int maxProfit(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice) {
                minprice = prices[i];
            } else if (prices[i] - minprice > maxprofit) {
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }

    /**
     * 不知道有没有漏洞   和上面等价
     */
    public static int maxProfit2(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            minprice = Math.min(minprice,prices[i]);
            maxprofit = Math.max(maxprofit,prices[i]-minprice);
        }
        return maxprofit;
    }

    /**
     * 其他
     *
     * 最大的leftMax和rightMax之差的最大值
     * |leftMax-rightMax|绝对值最大
     */
    public static int maxleftright(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        return max - Math.min(arr[0], arr[arr.length - 1]);
    }

    /**
     * 区别：
     * 获取利润最大
     *
     */
    public int maxProfit3(int[] prices) {
        int count = 0;
        for (int i = 0; i < prices.length-1; i++) {
            if (prices[i] < prices[i + 1]) {
                count = prices[i + 1] - prices[i] + count;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] a = {7,1,5,3,6,4};
        System.out.println(maxProfit(a));
        System.out.println(maxProfit2(a));
        System.out.println(maxleftright(a));
        System.out.println();
    }

}

package com.tian.algorithm.leedcode;

/**
 * @author David Tian
 * @desc
 * @since 2022/10/7 11:38
 */
public class a4_寻找两个正序数组的中位数 {


    /**
     * 方法一 归并
     * 简单粗暴，先将两个数组合并，两个有序数组的合并也是归并排序中的一部分。然后根据奇数，还是偶数，返回中位数。
     */
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int[] combo = new int[nums1.length + nums2.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) {
                combo[k++] = nums1[i++];
            } else {
                combo[k++] = nums2[j++];
            }
        }
        while (i < nums1.length) {
            combo[k++] = nums1[i++];
        }
        while (j < nums2.length) {
            combo[k++] = nums2[j++];
        }
        int len = combo.length;
        if (len == 1) {
            return Double.valueOf(combo[0]);
        }
        // 偶数
        if (len % 2 == 0) {
            return Double.valueOf(combo[len / 2 - 1] + combo[len / 2]) / 2d;
        } else {
            return Double.valueOf(combo[len / 2]);
        }
    }

    /**
     * 方法二
     * 不需要将两个数组真的合并，我们只需要找到中位数在哪里就可以了
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int i = 0, j = 0, k = 0, m1 = 0, m2 = 0;
        int mid = (nums1.length + nums2.length) / 2;
        while (k <= mid && (i < nums1.length || j < nums2.length)) { // 注意：条件中mid:一半
            m1 = m2;
            if (i == nums1.length || (i != nums1.length && j != nums2.length && nums2[j] < nums1[i]))
                m2 = nums2[j++];
            else
                m2 = nums1[i++];
            k++;
        }
        return (nums1.length + nums2.length) % 2 == 1 ? (double) m2 : (double) (m1 + m2) / 2;
    }
    public double findMedianSortedArrays22(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int len = m + n;
        int left = -1, right = -1;
        int aStart = 0, bStart = 0;
        for (int i = 0; i <= len / 2; i++) { // 注意：条件中len:一半
            left = right;
            if (aStart < m && (bStart >= n || A[aStart] < B[bStart])) {
                right = A[aStart++];
            } else {
                right = B[bStart++];
            }
        }
        if ((len & 1) == 0)
            return (left + right) / 2.0;
        else
            return right;
    }

    /**
     * 方法三
     * 我们如果暴力的话直接可以合并两个数组再找出中位数，但是我们其实可以不用合并两个数组，
     * 而是转化为寻找两个数组中的第k数，寻找有序数组的第k个数就可以用到二分。
     *
     * 复杂度分析
     * 时间复杂度：O(log(m + n))
     * 空间复杂度：O(1)
     */
    public static double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        int t = nums1.length + nums2.length;    //总长度
        if(t % 2 == 0)  //总长度为偶数
            return (f(nums1, 0, nums2, 0, t / 2) + f(nums1, 0, nums2, 0, t / 2 + 1)) / 2.0; //中位数为两个数的平均数
        else
            return f(nums1, 0, nums2, 0, t / 2 + 1);//中位数为中间那个数
    }
    static int f(int[] nums1, int i, int[] nums2, int j, int k) { // nums1代表少的，nums2代表多的
        if(nums1.length - i > nums2.length - j) //我们使得剩余长度小的放在nums1；如果nums1的剩余长度大于nums2，则交换位置
            return f(nums2, j, nums1, i, k);
        if(nums1.length == i) //nums1已经全部遍历完，那么中位数肯定再nums2的第j+k-1个
            return nums2[j + k - 1];
        if(k == 1)  //如果k等于1，就代表是第一个数，那么我们就直接返回两个数组中较小的那个数
            return Math.min(nums1[i], nums2[j]);

        int si = Math.min(i + k / 2, nums1.length);//取nums1第剩余区间的中间那个数
        int sj = j + k / 2; //取nums2剩余区间中间那个数

        if(nums1[si - 1] > nums2[sj - 1])   //代表可以排除nums2中sj左半边的数
            return f(nums1, i, nums2, sj, k - k / 2);
        else
            return f(nums1, si, nums2, j, k - (si - i));//排除nums1左半边的数
    }

    // k:有序数组的第k个数
    public static double findMedianSortedArrays32(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
    }
    private static int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        if (len1 == 0) return nums2[start2 + k - 1];
        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
        else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }

    /**
     * 方法三
     *
     * 时间复杂度：我们对较短的数组进行了二分查找，所以时间复杂度是 O(log（min（m，n））O(log（min（m，n））。
     * 空间复杂度：只有一些固定的变量，和数组长度无关，所以空间复杂度是 O ( 1 )O(1)。
     *
     */
    public double findMedianSortedArrays4(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) {
            return findMedianSortedArrays4(B,A); // 保证 m <= n
        }
        int iMin = 0, iMax = m;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = (m + n + 1) / 2 - i;
            if (j != 0 && i != m && B[j-1] > A[i]){ // i 需要增大
                iMin = i + 1;
            }
            else if (i != 0 && j != n && A[i-1] > B[j]) { // i 需要减小
                iMax = i - 1;
            }
            else { // 达到要求，并且将边界条件列出来单独考虑
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; } // 奇数的话不需要考虑右半部分

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0; //如果是偶数的话返回结果
            }
        }
        return 0.0;
    }

    public static void main(String[] args) {

        //int[] nums1 = {1,3,4,9};
        //int[] nums2 = {1,2,3,4,5,6,7,8,9};

        int[] nums1 = {1,3,5,7,9};
        int[] nums2 = {2,4,6,8,10,11};

        double medianSortedArrays3 = findMedianSortedArrays3(nums1, nums2);
        double medianSortedArrays32 = findMedianSortedArrays32(nums1, nums2);

        System.out.println("medianSortedArrays3: "+medianSortedArrays3);
        System.out.println("medianSortedArrays32: "+medianSortedArrays32);
        System.out.println();

    }

}

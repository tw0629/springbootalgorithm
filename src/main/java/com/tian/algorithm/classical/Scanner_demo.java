package com.tian.algorithm.classical;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author David Tian
 * @desc
 * @since 2023/7/25 09:18
 */
public class Scanner_demo {

    /**
     * 看看这个：Scanner中nextInt()、nextLine()等方法总结与问题解决
     * https://blog.csdn.net/qq_41620270/article/details/120582910
     */

    public static void main(String[] args) {
        //test();
        test2();
    }

    public static void test(){
        //手动输入字符数组（整数）
        Scanner sc = new Scanner(System.in);
        //获取字符串中的每一个数字.
        String[] strArr = sc.nextLine().split(",");

        //方法一
        //创建一个int类型的数组.
        int [] numberArr1 = new int[strArr.length];
        //通过遍历把strArr中的数据进行类型转换并存入到int数组中
        for (int i = 0; i < strArr.length; i++) {
            numberArr1[i]  = Integer.parseInt(strArr[i]);
        }
        //遍历输出检查
        for(int s1 : numberArr1){
            System.out.print(s1 +" ");
        }

        //方法二
        //通过lamada表达式进行转换
        int[] numberArr2 = Arrays.asList(strArr).stream().mapToInt(Integer::parseInt).toArray();
        //遍历输出检查
        for(int s2 : numberArr2){
            System.out.print(s2 +" ");
        }
    }

    public static void test2(){
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a + b);
        }

        Scanner scanner = new Scanner(System.in);

        String str = scanner.next();
        int num = scanner.nextInt();
        String line = scanner.nextLine();

        boolean hasLine = scanner.hasNextLine();
        boolean hasInt = scanner.hasNextInt();

        while (scanner.hasNextInt()) {
            int i = scanner.nextInt();//输入数字i
            System.out.println(i);//打印数字
        }
    }
}

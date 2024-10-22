package com.tian.algorithm.leedcode;

/**
 * @author David Tian
 * @desc
 * @since 2022/10/17 23:02
 */
public class a6_Z字形变换 {

    /**
     * Z字形转换是指在一个二维网格上，按照从左到右、从上到下、再从右到左、从下到上的Z字形顺序，
     * 依次选取每个单元格中的数字。
     * 最后将所有的数字连接成一个新的字符串。
     *
     * Z字形转换："字符串Z字形排列"输入 转变为 "逐行横向"输出
     *  String convert(String s, int numRows){}
     *
     * 例如：
         将字符串 "PAYPALISHIRING" 以Z字形排列成给定的行数：
         P   A   H   N
         A P L S I I G
         Y   I   R
         之后从左往右，逐行读取字符："PAHNAPLSIIGYIR"
     */

    /**  ？？？是不是写错了
     * numRows=3时候
     * row号:   0    1   2   1   0   1   2   1   0   1   2   1   0   1   2   1   0
     * index:        row-1           row-1           row-1           row-1
     */
    public String convert(String s, int numRows) {
        if(numRows==1){
            return s;
        }

        // !!!!!! 初始化numRows行字符：result
        String[] result = new String[numRows];
            // result初始化
        for(int i=0;i<result.length;i++){
            result[i]="";//这里一定要初始化，如果不初始化的话，默认是null
        }

        boolean isDown=false;
        int index=0;
        for(int i=0;i<s.length();i++){

            // !!!!!! index不停轮流变化，就是要不同字符追加到不同行，一共是numRows行
            result[index]+=s.charAt(i); // !!!!!! 横向追加，进行字符串拼接

            // 下面代码就是实现：变化不同行，实现轮动
            if(index==0||index==numRows-1){
                isDown=!isDown;//第一次index=0时，isDown修改为true说明可以继续往下走；当index=numRows的时候说明不能继续往下走，然后把isDown修改为false
            }
            index+=isDown?1:-1;  // !!!!!!  isDown=1/-1  index=index+1
        }

        StringBuffer sb=new StringBuffer();
        for(int i=0;i<result.length;i++){
            sb.append(result[i]);
        }
        return sb.toString();
    }

}

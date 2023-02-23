package com.tian.algorithm.leedcode;

/**
 * @author David Tian
 * @desc
 * @since 2022/10/17 23:02
 */
public class a6_Z字形变换 {

    /**
     * numRows=3时候
     * row:   0    1   2   1   0   1   2   1   0   1   2   1   0   1   2   1   0
     * index:        row-1           row-1           row-1           row-1
     */
    public String convert(String s, int numRows) {
        if(numRows==1){
            return s;
        }
        String result[]=new String[numRows];
        for(int i=0;i<result.length;i++){
            result[i]="";//这里一定要初始化，如果不初始化的话，默认是null
        }

        boolean isDown=false;
        int index=0;
        for(int i=0;i<s.length();i++){
            result[index]+=s.charAt(i); // !!!!!!

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

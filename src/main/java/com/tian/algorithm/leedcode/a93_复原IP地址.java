package com.tian.algorithm.leedcode;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author David Tian
 * @desc
 * @since 2022/10/31 15:34
 */
public class a93_复原IP地址 {

    //方法二  推荐
    //方法二  推荐
    //方法二  推荐

    /**
     * 方法一
     */
    static List<String> ret = new ArrayList<>();
    public static List<String> restoreIpAddresses11(String s) {
        LinkedList<String> path = new LinkedList<>();
        backTrace11(0,s,path);
        return ret;
    }
    public static void backTrace11(int startIndex, String s, LinkedList<String> path){
        if(path.size() > 4) return; // 长度>4剪枝
        if(startIndex == s.length() && path.size() == 4){
            ret.add(toResult11(path));
            return;
        }
        for(int i = startIndex;i<s.length();i++){
            String str = s.substring(startIndex,i+1);
            if(!isValid11(str)) continue;
            path.offerLast(str);
            backTrace11(i+1,s,path);
            path.removeLast();
        }
    }
    public static String toResult11(LinkedList<String> path){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < path.size(); i++){
            sb.append(path.get(i));
            if(i != path.size() - 1)
                sb.append(".");
        }
        return sb.toString();
    }
    public static boolean isValid11(String s){
        if(s.length()==1) return true;
        if(s.length()>3) return false;
        if(s.charAt(0) == '0') return false;
        if(Integer.valueOf(s) > 255) return false;
        return true;
    }


    /**
     * 方法二  推荐
     */
    // 所有的ip地址  注意: 因为是字符串,所以res为List<String>而不是List<List<String>>
    static List<String> res = new ArrayList<>();
    // 每次子集临时存放, ip地址的四个段
    static List<String> path = new ArrayList<>();

    public static List<String> restoreIpAddresses12(String s) {
        //backtracking12(s,0,path);
        backtracking12(s,0);
        return res;
    }
//    private static void backtracking12(String s, int index, List<String> path) {
    private static void backtracking12(String s, int index) {
        if (path.size() == 4 && index == s.length()){
            // 收集
            String ans = "";
            for (String s1 : path) {
                ans += s1;
                ans += ".";
            }
            res.add(ans.substring(0,ans.length() - 1));
        }
        if (path.size() == 4 && index != s.length())
            return;

        for (int i = index; i < s.length() && i < index + 3; i++) {
            if (isIp12(s,index,i)){
                path.add(s.substring(index,i + 1));    //!!!!!! substring(index, i + 1)
                //backtracking12(s,i + 1,path);
                backtracking12(s,i + 1);
                path.remove(path.size() - 1);
            }
        }
    }
    private static boolean isIp12(String s, int index, int end) {
        if (s.charAt(index) == '0' && end - index >= 1) return false; // 可以:0, 不可以:011,01
        int sum = 0;
        for (int j = index; j <= end; j++) {
            int tmp = s.charAt(j) - '0';
            sum = sum*10 + tmp;
        }
        //sum = Integer.valueOf(s.substring(index,end));
        if (sum >= 0 && sum <= 255) return true;
        return false;
    }

    public static void main(String[] args) {

        //String s = "25525511135";
        String s = "25515111115";
        //System.out.println(restoreIpAddresses11(s));
        System.out.println();
        System.out.println(restoreIpAddresses12(s));
        System.out.println();
        //System.out.println(ipProcess("123456123456"));
        System.out.println(s.substring(0,2));
    }


    /**
     *  类似 复原IP地址
     *
     *  此题为字节跳动面试题,
     *  大致是编码加码题的叙述
     *  即：1--->A 2--->B 3--->C …… 26--->Z
     *  (226)--->(2 2 6)(22 6)(2 26)  有效编码组合
     *           (B B F)(T F)(B Z)    解码后的组合
     *
     *  给一串数字, 能拆出多少组合来
     */
    public static int ipProcess(String s){
        List<List<String>> res = new ArrayList<>();
        List<String> path = new ArrayList<>();

        dfs(s,0,res,path);
        System.out.println();
        return res.size();
    }

    public static void dfs(String s, int index, List<List<String>> res, List<String> path){
        if(index==s.length()){
            System.out.println("======> "+path.toString());
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i=index; i<s.length()&&i<=index+2; i++){ // !!! i<s.length()&&i<=index+2
            //!!!!!! substring(index, i + 1)
            String substr = s.substring(index, i + 1);  //!!!!!! substring(index, i + 1)

            if(is126(substr)){
                path.add(substr);
                dfs(s,index+1,res,path);
                path.remove(path.size()-1);
            }
        }

    }

    public static boolean is126(String s){
        if(StringUtils.isEmpty(s)){
            return false;
        }
        Integer a = Integer.valueOf(s);
        if(1<=a && a<=26){
            return true;
        }
        return false;
    }


}

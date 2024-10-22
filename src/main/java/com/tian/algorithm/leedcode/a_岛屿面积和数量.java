package com.tian.algorithm.leedcode;

/**
 * @author David Tian
 * @desc
 * @since 2023/3/18 15:25
 */
public class a_岛屿面积和数量 {

    /**
     * 岛屿是由一些相邻的1(代表土地) 构成的组合，
     * 这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。
     * 你可以假设grid 的四个边缘都被 0（代表水）包围着。
     */

    /**
     * 岛屿的最大面积
     */
    // 链接：https://leetcode.cn/problems/max-area-of-island/solution/biao-zhun-javadong-tai-gui-hua-jie-fa-100-by-mark-/
    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) { // 计算面积时,grid[i].length
                if (grid[i][j] == 1) {
                    res = Math.max(res, dfs(i, j, grid)); // 如果是：面积最大的岛屿

                    // res +=dfs(i, j, grid);  // 如果是：所有岛屿的面积
                }
            }
        }
        return res;
    }
    // 每次调用的时候默认num为1，进入后判断如果不是岛屿，则直接返回0，就可以避免预防错误的情况。
    // 每次找到岛屿，则直接把找到的岛屿改成0，这是传说中的沉岛思想，就是遇到岛屿就把他和周围的全部沉默。
    // ps：如果能用沉岛思想，那么自然可以用朋友圈思想。有兴趣的朋友可以去尝试。
    private int dfs(int i, int j, int[][] grid) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || grid[i][j] == 0) { // ??? 要改为grid[i][j]!=1吗
            return 0;
        }

        // 一 标注, 表示已经计算过
        grid[i][j] = 2; //#遍历过的标记为2,或者其他0, 防止重复陆地重复遍历。

        // 二 递归遍历
        int num = 1; //定义一个变量表示岛屿的面积，就是包含几个陆地
        num += dfs(i + 1, j, grid);
        num += dfs(i - 1, j, grid);
        num += dfs(i, j + 1, grid);
        num += dfs(i, j - 1, grid);
        return num;
    }

    /**
     * 岛屿数量
     */
    //利用深度递归解决，可以看图，并加记住这个模板，他可以解决岛屿中的问题，还有一题岛屿面积问题也是这个模板。
    // https://leetcode.cn/problems/number-of-islands/solution/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/
    public int numIslands(char[][] grid) {
        //定义一个表示岛屿数量的变量
        int count = 0;
        //这个两层for循环是用来遍历整张二维表格中所有的陆地
        //其中 i 表示行，j 表示列
        for(int i = 0; i<grid.length;i++){
            for(int j =0;j<grid[0].length;j++){ // grid[0].length
                //取出所有的陆地
                if(grid[i][j] == '1'){
                    //深度递归，遍历所有的陆地
                    dfs(grid,i,j); // ======> 这样操作可以改变 grid二维数组,点的状态
                    //用来统计有多少岛屿，岛屿是由多个陆地组成的，概念不一样
                    count++;
                }
            }
        }
        //返回岛屿的数量
        return count;
    }
    // 为了将相连的'1'都遍历了
    public void dfs(char[][] grid,int i, int j){
        //防止 i 和 j 越界，也就是防止超出岛屿（上下左右）的范围。特别注意当遍历到海洋的时候也退出循环
        if( i<0 || j<0 || i>=grid.length || j>=grid[0].length || grid[i][j]=='0') // ??? 要改为grid[i][j]!=1吗
            return;

        // 一 标注, 表示已经计算过
        // 将遍历过的陆地改为海洋，防止重复遍历
        grid[i][j]='2'; // 将格子标记为「已遍历过」

        // 二 递归遍历
        //上，
        dfs(grid,i+1,j);
        //下
        dfs(grid,i-1,j);
        //右
        dfs(grid,i,j+1);
        //左
        dfs(grid,i,j-1);
    }

}

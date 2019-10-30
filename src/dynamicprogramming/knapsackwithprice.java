
package dynamicprogramming;

/**
 * 动态规划——背包问题进阶，引入物品价值
 *
 * @author <a href="mailto:your mail">Tu_ZhengSong(your mail)</a>
 */
public class knapsackwithprice {

    /**
     * 结果放到maxV中
     */
    private int maxV = Integer.MIN_VALUE;

    /**
     * 物品的重量
     */
    private int[] items = {2, 2, 4, 6, 3};

    /**
     * 物品的价值
     */
    private int[] value = {3, 4, 8, 9, 6};


    /**
     * 物品的个数
     */
    private int n = 5;

    /**
     * 背包承受的最大重量
     */
    private int w = 9;

    /**
     * 调用方式 f(0,0,0)
     *
     * @param i  考察到哪个物品了
     * @param cw 背包中已有的物品总重量
     * @param cv 背包中已有的物品总价值
     */
    public void f(int i, int cw, int cv) {
        //cw == w 表示装满了
        //i == n 表示物品考察完了
        if (cw == w || i == n) {
            if (cv > maxV) {
                maxV = cv;
                return;
            }
        }

        //选择不装第i个物品
        f(i + 1, cw, cv);

        if (cw + items[i] <= w) {
            //选择装第i个物品
            f(i + 1, cw + items[i], cv + value[i]);
        }
    }


    public static int knapsack3(int[] weight, int[] value, int n, int w) {
        int[][] states = new int[n][w + 1];

        //初始化states
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < w + 1; j++) {
                states[i][j] = -1;
            }
        }

        //动态规划，状态转移
        for (int i = 0; i < n; ++i) {
            //不选择第i个物品
            for (int j = 0; j <= w; ++j) {
                if (states[i - 1][j] >= 0) {
                    states[i][j] = states[i - 1][j];
                }
            }

            //选择第i个物品
            for (int j = 0; j < w - weight[i]; ++j) {
                if (states[i - 1][j] >= 0) {
                    int v = states[i - 1][j] + value[i];
                    //states[i][j + weight[i]] 是将要放入的物品所在位置的价值，可能是-1，也可能已更新，这里取所有情况下的最大值
                    if (v > states[i][j + weight[i]]) {
                        states[i][j + weight[i]] = v;
                    }
                }
            }
        }

        //找出最大值
        int maxvalue = -1;

        for (int j = 0; j <= w; ++j) {
            if (states[n - 1][j] > maxvalue) {
                maxvalue = states[n - 1][j];
            }
        }

        return maxvalue;
    }
}

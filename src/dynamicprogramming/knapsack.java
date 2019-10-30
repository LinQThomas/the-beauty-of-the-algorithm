
package dynamicprogramming;

/**
 * 动态规划——背包问题
 *
 * @author <a href="mailto:your mail">Tu_ZhengSong(your mail)</a>
 */
public class knapsack {

    /**
     * @param weight 物品重量
     * @param n      物品个数
     * @param w      背包可承载重量
     * @return
     */
    public int knapsack(int[] weight, int n, int w) {
        //默认值false
        boolean[][] status = new boolean[n][w + 1];
        //第一行数据需要特殊处理，可以利用哨兵优化
        status[0][0] = true;

        if (weight[0] <= w) {
            status[0][weight[0]] = true;
        }

        //动态规划状态转移
        for (int i = 0; i < n; ++i) {
            //不把第i个物品放入背包
            for (int j = 0; j <= w; ++j) {
                //status[i - 1][j] == true 是上一步的状态
                if (status[i - 1][j] == true) {
                    // status[i][j] = status[i - 1][j]; 表示这一步维持上一步的状态，不做改变
                    status[i][j] = status[i - 1][j];
                }
            }
            //把第i个物品放入背包
            for (int j = 0; j <= w - weight[i]; ++j) {
                //status[i - 1][j] == true 是上一步的状态
                if (status[i - 1][j] == true) {
                    //status[i][j + weight[i]] = true;表示这一步把物品放入背包，在上一步状态的基础上，扩展这一步的状态
                    status[i][j + weight[i]] = true;
                }
            }
        }
        for (int i = w; i >= 0; --i) {
            //输出结果
            if (status[n - 1][i] == true) {
                return i;
            }
        }

        return 0;
    }

    /**
     * @param items 物品重量
     * @param n     物品个数
     * @param w     背包可承载重量
     * @return
     */
    public static int knapsack2(int[] items, int n, int w) {
        boolean[] states = new boolean[w + 1];
        //第一行数据特殊处理，哨兵优化
        states[0] = true;

        if (items[0] <= w) {
            states[items[0]] = true;
        }

        //动态规划
        for (int i = 1; i < n; ++i) {
            for (int j = w - items[i]; j >= 0; --j) {
                if (states[j] == true) {
                    states[j + items[i]] = true;
                }
            }
        }

        for (int i = w; i >= 0; --i) {
            if (states[i] == true) {
                return i;
            }
        }

        return 0;
    }
}

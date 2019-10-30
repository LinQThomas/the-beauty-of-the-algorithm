
package dynamicprogramming;

/**
 * 最短路径
 *
 * @author <a href="mailto:your mail">Tu_ZhengSong(your mail)</a>
 */
public class MinDist {
    //全局变量或者成员变量
    private int minDist = Integer.MAX_VALUE;

    /**
     * n * n的矩阵 回溯算法实现
     *
     * @param i    矩阵行
     * @param j    矩阵列
     * @param dist 矩阵值
     * @param w    n * n的矩阵
     * @param n    n * n的矩阵的边长
     */
    public void minDistBT(int i, int j, int dist, int[][] w, int n) {
        //达到了 n - 1 ，n - 1 这个位置了
        if (i == n && j == n) {
            if (dist < minDist) {
                minDist = dist;
                return;
            }
        }

        //往下走，更新i=i+1,j=j
        if (i < n) {
            minDistBT(i + 1, j, dist + w[i][j], w, n);
        }

        //往右走，更新i=i,j=j+1
        if (j < n) {
            minDistBT(i, j + 1, dist + w[i][j], w, n);
        }
    }


    /**
     * n * n的矩阵 动态规划算法——状态转移表法实现
     *
     * @param matrix 矩阵
     * @param n      矩阵边长
     * @return
     */
    public int minDistDP(int[][] matrix, int n) {
        int[][] states = new int[n][n];
        int sum = 0;
        //初始化states的第一行数据
        for (int j = 0; j < n; ++j) {
            sum += matrix[0][j];
            states[0][j] = sum;
        }

        sum = 0;

        //初始化第一列数据
        for (int i = 0; i < n; ++i) {
            sum += matrix[i][0];
            states[i][0] = sum;
        }

        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < n; ++j) {
                states[i][j] = matrix[i][j] + Math.min(states[i][j - 1], states[i - 1][j]);
            }
        }

        return states[n - 1][n - 1];
    }
}

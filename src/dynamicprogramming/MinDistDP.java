
package dynamicprogramming;

/**
 * 矩阵问题——动态规划——状态转移方程法
 *
 * @author <a href="mailto:your mail">Tu_ZhengSong(your mail)</a>
 */
public class MinDistDP {
    private int[][] matrix = {{1, 3, 5, 9}, {2, 1, 3, 4}, {5, 2, 6, 7}, {6, 8, 4, 3}};

    private int n = 4;
    private int[][] men = new int[4][4];

    /**
     * 调用 minDist(n-1,n-1)
     *
     * @param i
     * @param j
     * @return
     */
    public int minDist(int i, int j) {

        //因为调用是minDist(n-1,n-1) ，开始调用的总是 n-1,这里是3
        // i == 0 && j == 0表示矩阵已经走完，直接返回，终止递归迭代
        if (i == 0 && j == 0) {
            return matrix[0][0];
        }

        //这里考察的是men[i][j]，men初始化的时候，默认值是0，
        // 所以这里在正常迭代考察的时候=0 ，如果这个位置已经被考察过，那么直接返回，不用继续考察
        if (men[i][j] > 0) {
            return men[i][j];
        }

        int minLeft = Integer.MAX_VALUE;

        //递归考察左边
        if (j - 1 >= 0) {
            minLeft = minDist(i, j - 1);
        }

        int minUp = Integer.MIN_VALUE;
        //递归考察上面
        if (i - 1 >= 0) {
            minUp = minDist(i - 1, j);
        }

        //状态转移方程
        int currMinDist = matrix[i][j] + Math.min(minLeft, minUp);
        men[i][j] = currMinDist;
        return currMinDist;
    }

}

package dynamicprogramming;

/**
 * 莱文斯坦距离
 *
 * @author <a href="mailto:your mail">Tu_ZhengSong(your mail)</a>
 */
public class ListDist {
    private char[] a = "mitcmu".toCharArray();
    private char[] b = "mtacnu".toCharArray();
    private int n = 6;
    private int m = 6;

    //存储结果
    private int minDist = Integer.MAX_VALUE;

    /**
     * 调用方式 lwstBT(0,0,0)
     *
     * @param i
     * @param j
     * @param edist
     */
    public void lwstBT(int i, int j, int edist) {

        //其中一个字符串比较完毕
        if (i == n || j == m) {

            //说明是j == m ，此时i未比较完毕，edist填充剩下的
            if (i < n) {
                edist += (n - 1);
            }
            if (j < m) {
                edist += (m - 1);
            }

            //minDist用来记录最小的edist
            if (edist < minDist) {
                minDist = edist;
            }
        }
        //两个字符串匹配
        if (a[i] == b[i]) {
            lwstBT(i + 1, j + 1, edist);
        } else {
            //两个字符串不匹配

            //删除a[i]或者b[j]前面添加一个字符
            lwstBT(i + 1, j, edist + 1);
            //删除b[j]或者a[i]前面添加一个字符
            lwstBT(i, j + 1, edist + 1);
            //将a[i]和b[j]替换为相同字符
            lwstBT(i + 1, j + 1, edist + 1);
        }
    }


    /**
     * 动态规划实现
     *
     * @param a
     * @param n
     * @param b
     * @param m
     * @return
     */
    public int lwstDP(char[] a, int n, char[] b, int m) {
        int[][] minDist = new int[n][m];

        //初始化第0行：a[0..0] 与 b[0..j] 的编辑距离
        for (int j = 0; j < m; ++j) {
            if (a[0] == b[j]) {
                minDist[0][j] = j;
            } else if (j != 0) {
                minDist[0][j] = minDist[0][j - 1] + 1;
            } else {
                minDist[0][j] = 1;
            }
        }

        //初始化第0列：a[0..i] 与 b[0..0] 的编辑距离
        for (int i = 0; i < n; ++i) {
            if (a[i] == b[0]) {
                minDist[i][0] = i;
            } else if (i != 0) {
                minDist[i][0] = minDist[i - 1][0] + 1;
            } else {
                minDist[i][0] = 1;
            }
        }


        //按行填表
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (a[i] == b[j]) {
                    minDist[i][j] = min(minDist[i - 1][j] + 1, minDist[i][j - 1] + 1, minDist[i - 1][j - 1]);
                } else {
                    minDist[i][j] = min(minDist[i - 1][j] + 1, minDist[i][j - 1] + 1, minDist[i - 1][j - 1] + 1);
                }
            }
        }
        return minDist[n - 1][m - 1];
    }


    private int min(int x, int y, int z) {
        int minv = Integer.MAX_VALUE;
        if (x < minv) {
            minv = x;
        }
        if (y < minv) {
            minv = y;
        }
        if (z < minv) {
            minv = z;
        }

        return minv;
    }

    /**
     * 计算最长公共子串长度
     *
     * @param a
     * @param n
     * @param b
     * @param m
     * @return
     */
    public int lcs(char[] a, int n, char[] b, int m) {
        int[][] maxlcs = new int[n][m];
        //初始化第0行：a[0..0] 与 b[0..j] 的编辑距离
        for (int j = 0; j < m; ++j) {
            if (a[0] == b[j]) {
                maxlcs[0][j] = 1;
            } else if (j != 0) {
                maxlcs[0][j] = maxlcs[0][j - 1];
            } else {
                maxlcs[0][j] = 0;
            }
        }

        //初始化第0列：a[0..i] 与 b[0..0] 的编辑距离
        for (int i = 0; i < n; ++i) {
            if (a[i] == b[0]) {
                maxlcs[i][0] = 1;
            } else if (i != 0) {
                maxlcs[i][0] = maxlcs[i - 1][0];
            } else {
                maxlcs[i][0] = 0;
            }
        }

        //填表
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (a[i] == b[j]) {
                    maxlcs[i][j] = max(maxlcs[i - 1][j], maxlcs[i][j - 1], maxlcs[i - 1][j - 1] + 1);
                } else {
                    maxlcs[i][j] = max(maxlcs[i - 1][j], maxlcs[i][j - 1], maxlcs[i - 1][j - 1]);
                }
            }
        }
        return maxlcs[n - 1][m - 1];
    }

    private int max(int x, int y, int z) {
        int maxv = Integer.MIN_VALUE;
        if (x > maxv) {
            maxv = x;
        }
        if (y > maxv) {
            maxv = y;
        }
        if (z > maxv) {
            maxv = z;
        }

        return maxv;
    }

}

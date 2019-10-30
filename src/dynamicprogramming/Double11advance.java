
package dynamicprogramming;

/**
 * 动态规划——双十一问题
 *
 * @author <a href="mailto:your mail">Tu_ZhengSong(your mail)</a>
 */
public class Double11advance {

    /**
     * @param items 商品价格
     * @param n     商品个数
     * @param w     满减条件 比如200
     */
    public static void double11advance(int[] items, int n, int w) {
        //超过3倍没有薅羊毛的价值
        boolean[][] states = new boolean[n][3 * w + 1];
        //第一行数据特殊处理
        states[0][0] = true;
        if (items[0] <= 3 * w) {
            states[0][items[0]] = true;
        }

        //动态规划
        for (int i = 1; i < n; ++i) {
            //不购买第i个商品
            for (int j = 0; j < 3 * w; ++j) {
                if (states[i - 1][j] == true) {
                    states[i][j] = states[i - 1][j];
                }
            }

            //购买第i个商品
            for (int j = 0; j <= 3 * w - items[i]; ++j) {
                if (states[i - 1][j] == true) {
                    states[i][j + items[i]] = true;
                }
            }
        }

        int j;
        for (j = w; j < 3 * w + 1; ++j) {
            if (states[n - 1][j] == true) {
                //出书结果大于等于w的最小值
                break;
            }
        }

        //没有可行解
        if (j == 3 * w + 1) {
            return;
        }

        for (int i = n - 1; i >= 1; --i) {
            //i表示二维数组中的行，j表示列
            if (j - items[i] >= 0 && states[i - 1][j - items[i]] == true) {
                //购买这个商品
                System.out.println(items[i] + " ");
                j = j - items[i];
            }//else没有购买这个商品，j不变
        }
        if (j != 0) {
            System.out.println(items[0]);
        }

    }
}

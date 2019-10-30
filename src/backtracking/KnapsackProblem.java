package backtracking;

/**
 * 回溯算法——背包问题
 *
 * @author <a href="mailto:your mail">Tu_ZhengSong(your mail)</a>
 */
public class KnapsackProblem {
    //存储背包中物品总重量的最大值
    public int maxW = Integer.MAX_VALUE;

    /**
     * 假设背包可承受重量100，物品个数10，物品重量存储在数组a中，那可以这样调用函数
     * <p>
     * f(0,0,a,10,100)
     *
     * @param i     表示考察到哪个物品了
     * @param cw    表示已经装进去的物品的重量和
     * @param items 表示每个物品的重量
     * @param n     表示物品个数
     * @param w     背包重量
     */
    public void f(int i, int cw, int[] items, int n, int w) {
        //cw == w表示装满了；i == n表示考察完所有的物品
        if (cw == w || i == n) {
            if (cw > maxW) {
                maxW = cw;
            }
            return;
        }
        f(i + 1, cw, items, n, w);
        //已经超过背包可以承受的重量的时候，就不要再装了
        if (cw + items[i] <= w) {
            f(i + 1, cw + items[i], items, n, w);
        }
    }
}


package stringmatching;

/**
 * 字符串匹配 kmp算法
 *
 * @author <a href="mailto:your mail">Tu_ZhengSong(your mail)</a>
 */
public class KMP {

    /**
     * @param a 主串
     * @param n 主串长度
     * @param b 模式串
     * @param m 模式串长度
     * @return
     */
    private static int kmp(char[] a, int n, char[] b, int m) {
        int[] next = getNexts(b, m);
        int j = 0;
        for (int i = 0; i < n; i++) {
            //一直找到a[i]和b[j]
            while (j > 0 && a[i] != b[j]) {
                //模式串往后移动
                j = next[j - 1] + 1;
            }

            if (a[i] == b[j]) {
                ++j;
            }
            //找到匹配模式串
            if (j == m) {
                return i - m + 1;
            }
        }

        return -1;
    }

    /**
     * 失效函数算法
     *
     * @param b 模式串
     * @param m 模式串长度
     * @return
     */
    private static int[] getNexts(char[] b, int m) {
        int[] next = new int[m];
        next[0] = -1;
        int k = -1;

        for (int i = 1; i < m; ++i) {
            while (k != -1 && b[k + 1] != b[i]) {
                k = next[k];
            }

            if (b[k + 1] == b[i]) {
                ++k;
            }
            next[i] = k;
        }
        return next;
    }
}

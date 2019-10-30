
package stringmatching;

/**
 * BM 字符串匹配算法
 *
 * @author <a href="mailto:your mail">Tu_ZhengSong(your mail)</a>
 */
public class BM {
    //用ASCII码与字符对应
    private static final int SIZE = 256;

    /**
     * 生成bc
     *
     * @param b  模式串
     * @param m  模式串的长度
     * @param bc 散列表
     */
    private void generateBC(char[] b, int m, int[] bc) {
        for (int i = 0; i < SIZE; i++) {
            //初始化bc
            bc[i] = -1;
        }

        for (int i = 0; i < m; i++) {
            //计算b[i]的ASCII值
            int ascii = b[i];
            bc[ascii] = i;
        }
    }


    /**
     * @param a 主串
     * @param n 主串长度
     * @param b 模式串
     * @param m 模式串长度
     * @return
     */
    public int bm(char[] a, int n, char[] b, int m) {
        //记录模式串中每个字符最后出现的位置
        int[] bc = new int[SIZE];
        //构建坏字符哈希表
        generateBC(b, m, bc);

        int[] suffix = new int[m];
        boolean[] prefix = new boolean[m];

        generateGS(b, m, suffix, prefix);

        //表示主串与模式串对齐的第一个字符
        int i = 0;

        while (i <= n - m) {
            int j;
            //模式串从后往前匹配
            for (j = m - 1; j >= 0; --j) {
                //模式串对应的下标是j
                if (a[i + j] != b[j]) {
                    break;
                }
            }
            if (j < 0) {
                //匹配成功，返回主串与模式串第一个匹配的字符的位置
                return i;
            }
            //这里等同于将模式串往后华东j-bc[(int) a[i + j]]位
            // a[i + j] 是主串中的坏字符位置
            // bc[(int) a[i + j]] 是坏字符在模式串中最后出现的位置
            // j - bc[(int) a[i + j]] 是出现坏字符后，需要移动的位置
//            i = i + (j - bc[(int) a[i + j]]);
            int x = j - bc[(int) a[i + j]];
            int y = 0;
            if (j < m - 1) {
                //如果有好后缀的话
                y = moveByGS(j, m, suffix, prefix);
            }
            i = i + Math.max(x, y);
        }
        return -1;
    }

    /**
     * @param j      表示坏字符对应的模式串中的字符下标
     * @param m      表示目视串的长度
     * @param suffix
     * @param prefix
     * @return
     */
    private int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
        //好后缀的长度
        int k = m - 1 - j;

        //-1表示不存在匹配的子串
        //suffix[k] != -1表示模式串中存在匹配的子串
        if (suffix[k] != -1) {
            //j表示坏字符对应的模式串中的字符下标
            return j - suffix[k] + 1;
        }

        //表示模式串中不存在另一个跟好后缀匹配的子串片段
        //j + 1表示好后缀开始，因为到这一步，说明不存在好后缀，所以j + 2，表明搜索范围更小的后缀子串
        for (int r = j + 2; r < m - 1; ++r) {

            // k = m - r, prefix[m - r] ==true，表明长度为k的后缀子串，有可匹配的前缀子串，可以把模式串后移r位
            //后移r位达到的效果是，模式串中长度为k的前缀子串移动到了匹配的后缀子串位置
            if (prefix[m - r] == true) {
                return r;
            }
        }

        return m;
    }


    /**
     * 好后缀
     *
     * @param b      模式串
     * @param m      长度
     * @param suffix 公共后缀子串
     * @param prefix
     */
    private void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
        //初始化
        for (int i = 0; i < m; i++) {
            suffix[i] = -1;
            prefix[i] = false;
        }

        for (int i = 0; i < m - 1; ++i) {
            int j = i;
            //公共后缀子串长度
            int k = 0;

            //与 b[0,m-1]求公共后缀子串
            while (j >= 0 && b[j] == b[m - 1 - k]) {
                --j;
                ++k;
                //j + 1 表示公共后缀子串在b[0,i]中的起始下标
                suffix[k] = j + 1;
            }
            if (j == -1) {
                prefix[k] = true;
            }
        }
    }
}

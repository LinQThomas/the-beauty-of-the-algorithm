
package sort_13;

/**
 * 记数排序
 *
 * @author <a href="mailto:your mail">Tu_ZhengSong(your mail)</a>
 */
public class CountingSort {

    /**
     * 记数排序 假设数组中存储的都是非负整数
     *
     * @param a 数组
     * @param n 数组大小
     */
    public static void countingSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }

        //查找数组中数组范围
        int max = a[0];
        for (int i = 0; i < n; ++i) {
            if (max < a[i]) {
                max = a[i];
            }
        }


        //申请一个计数数组c，下标大小[0,max]
        int[] c = new int[max + 1];
        for (int i = 0; i < max; ++i) {
            c[i] = 0;
        }

        //计算每个元素的个数，放入c中
        for (int i = 0; i < n; ++i) {
            c[a[i]]++;
        }

        //依次累加
        for (int i = 0; i < max + 1; ++i) {
            c[i] = c[i - 1] + c[i];
        }

        //临时数组，存储排序之后的结果
        int[] r = new int[n];
        //记数关键步骤
        for (int i = n - 1; i >= 0; --i) {
            int index = c[a[i]] - 1;
            r[index] = a[i];
            c[a[i]]--;
        }

        //将结果拷贝回a数组
        for (int i = 0; i < n; i++) {
            a[i] = r[i];
        }
    }

}

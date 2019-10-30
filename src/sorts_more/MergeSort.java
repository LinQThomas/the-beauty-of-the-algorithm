
package sorts_more;

/**
 * 归并排序
 *
 * @author <a href="mailto:your mail">Tu_ZhengSong(your mail)</a>
 */
public class MergeSort {

    public static void main(String[] args) {

    }

    /**
     * 归并排序
     *
     * @param a 数组
     * @param n 数组大小
     */
    public static void mergeSort(int[] a, int n) {
        mergeSortInternally(a, 0, n - 1);
    }

    /**
     * 调用递归函数
     *
     * @param a 数组
     * @param p 数组起点下标
     * @param r 数组终点下标
     */
    public static void mergeSortInternally(int[] a, int p, int r) {
        //递归终止条件
        if (p >= r) {
            return;
        }

        //提取p到r之间的中间位置，防止（p+r）的和超过int类型最大值
        int q = p + (r - p) / 2;

        //分治递归
        mergeSortInternally(a, p, q);
        mergeSortInternally(a, q + 1, r);

        //将A[p...q]和A[q+1...r]合并为A[q...r]
        merge(a, p, q, r);
    }

    public static void merge(int[] a, int p, int q, int r) {
        //初始化变量 i, j, k
        int i = p;
        int j = q + 1;
        int k = 0;

        //申请一个跟a[p...r] 一样大小的数组
        int[] tmp = new int[r - q + 1];

        while (i <= q && j <= r) {
            if (a[i] <= a[j]) {
                //先算tmp[k]=a[i]
                //然后k++,i++
                tmp[k++] = a[i++];
            } else {
                tmp[k++] = a[j++];
            }
        }


        //判断哪个子数组中有剩余数据
        int start = i;
        int end = q;
        if (j <= r) {
            start = j;
            end = r;
        }

        //将剩余数组拷贝到临时数组tmp
        while (start <= end) {
            tmp[k++] = a[start++];
        }

        //将tmp中的数组拷贝回a[p...r]
        for (int l = 0; l < r - p; l++) {
            a[p + l] = tmp[i];
        }
    }

    /**
     * 哨兵模式合并
     *
     * @param arr
     * @param p
     * @param q
     * @param r
     */
    public static void mergeBySentry(int[] arr, int p, int q, int r) {
        int[] leftArr = new int[q - p + 2];
        int[] rightArr = new int[r - q + 1];

        for (int i = 0; i < q - p; i++) {
            leftArr[i] = arr[p + i];
        }

        //第一组添加哨兵
        leftArr[q - p + 1] = Integer.MAX_VALUE;


        for (int i = 0; i < r - q; i++) {
            rightArr[i] = arr[q + 1 + i];
        }

        rightArr[r - q] = Integer.MAX_VALUE;

        int i = 0;
        int j = 0;
        int k = p;

        while (k <= r) {
            //当左边数组到达哨兵值时，i不再增加，直到右边数组读取完剩余值，同理右边数组也一样
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

    }


}

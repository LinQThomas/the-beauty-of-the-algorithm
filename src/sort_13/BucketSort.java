
package sort_13;

import java.util.Arrays;

/**
 * 桶排序算法
 *
 * @author <a href="mailto:your mail">Tu_ZhengSong(your mail)</a>
 */
public class BucketSort {

    public static void main(String[] args) {
        int[] arr = new int[]{22, 5, 11, 41, 45, 26, 29, 10, 7, 8, 30, 27, 42, 43, 40};
        bucketSort(arr, 10);
        System.out.println(Arrays.toString(arr));

    }



    /**
     * 桶排序
     *
     * @param arr        数组
     * @param bucketSize 桶容量
     */
    public static void bucketSort(int[] arr, int bucketSize) {
        if (arr.length < 2) {
            return;
        }

        //数组最小值
        int minVaue = arr[0];
        //数组最大值
        int maxValue = arr[1];

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < minVaue) {
                minVaue = arr[i];
            } else if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }


        //桶数量
        int bucketCount = (maxValue - minVaue) / bucketSize + 1;
        int[][] buckets = new int[bucketCount][bucketSize];
        int[] indexArr = new int[bucketCount];

        //将数组中值分配到各个桶里
        for (int i = 0; i < arr.length; i++) {
            int bucketIndex = (arr[i] - minVaue) / bucketSize;
            if (indexArr[bucketIndex] == buckets[bucketIndex].length) {
                ensureCapacity(buckets, bucketIndex);
            }
            buckets[bucketIndex][indexArr[bucketIndex]++] = arr[i];
        }

        //对每个桶进行排序，这里使用了快速排序
        int k = 0;
        for (int i = 0; i < buckets.length; i++) {
            if (indexArr[i] == 0) {
                continue;
            }
            quickSortC(buckets[i], 0, indexArr[i] - 1);
            for (int j = 0; j < indexArr[i]; j++) {
                arr[k++] = buckets[i][j];
            }

        }
    }


    /**
     * 数组扩容
     *
     * @param buckets     桶
     * @param bucketIndex 桶下标
     */
    private static void ensureCapacity(int[][] buckets, int bucketIndex) {
        int[] tempArr = buckets[bucketIndex];
        int[] newArr = new int[tempArr.length * 2];

        for (int j = 0; j < tempArr.length; j++) {
            newArr[j] = tempArr[j];
        }
        buckets[bucketIndex] = newArr;
    }


    /**
     * 快速排序
     *
     * @param arr 数组
     * @param p   数组起点
     * @param r   数组终点
     */
    private static void quickSortC(int[] arr, int p, int r) {

        if (p >= r) {
            return;
        }

        int q = partition(arr, p, r);
        quickSortC(arr, p, q - 1);
        quickSortC(arr, q + 1, r);
    }


    /**
     * 分区函数
     *
     * @param arr 数组
     * @param p   数组起点
     * @param r   数组终点
     * @return 分区点
     */
    private static int partition(int[] arr, int p, int r) {
        int pivot = arr[r];
        int i = p;
        for (int j = p; j < r; j++) {
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }

        swap(arr, i, r);
        return i;
    }

    /**
     * 交换元素
     *
     * @param arr 数组
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }

        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

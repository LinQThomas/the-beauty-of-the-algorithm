
package sorts;

import com.sun.tools.javac.util.ArrayUtils;

/**
 * 冒泡排序、插入排序、选择排序
 *
 * @author <a href="mailto:your mail">Tu_ZhengSong(your mail)</a>
 */
public class Sorts {


    public static void main(String[] args) {
        int[] a = new int[]{6, 5, 4, 3, 2, 1};
//        Sorts.bubbleSort(a, a.length);
        Sorts.selectionSort(a, a.length);
    }

    /**
     * 冒泡排序,我自己实现的
     *
     * @param a 数组
     * @param n 数组大小
     */
//    public static void bubbleSort(int[] a, int n) {
//        //校验数组大小
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n-1; j++) {
//                //比较相邻两个数组大小
//                if (a[j] > a[j + 1]) {
//                    int tem = a[j];
//                    a[j] = a[j + 1];
//                    a[j + 1] = tem;
//                }
//            }
//        }
//        System.out.println();
//    }
    public static void bubbleSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }

        for (int i = 0; i < n; ++i) {
            //提前退出标志位
            boolean flag = false;
            // n - i - 1表示过滤掉已经排好序的内容
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    //交换
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                    // 此次冒泡有数据交换
                    flag = true;
                }
                //没有数据交换则退出

            }
            if (!flag) {
                break;
            }
        }
        System.out.println();
    }

    /**
     * 冒泡排序改进：在每一轮排序后记录最后一次元素交换的位置，作为下次比较的边界
     * 对于外界的元素在下次循环中无需比较
     *
     * @param a 数组位置
     * @param n 数组大小
     */
    public static void bubbleSorts2(int[] a, int n) {
        if (n <= 1) {
            return;
        }
        //最后一次交换的位置
        int lastExchange = 0;
        //无序数组的边界，每次只需要比较到这里即可退出
        int sortBorder = n - 1;
        for (int i = 0; i < n; i++) {
            // 提前退出标志位
            boolean flag = false;
            for (int j = 0; j < sortBorder; j++) {
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                    flag = true;
                    lastExchange = j;
                }
            }
            sortBorder = lastExchange;

            System.out.println(lastExchange);

            //如果没有数据交换，提前退出
            if (!flag) {
                break;
            }

            //如果最后一次移动的位置等于0，提前退出
            if (lastExchange == 0) {
                break;
            }
        }
    }

    /**
     * 冒泡排序
     */
    public void insertSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }

        for (int i = 1; i < n; i++) {
            int value = a[i];
            int j = i - 1;

            for (; j >= 0; --j) {
                if (a[j] > value) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }

            a[j + 1] = value;
        }
    }

    /**
     * 选择排序，a表示数组，n表示数组大小
     *
     * @param a
     * @param n
     */
    //自己写的
//    public static void selectionSort(int[] a, int n) {
//        if (n <= 1) {
//            return;
//        }
//
//
//        for (int i = 0; i < n; i++) {
//
//            int min = a[i];
//            int minIndex = i;
//            for (int j = i; j < n - 1; j++) {
//
//                if (min > a[j + 1]) {
//                    min = a[j + 1];
//                    ++minIndex;
//                }
//
//            }
//            int tmp = a[i];
//            a[i] = min;
//            a[minIndex] = tmp;
//        }
//
//        System.out.println();
//    }

    public static void selectionSort(int[] a, int n){
        if (n <= 1) {
            return;
        }

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }

            //交换
            int tmp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = tmp;
        }


    }

}

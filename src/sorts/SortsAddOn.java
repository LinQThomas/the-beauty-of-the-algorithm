
package sorts;

/**
 * 描述本类的作用，需要注意的地方.
 *
 * @author <a href="mailto:your mail">Tu_ZhengSong(your mail)</a>
 */
public class SortsAddOn {


    public static void main(String[] args) {
        int[] arr = {3, 2, 6, 4, 5, 1, 9, 20, 13, 16};
        // bubbleDownSort(arr);
        shellSort(arr);
        print(arr);
    }
    /**
     * 希尔排序
     * @param arr
     */
    private static void shellSort(int[] arr) {
        int len = arr.length;
        if (len == 1) {
            return;
        }

        int step = len / 2;
        while (step >= 1) {
            for (int i = step; i < len; i++) {
                int value = arr[i];
                int j = i - step;
                for (; j >= 0; j -= step) {
                    if (value < arr[j]) {
                        arr[j+step] = arr[j];
                    } else {
                        break;
                    }
                }
                arr[j+step] = value;
            }
            step = step / 2;
        }
    }

    private static void print(int[] arr) {
        System.out.println("Print array:");
        for (int x : arr) {
        System.out.print(x + "\t");
        }
        System.out.println("");
    }
}


package queue;

/**
 * 用数组实现的队列
 *
 * @author <a href="mailto:your mail">Tu_ZhengSong(your mail)</a>
 */
public class ArrayQueue {

    /**
     * 数组items
     */
    private String[] items;

    /**
     * 数组大小
     */
    private int n = 0;

    /**
     * head表示队头下标，tail表示队尾下标
     */
    private int head = 0;
    private int tail = 0;


    /**
     * 申请一个大小为capacity的数组
     *
     * @param capacity 数组大小
     */
    public ArrayQueue(int capacity) {
        items = new String[capacity];
        n = capacity;
    }

    /**
     * 入队
     *
     * @param item 入队的元素
     * @return 入队操作结果
     */
//    public boolean enqueue(String item) {
//        //表示队列已经满了
//        if (tail == n) {
//            return false;
//        }
//
//        items[tail] = item;
//        ++tail;
//        return true;
//    }

    //入队优化，将item放入队尾
    public boolean enqueue(String item) {
        //tail==n表示队尾巴没有空间了
        if (tail == n) {
            //tail==n　&& head==0表示整个队列都满了
            if (head == 0) {
                return false;
            }

            //数据搬移
            for (int i = head; i < tail; i++) {
                items[i - head] = items[i];
            }
            //搬移完成之后重新更新head和tail
            tail -= head;
            head = 0;
        }

        items[tail] = item;
        ++tail;
        return true;

    }


    /**
     * 出队
     *
     * @return 返回操作之前的队列头
     */
    public String dequeue() {
        //表示队列为空
        if (head == 0) {
            return null;
        }
        String ret = items[head];
        ++head;
        return ret;
    }
}

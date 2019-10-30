
package queue;

/**
 * 循环队列实现
 *
 * @author <a href="mailto:your mail">Tu_ZhengSong(your mail)</a>
 */
public class CircularQueue {
    /**
     * 数组：items 数组大小n
     */
    private String[] items;

    private int n = 0;

    /**
     * head 表示队头下标，tail表示队尾下标
     */
    private int head;

    private int tail;

    /**
     * 申请大小为capacity的数组
     * @param capacity  数组大小
     */
    public CircularQueue(int capacity) {
        items = new String[capacity];
        n = capacity;
    }

    public boolean enqueue(String item) {
        //队列满了
        if ((tail + 1) % n == head) {
            return false;
        }
        items[tail] = item;
        /*
        * 入队时尾指针向前追赶头指针
        *  % 取模运算是为了将循环队列首尾相连，当 n = 8 ，tail = 7 的时候，
        *  一个新元素入队，如果不是循环列表，tail 应该 = 8，
        *  在循环列表中，8要叠加在0的位置上，相当于tail > n 的时候，tail要归零，与取模运算吻合
        *  循环队列中的头是0，因此取摸运算
        * */
        tail = (tail + 1) % n;
        return true;
    }

    public String dequeue() {
        //重点是出队列时队列为空的判断
        if (head == tail) {
            return null;
        }

        String ret = items[head];
        head = (head + 1) % n;
        return ret;
    }

}

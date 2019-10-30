
package queue;

/**
 * @author <a href="mailto:your mail">Tu_ZhengSong(your mail)</a>
 */
public class DynamicArrayQueue {
    // 数组：items，数组大小：n
    private String[] items;
    private int n = 0;
    // head表示队头下标，tail表示队尾下标
    private int head = 0;
    private int tail = 0;

    // 申请一个大小为capacity的数组
    public DynamicArrayQueue(int capacity) {
        items = new String[capacity];
        n = capacity;
    }

    /**
     * 入队操作，将item放在队尾
     *
     * @param item 入队的元素
     * @return 操作结果
     */
    public boolean enqueue(String item) {
        //tail == n表示队列末尾没有空间了
        if (tail == n) {
            //tail ==n && head==0，表示整个队列都占满了
            if (head == 0) {
                return false;
            }
            //数据搬移
            for (int i = head; i < tail; i++) {
                items[i - head] = items[i];
            }

            //数据搬移完成之后，更新head和tail
            tail -= head;
            head = 0;
        }
        items[tail] = item;
        tail++;
        return true;
    }

    /**
     * 出队
     * @return 出队的元素数据
     */
    public String dequeue() {
        //判断队列是否为空
        if (head == tail) {
            return null;
        }
        String ret = items[head];
        ++head;
        return ret;
    }
}

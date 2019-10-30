
package stack;

/**
 * 基于数组实现的顺序栈
 *
 * @author <a href="mailto:your mail">Tu_ZhengSong(your mail)</a>
 */
public class ArrayStack {
    /**
     * 数组
     */
    private String[] items;

    /**
     * 栈中元素个数
     */
    private int count;

    /**
     * 栈的大小
     */
    private int n;

    /**
     * 初始化数组，申请一个大小为n的数组空间
     *
     * @param n 栈的大小
     */
    public ArrayStack(int n) {
        this.items = new String[n];
        this.n = n;
        this.count = 0;
    }

    /**
     * 入栈操作
     *
     * @param item 入栈元素
     * @return 入栈结果
     */
    public boolean push(String item) {
        //校验栈的剩余空间，如果数组空间不够用，返回false，入栈失败
        if (count == n) {
            return false;
        }
        items[count] = item;
        ++count;
        return true;
    }

    public String pop() {
        //栈为空，则返回null
        if (count == 0) {
            return null;
        }

        //返回下标为count-1的数组元素，并且栈中元素个数为count - 1
        //这里的items[conut]元素仍然存在，但是可以被替换
        String tmp = items[count - 1];
        --count;
        return tmp;
    }
}


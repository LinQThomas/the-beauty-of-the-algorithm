
package stack;


/**
 * 基于链表实现的栈
 *
 * @author <a href="mailto:your mail">Tu_ZhengSong(your mail)</a>
 */
public class StackBasedOnLinkedList {

    private Node top = null;

    public void push(int value) {
        Node newNode = new Node(value, null);
        //判断是否空栈区
        if (top == null) {
            top = newNode;
        } else {
            //newNode成为新的栈顶元素
            newNode.next = top;
            top = newNode;
        }
    }


    public int pop() {
        //校验是否空栈区
        if (top == null) {
            return -1;
        }
        int value = top.data;
        top = top.next;
        //返回的是出栈的元素数据
        return value;
    }

    public void pringAll() {
        Node p = top;
        while (p.next != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();

    }


    private static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }

}

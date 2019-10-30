
package queue;

/**
 * 基于链表实现的队列
 *
 * @author <a href="mailto:your mail">Tu_ZhengSong(your mail)</a>
 */
public class QueueBasedOnLinkedList {

    public static void main(String[] args) {
        QueueBasedOnLinkedList queueBasedOnLinkedList = new QueueBasedOnLinkedList();
        queueBasedOnLinkedList.enqueue("1");
        queueBasedOnLinkedList.enqueue("2");
        queueBasedOnLinkedList.printAll();
        queueBasedOnLinkedList.dequeue();
    }


    /**
     * 队列的队首和队尾
     */
    private Node head = null;
    private Node tail = null;


    /**
     * 入队
     *
     * @param value 入队的元素
     */
    public void enqueue(String value) {
        //表示队列为空
        if (tail == null) {
            Node newNode = new Node(value, null);
            head = newNode;
            tail = newNode;
        } else {
            tail.next = new Node(value, null);
            tail = tail.next;
        }
    }


    /**
     * 出队
     *
     * @return 出队的元素数据
     */
    public String dequeue() {
        if (head == null) {
            return null;
        }

        String value = head.data;
        head = head.next;
        //再次校验head指针是否为null，如果为null，说明队列已空，但是此时tail指针仍然保存队尾的数据
        if (head == null) {
            tail = null;
        }
        return value;

    }

    public void printAll() {
        Node p = head;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    private static class Node {
        private String data;
        private Node next;

        public Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }

        public String getData() {
            return data;
        }
    }
}


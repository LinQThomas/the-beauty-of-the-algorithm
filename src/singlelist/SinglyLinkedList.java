
package singlelist;

/**
 * 描述本类的作用，需要注意的地方.
 *
 * @author <a href="mailto:your mail">Tu_ZhengSong(your mail)</a>
 */
public class SinglyLinkedList {

//    private Node head = null;

    //带头链表
    private Node head = null;

    public Node findByValue(int value) {
        Node p = head;
        while (p.data != value) {
            p = p.next;
        }
        return p;
    }

    public Node findByIndex(int index) {
        Node p = head;
        int start = 0;
        while (start != index) {
            p = p.next;
            start++;
        }
        return p;
    }


    //无头节点
    //表头部插入
    public void insertToHead(int value) {
        Node newNode = new Node(value, null);
        insertToHead(newNode);
    }

    //在头部插入
    public void insertToHead(Node newNode) {
        //哨兵模式，判断是否是空链表
//        if (head == null) {
//            head = newNode;
//        } else {
        //不是空链表，newNode的next指针指向现在的head，
        newNode.next = head;
        //head指针指向新的头部
        head = newNode;
//        }
    }

    //顺序插入，在链表尾部插入
    public void insertTail(int value) {
        Node newNode = new Node(value, null);
        Node q = head;
        //找到链表末尾

        if (head == null) {
            head = newNode;

        } else {
            while (q.next != null) {
                q = q.next;
            }
            q.next = newNode;
        }
    }

    //在指定位置插入
    public void insertAfter(Node p, int value) {
        Node newNode = new Node(value, null);
        insertAfter(p, newNode);
    }

    public void insertAfter(Node p, Node newNode) {
        if (p == null) {
            return;
        }
        //先设置newNode.next，防止指针丢失
        newNode.next = p.next;
        p.next = newNode;
    }

    public void insertBefore(Node p, int value) {
        Node newNode = new Node(value, null);
        insertBefore(p, newNode);
    }

    public void insertBefore(Node p, Node newNode) {
        if (p == null) {
            return;
        }

        //如果p是头节点
        if (head == p) {
            insertToHead(newNode);
            return;
        }

        Node q = head;
        //寻找p前面的节点
        while (q.next != p) {
            //单链表，最终会走到链表尾部
            q = q.next;
        }

        //找到p前面的指针
        q.next = newNode;
        newNode.next = p;
    }

    public void deleteByNode(Node p) {

        //null校验，参数为null，或者链表为null，这里校验的是函数操作的关联主体
        if (p == null || head == null) {
            return;
        }

        //校验参数是否是单链表头部
        if (p == head) {
            //如果是的话，head指针向后移动一位，因为这里涉及到单链表的头部指针
            head = head.next;
            return;
        }

        Node q = head;
        //单链表向后查找
        while (q != null && q.next != p) {
            q = q.next;
        }

        //q是否走完单链表，当q走完单链表，q会出现q = q.next
        if (q == null) {
            return;
        }
        //如果p是链表尾部
        p.next = p.next.next;
    }

    public void deleteByValue(int value) {
        //如果队列为null
        if (head == null) {
            return;
        }

        Node p = head;
        Node q = null;

        while (p != null && p.data != value) {
            //防止指针丢失
            q = p;
            p = p.next;
        }

        //走到单链表末尾，未发现value
        if (p == null) {
            return;
        }

        //q==null，表示单链表仅有head，此时改变head指针
        if (q == null) {
            head = head.next;
        } else {
            //否则删除节点
            p.next = p.next.next;
        }


    }

    //判断是否回文
    public boolean palindrome() {
        //判断链表是否正常
        if (head == null) {
            return false;
        }

        //慢指针
        Node p;
        //快指针
        Node q;

        p = q = head;
        while (q.next != null && q.next.next != null) {
            //慢指针前进一步
            p = p.next;
            //快指针前进两部
            q = q.next.next;
        }

        Node leftLink = null;
        Node rightLink = null;

        //处理单链表奇偶个数问题
        if (q.next == null) {
            //奇数链表，p慢指针处于奇数链表的中间节点
            rightLink = p.next;
            leftLink = inverseLinkList(p).next;
        } else {
            //偶数链表，慢指针处于偶数链表中间分界线前
            rightLink = p.next;
            leftLink = inverseLinkList(p);
        }

        return TFResult(leftLink, rightLink);

    }


    //无头结点的链表翻转，翻转p节点之前的链表
    public Node inverseLinkList(Node p) {
        Node pre = null;
        Node r = head;
        Node next = null;
        while (r != p) {
            //用next节点指向r.next，防止指针丢失
            next = r.next;
            //改变r.next指针，指向，前一个节点
            r.next = pre;
            //保存前一个节点
            pre = r;
            //r移动位置
            r = next;
        }

        //退出循环时，r.next指针仍未改变，需要改变
        r.next = pre;
        //　返回左半部分的中点之前的那个节点
        //　从此处开始同步像两边比较
        return r;

    }


    //判断true or false
    public boolean TFResult(Node left, Node right) {
        Node l = left;
        Node r = right;

        while (l != null && r != null) {
            if (l.data == r.data) {
                l = l.next;
                r = r.next;
            } else {
                break;
            }
        }
        return l == null && r == null;
    }


    public void printAll() {
        Node p = head;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    //静态内部类
    public static class Node {
        private int data;
        private Node next;

        public Node() {
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }

    public static Node createNode(int value) {
        return new Node(value, null);
    }


    public static void main(String[] args) {

        SinglyLinkedList link = new SinglyLinkedList();
        link.insertToHead(1);
        link.insertToHead(2);
        link.insertToHead(3);
        link.insertToHead(4);
        link.insertToHead(5);

//        link.insertTail(1);
//        link.insertTail(2);
//        link.insertTail(3);
//        link.insertTail(4);
//        link.insertTail(5);

        link.printAll();
        Node p = link.findByIndex(3);
        System.out.println(p.data);

//        link.findByIndex(3);
    }


}

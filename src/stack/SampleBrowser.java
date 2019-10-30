
package stack;

/**
 * 使用前后栈实现浏览器的前进后退。
 *
 * @author <a href="mailto:your mail">Tu_ZhengSong(your mail)</a>
 */
public class SampleBrowser {

    public static void main(String[] args) {
        SampleBrowser browser = new SampleBrowser();
        browser.open("http://www.baidu.com");
        browser.open("http://news.baidu.com/");
        browser.open("http://news.baidu.com/ent");
        browser.goBack();
        browser.goBack();
        browser.goForward();
        browser.open("http://www.qq.com");
        browser.goForward();
        browser.goBack();
        browser.goForward();
        browser.goBack();
        browser.goBack();
        browser.goBack();
        browser.goBack();
        browser.checkCurrentPage();
    }


    private String currentPage;
    private LinkedListBasedStack backStack;
    private LinkedListBasedStack forwardStack;


    public SampleBrowser() {
        this.backStack = new LinkedListBasedStack();
        this.forwardStack = new LinkedListBasedStack();
    }


    public void open(String url) {
        //如果当前页面不为空
        if (this.currentPage != null) {
            //把当前页面入栈（后退功能栈栈）
            this.backStack.push(this.currentPage);
            //前进功能栈清空
            this.forwardStack.clear();
        }
        showUrl(url, "Open");
    }

    public boolean canGoBack() {
        return this.backStack.size > 0;
    }

    public boolean canGoForward() {
        return this.forwardStack.size > 0;
    }

    public String goBack() {
        if (this.canGoBack()) {
            //bcurrentPage压入forwardStack
            this.forwardStack.push(this.currentPage);
            //backStack出栈
            String backUrl = this.backStack.pop();
            showUrl(backUrl, "Back");
            return backUrl;
        }

        System.out.println("* Cannot go back, no pages behind.");
        return null;
    }

    public String goForward() {
        if (this.canGoForward()) {
            this.backStack.push(this.currentPage);
            String forwardUrl = this.forwardStack.pop();
            showUrl(forwardUrl, "Foward");
            return forwardUrl;
        }

        System.out.println("** Cannot go forward, no pages ahead.");
        return null;
    }

    public void checkCurrentPage() {
        System.out.println("Current page is: " + this.currentPage);
    }

    public void showUrl(String url, String prefix) {
        this.currentPage = url;
        System.out.println(prefix + " page = " + url);
    }

    public static class LinkedListBasedStack {


//        public static void main(String[] args) {
//            LinkedListBasedStack stack = new LinkedListBasedStack();
//            stack.push("A");
//            stack.push("B");
//            stack.push("C");
//            stack.pop();
//            stack.push("D");
//            stack.push("E");
//            stack.pop();
//            stack.push("F");
//            stack.print();
//
//            String data = stack.getTopData();
//            System.out.println("Top data == " + data);
//        }

        private int size;
        private Node top;

        static Node createNode(String data, Node next) {
            return new Node(data, next);
        }

        public void clear() {
            this.top = null;
            this.size = 0;
        }

        public void push(String data) {
            this.top = createNode(data, this.top);
            this.size++;
        }

        public String pop() {
            Node popNode = this.top;
            if (popNode == null) {
                System.out.println("Stack is empty.");
                return null;
            }

            this.top = popNode.next;

            //TODO 为什么要加这个判断
            if (this.size > 0) {
                this.size--;
            }

            if (this.size <= 0) {
                System.out.println("this.size <= 0");
            }
            return popNode.data;
        }

        public String getTopData() {
            if (this.top == null) {
                return null;
            }
            return this.top.data;
        }

        public int size() {
            return this.size;
        }

        public void print() {
            System.out.println("Print stack:");
            Node currentNode = this.top;
            while (currentNode != null) {
                String data = currentNode.getData();
                System.out.print(data + "\t");
                currentNode = currentNode.next;
            }
            System.out.println();
        }


        public static class Node {
            private String data;
            private Node next;

            public Node(String data, Node next) {
                this.data = data;
                this.next = next;
            }

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }

            public Node getNext() {
                return next;
            }

            public void setNext(Node next) {
                this.next = next;
            }
        }
    }


}


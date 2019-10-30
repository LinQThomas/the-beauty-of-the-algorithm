package ac;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 描述本类的作用，需要注意的地方.
 *
 * @author <a href="mailto:your mail">Tu_ZhengSong(your mail)</a>
 */
public class Ac {

    private AcNode root = new AcNode('/');

    /**
     * 构建失败指针
     */
    public void buildFailurePointer() {
        Queue<AcNode> queue = new LinkedList<>();
        root.fail = null;
        queue.add(root);

        //队列不为空
        while (!queue.isEmpty()) {
            AcNode p = queue.remove();
            for (int i = 0; i < 26; i++) {
                AcNode pc = p.children[i];
                if (pc == null) {
                    continue;
                }
                if (p == root) {
                    pc.fail = root;
                } else {
                    AcNode q = p.fail;
                    while (q != null) {
                        AcNode qc = q.children[pc.data - 'a'];
                        if (qc != null) {
                            pc.fail = qc;
                            break;
                        }
                        q = q.fail;
                    }
                    if (q == null) {
                        pc.fail = root;
                    }
                }
                queue.add(pc);
            }
        }
    }


    public class AcNode {

        public char data;
        //字符集只包含a~z这26个字符
        public AcNode[] children = new AcNode[26];
        //结尾字符为true
        public boolean isEndingChar = false;
        //当isEndingChar=true时，记录模式串长度
        public int length = -1;
        //失败指针
        public AcNode fail;

        public AcNode(char data) {
            this.data = data;
        }
    }

    //text是主串
    public void match(char[] text) {
        int n = text.length;
        AcNode p = root;
        for (int i = 0; i < n; ++i) {
            int idx = text[i] - 'a';
            while (p.children[idx] == null && p != root) {
                //失败指针发挥作用的地方
                p = p.fail;
            }
            p = p.children[idx];
            //如果没有匹配的，从root开始匹配
            if (p == null) {
                p = root;
            }
            AcNode tmp = p;
            //打印出可以匹配的模式串
            while (tmp != root) {
                if (tmp.isEndingChar == true) {
                    int pos = i - text.length + 1;
                    System.out.println(" 匹配起始下标 " + "; 长度 " + tmp.length);
                }
                tmp = tmp.fail;
            }
        }
    }
}

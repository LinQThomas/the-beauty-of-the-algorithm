
package skiplist;

import java.util.Random;

/**
 * 跳表的一种实现方法。
 * 跳表中存储的是正整数，并且存储的是不重复的。
 *
 * @author <a href="mailto:your mail">Tu_ZhengSong(your mail)</a>
 */
public class SkipList {

    private static final int MAX_LEVEL = 16;

    private int levelCount = 1;

    //带头链表
    private Node head = new Node();

    private Random r = new Random();

    public Node find(int value) {
        Node p = head;

        //从最上层索引开始寻找，一直p不断接近value
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
        }

        //校验找到的p是否就是value
        if (p.forwards[0] != null && p.forwards[0].data == value) {
            return p.forwards[0];
        } else {
            return null;
        }
    }

    public void insert(int value) {
        int level = randomLevel();

        Node newNode = new Node();
        newNode.data = value;
        newNode.maxLevel = level;
        Node update[] = new Node[level];

        //准备更新节点
        for (int i = 0; i < level; i++) {
            update[i] = head;
        }

        Node p = head;

        //找到每一层的插入节点
        for (int i = level - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }

            update[i] = p;
        }

        for (int i = 0; i < level; ++i) {
            //update[i]记录的是对应level的前驱节点，newNode取代update[i]
            newNode.forwards[i] = update[i].forwards[i];
            //update[i]向后移动一位
            update[i].forwards[i] = newNode;

        }

        if (levelCount < level) {
            levelCount = level;
        }
    }


    public void delete(int value) {
        Node[] update = new Node[levelCount];
        Node p = head;

        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data <= value) {
                p = p.forwards[i];
            }

            update[i] = p;
        }

        //校验是否删除了最后一个节点
        //校验删除的是否刚好是被提取出来的跳表节点
        if (p.forwards[0] != null && p.forwards[0].data == value) {
            for (int i = levelCount - 1; i >= 0; --i) {
                if (update[i].forwards[i] != null && update[i].forwards[i].data == value) {
                    update[i].forwards[i] = update[i].forwards[i].forwards[i];
                }
            }
        }

    }


    // 随机 level 次，如果是奇数层数 +1，防止伪随机
    private int randomLevel() {
        int level = 1;

        for (int i = 0; i < MAX_LEVEL; ++i) {
            if (r.nextInt() % 2 == 1) {
                level++;
            }
        }

        return level;

    }

    public class Node {
        private int data = -1;
        private Node forwards[] = new Node[MAX_LEVEL];
        private int maxLevel = 0;

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("{ data: ");
            builder.append(data);
            builder.append("; levels: ");
            builder.append(maxLevel);
            builder.append(" }");

            return builder.toString();
        }

    }

}

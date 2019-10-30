package astar;

import java.util.LinkedList;

/**
 * 描述本类的作用，需要注意的地方.
 *
 * @author <a href="mailto:your mail">Tu_ZhengSong(your mail)</a>
 */
public class Graph {

    /**
     * 邻接图
     */
    private LinkedList<Edge> adj[];
    /**
     * 顶点个数
     */
    private int v;

    public Graph(int v) {
        this.v = v;
        this.adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            this.adj[i] = new LinkedList<>();
        }
    }

    private class Edge {
        /**
         * 边的起始顶点编号
         */
        public int sid;
        /**
         * 边的终止顶点编号
         */
        public int tid;
        /**
         * 权重
         */
        public int w;

        public Edge(int sid, int tid, int w) {
            this.sid = sid;
            this.tid = tid;
            this.w = w;
        }
    }

    //为了dijkstra实现用的
    public class Vertex {
        /**
         * 顶点编号ID
         */
        public int id;
        /**
         * 从起始顶点到这个顶点的距离
         */
        public int dist;

        /**
         * 新增：f(i)=g(i)+h(i)
         */
        public int f;

        /**
         * 新增：顶点在地图中的坐标
         */
        public int x, y;

        public Vertex(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.f = Integer.MAX_VALUE;
            this.dist = Integer.MAX_VALUE;
        }
    }

    private class PriorityQueue {
        /**
         * 根据vertex.dist构建小顶堆
         */
        private Vertex[] nodes;

        private int count;

        public PriorityQueue(int v) {
            this.count = v;
            this.nodes = new Vertex[v + 1];
        }

        public Vertex poll() {
            //TODO 待实现
            return null;
        }

        public void add(Vertex vertex) {
            //TODO 待实现
        }

        public void update(Vertex vertex) {
            //TODO  待实现
        }

        public boolean isEmpty() {
            //TODO 待实现
            return false;
        }

        public void clear() {
            //TODO 待实现
        }
    }

    /**
     * Graph类的成员变量，在构造函数中初始化
     */
    Vertex[] vertices = new Vertex[this.v];

    /**
     * 新增一个添加顶点坐标
     */
    public void addVetex(int id, int x, int y) {
        vertices[id] = new Vertex(id, x, y);
    }

    /**
     * 从顶点s到顶点t的路径
     *
     * @param s 起点
     * @param t 终点
     */
    public void astar(int s, int t) {
        //用来还原路径
        int[] predecessor = new int[this.v];
        //按照vertex的f值构建的小顶堆，而不是按照dist
        PriorityQueue queue = new PriorityQueue(this.v);
        boolean[] inqueue = new boolean[this.v];
        vertices[s].dist = 0;
        vertices[s].f = 0;
        queue.add(vertices[s]);
        inqueue[s] = true;
        while (!queue.isEmpty()) {
            //取出堆顶元素并删除
            Vertex minVertex = queue.poll();
            for (int i = 0; i < adj[minVertex.id].size(); i++) {
                //取出一条minVertex相连的边
                Edge e = adj[minVertex.id].get(i);
                //minVertex --> nextVertex
                Vertex nextVertex = vertices[e.tid];
                //更新next的dist，f
                if (minVertex.dist + e.w < nextVertex.dist) {
                    nextVertex.dist = minVertex.dist + e.w;
                    nextVertex.f = nextVertex.dist + hManhattan(nextVertex, vertices[t]);
                    predecessor[nextVertex.id] = minVertex.id;
                    if (inqueue[nextVertex.id] == true) {
                        queue.update(nextVertex);
                    } else {
                        queue.add(nextVertex);
                        inqueue[nextVertex.id] = true;
                    }
                }
                //只要到达t就可以结束while了
                if (nextVertex.id == t) {
                    //清空queue，才能退出while循环
                    queue.clear();
                    break;
                }
            }
        }

    }

    private int hManhattan(Vertex v1, Vertex v2) {
        return Math.abs(v1.x - v2.x) + Math.abs(v1.y - v2.y);
    }

}

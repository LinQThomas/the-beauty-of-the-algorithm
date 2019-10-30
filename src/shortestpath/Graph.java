package shortestpath;

import java.util.LinkedList;

/**
 * 有向有权图的领接表示
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

        public Vertex(int id, int dist) {
            this.id = id;
            this.dist = dist;
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
    }

    public void dijkstra(int s, int t) {
        int[] predecessor = new int[this.v];
        Vertex[] vertices = new Vertex[this.v];
        for (int i = 0; i < this.v; i++) {
            vertices[i] = new Vertex(i, Integer.MAX_VALUE);
        }
        //小顶堆
        PriorityQueue queue = new PriorityQueue(this.v);
        //标记是痘进入过队列
        boolean[] inqueue = new boolean[this.v];
        vertices[s].dist = 0;
        queue.add(vertices[s]);
        inqueue[s] = true;
        while (!queue.isEmpty()) {
            //取堆定元素并删除
            Vertex minVertex = queue.poll();
            //最短路基产生了
            if (minVertex.id == t) {
                break;
            }
            for (int i = 0; i < adj[minVertex.id].size(); ++i) {
                //取出一条minVertex相连的边
                Edge e = adj[minVertex.id].get(i);
                //minVertex --> nextVertex
                Vertex nextVertex = vertices[e.tid];
                //更新next的dist
                if (minVertex.dist + e.w < nextVertex.dist) {
                    nextVertex.dist = minVertex.dist + e.w;
                    predecessor[nextVertex.id] = minVertex.id;
                    if (inqueue[nextVertex.id] == true) {
                        //更新队列中的dist值
                        queue.update(nextVertex);
                    } else {
                        queue.add(nextVertex);
                        inqueue[nextVertex.id] = true;
                    }
                }
            }
        }

        //输出最短路径
        System.out.println(s);
        print(s, t, predecessor);
    }

    private void print(int s, int t, int[] predecessor) {
        if (s == t) {
            return;
        }
        print(s, predecessor[t], predecessor);
        System.out.println("->" + t);
    }


}

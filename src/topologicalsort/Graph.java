
package topologicalsort;

import java.util.LinkedList;

/**
 * 有向无环图
 *
 * @author <a href="mailto:your mail">Tu_ZhengSong(your mail)</a>
 */
public class Graph {

    //顶点的个数
    private int v;

    //邻接表
    private LinkedList<Integer> adj[];

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    /**
     * s先于t，边 s->t
     *
     * @param s
     * @param t
     */
    public void addEdge(int s, int t) {
        adj[s].add(t);
    }

    public void topoSortByKahn() {
        //统计每个顶点的入度
        int[] inDegree = new int[v];

        for (int i = 0; i < v; ++i) {
            for (int j = 0; j < adj[i].size(); ++j) {
                //i->w
                int w = adj[i].get(j);
                inDegree[w]++;
            }
        }

        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < v; ++i) {

            //有这个条件在，queue队列中可能只有一个值，记录起始点
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            //这里的i就是上面找到的起始点
            int i = queue.remove();
            System.out.println("->" + i);
            for (int j = 0; j < adj[i].size(); ++j) {
                int k = adj[i].get(j);
                inDegree[k]--;
                if (inDegree[k] == 0) {
                    queue.add(k);
                }
            }
        }
    }

    /**
     * 深度优先算法
     */
    public void topoSortByDFS() {
        //先构建逆邻接表，边s->t表示，s依赖于t,t先于s
        LinkedList<Integer> inverseAdj[] = new LinkedList[v];
        //申请空间
        for (int i = 0; i < v; ++i) {
            inverseAdj[i] = new LinkedList<>();
        }

        //通过邻接表生成逆邻接表
        for (int i = 0; i < v; ++i) {
            for (int j = 0; j < adj[i].size(); ++j) {
                //i -> w
                int w = adj[i].get(j);
                //w -> i
                inverseAdj[w].add(i);
            }
        }

        boolean[] visited = new boolean[v];

        //深度优先遍历图
        for (int i = 0; i < v; ++i) {
            if (visited[i] == false) {
                visited[i] = true;
                dfs(i, inverseAdj, visited);
            }
        }
    }

    private void dfs(int vertex, LinkedList<Integer>[] inverseAdj, boolean[] visited) {
        for (int i = 0; i < inverseAdj[vertex].size(); ++i) {
            int w = inverseAdj[vertex].get(i);
            if (visited[w] == true) {
                continue;
            }
            visited[w] = true;
            dfs(w, inverseAdj, visited);
        }
        //只要是无环的话，输出的应该是最远的点
        // 先把vertex这个顶点可达的所有顶点打印出来之后，再打印它自己
        System.out.println("->" + vertex);
        //这里输出的应该是正向的依赖关系，因为逆邻接表存储的是逆向的依赖关系
        //通过dfs，找到最远的依赖，作为输出起点
    }
}

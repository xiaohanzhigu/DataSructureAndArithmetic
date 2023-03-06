package com.ch.data_sructure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph {
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        String[] vertexes = {"A", "B", "C", "D", "E"};
        for (String vertex : vertexes) {
            graph.insertVertex(vertex);
        }
        //添加边
        //A - B A - C B - C B - D B - E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        System.out.println(graph);
        graph.dfs();
    }

    private List<String> vertexes;
    private int[][] edges;
    private int numberOfEdge;
    private boolean[] isVisited; //记录每个结点是否已经访问过了

    public Graph(int n) {
        vertexes = new ArrayList<String>();
        edges = new int[n][n];
    }



    /**
     * 访问初始结点v并标记结点v为已访问。
     * 结点v入队列
     * 当队列非空时，继续执行，否则算法结束。
     * 出队列，取得队头结点u。
     * 查找结点u的第一个邻接结点w。
     * 若结点u的邻接结点w不存在，则转到步骤3；否则循环执行以下三个步骤：
     *  6.1 若结点w尚未被访问，则访问结点w并标记为已访问。
     *  6.2 结点w入队列
     *  6.3 查找结点u的继w邻接结点后的下一个邻接结点w，转到步骤6。
     * @param i
     */
    public void bfs(int i) {
        System.out.println(vertexes.get(i) + "->");


    }

    /**
     * 深度优先搜索
     */
    public void dfs() {
        isVisited = new boolean[vertexes.size()];
        //为了避免遗漏度为0的顶点(与其他顶点无边相连)
        //对顶点集合中所有的顶点都进行一次深度搜索
        for (int i = 0; i < vertexes.size(); i++) {
            if (!isVisited[i]) {
                dfs(i);
            }
        }
        System.out.println();
    }

    private void dfs(int i) {
        //访问初始结点v，并标记结点v为已访问。
        System.out.print(vertexes.get(i) + "->");
        isVisited[i] = true;

        //往图下面不断的递归, 找出所有"未被访问过的", "与起始顶点相邻或其邻接顶点相邻"
        for (int j = 0; j < vertexes.size(); j++) {
            if (!isVisited[j] && edges[i][j] > 0) {
                dfs(j);
            }
        }
    }

    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    /**
     * @param v1     表示点的下标即使第几个顶点  "A"-"B" "A"->0 "B"->1
     * @param v2     第二个顶点对应的下标
     * @param weight 表示
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numberOfEdge++;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("   ");
        for (int i = 0; i < vertexes.size(); i++) {
            if (i < vertexes.size() - 1)
                builder.append(vertexes.get(i) + "  ");
            else
                builder.append(vertexes.get(i));
        }
        builder.append("\n");
        for (int i = 0; i < edges.length; i++) {

            builder.append(vertexes.get(i) + " " + Arrays.toString(edges[i]) + "\n");
        }
        return builder.toString();
    }

    public void insertVertex(String vertex) {
        vertexes.add(vertex);
    }
}

package com.ltlon.restapi.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: restapi
 * @description: 无向图
 * @author: LTL
 * @create: 2020-06-17 09:52
 */
public class Graph {
    //顶点的个数
    private int v;
    //邻接表=链表数组
    private LinkedList<Integer> adj[];

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    //无向图一条边存两次，可以理解为双向有向图
    public void addEdge(int s, int t) {
        adj[s].add(t);
        adj[t].add(s);
    }

    /**
     * @description 图的广度优先搜索 BFS 时间复杂度:O(E),空间复杂度O(v)
     * @author litlb
     * @return
     * @throws
     * @date 2020/6/17 14:51
    */
    public void bfs(int s, int t) {
        if (s == t) {
            return;
        }
        //记录每个顶点到起始顶点的距离:用来实现N度好友关系
        int[] distance = new int[v];
        distance[s] = 0;
        //记录已经被访问的顶点数组
        boolean[] visited = new boolean[v];
        visited[s] = true;
        //记录已经被访问，但相连的顶点还没有被访问的顶点
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        //记录搜索路径，prev[i]=value记录的是从value到i的访问记录
        int[] prev = new int[v];
        for (int i = 0; i < v; ++i) {
            //每个顶点的访问前驱顶点都初始化为-1
            prev[i] = -1;
        }

        while (queue.size() != 0){
            //拿出一个顶点
            int w = queue.poll();
            //遍历该顶点的所有相连的顶点
            for (int i = 0; i < adj[w].size(); i++) {
                int q = adj[w].get(i);
                if(!visited[q]){
                    prev[q] = w;
                    if(q != s){
                        distance[q]=distance[prev[q]]+1;
                    }
                    //是否等于终点，若等于，从头开始,递归遍历路径并打印，结束遍历
                    if(q == t){
                        print(prev,s,t);
                        return;
                    }
                    //如果不等于，则把这个顶点放到已经访问的数组里，并将新的未访问顶点放到列表里
                    visited[q] = true;
                    queue.add(q);
                }
            }
        }
    }
    //递归打印s->t的路径
    private void print(int[] prev,int s,int t){
        if(prev[t] != -1 && t != s){
            print(prev,s,prev[t]);
        }
        System.out.println(t +"->");
    }

    /**
     * @description 图的深度优先搜索 dfs 时间复杂度=O(E) 空间复杂度=O(V)
     * 打印顶点到顶点的路径
     * @author litlb
     * @param null
     * @return
     * @throws
     * @date 2020/6/17 14:52
    */
    //全局变量,表示是否遍历到达终点
    boolean found = false;

    public void dfs(int s ,int t){
        found = false;
        //初始化已经访问数组
        boolean[] visited = new boolean[v];
        //初始化前驱顶点数组
        int[] prev = new int[v];
        //初始化所有顶点的前驱顶点都是默认值-1
        for(int i = 0; i<prev.length;i++){
            prev[i] = -1;
        }
        //递归深度遍历
        recurDfs(s,t,visited,prev);
        //打印路径
        print(prev,s,t);
    }

    private void recurDfs(int s, int t,boolean[] visited,int[] prev){
        //边界检查
        if(found == true){
            return ;
        }
        visited[s] = true;
        //边界检查
        if(s == t){
            found = true;
            return;
        }
        for (int i = 0; i < adj[s].size(); i++) {
            int q = adj[s].get(i);
            if(!visited[q]){
                prev[q] = s;
                recurDfs(q,t,visited,prev);
            }
        }
    }

}

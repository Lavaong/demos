package com.ltlon.restapi.leetcode;

import java.util.LinkedList;

/**
 * @program: restapi
 * @description: 有向无环图
 * @author: LTL
 * @create: 2020-07-08 10:23
 */
public class WayGraph {

    private int v;
    private LinkedList<Integer>[] items;

    public WayGraph(){
        this.v  = v;
        items = new LinkedList[v];
        for(int i = 0 ; i < v ; i++){
            items[i] = new LinkedList<>();
        }
    }

    //s -> t ,s 先于 t
    public void addEdge(int s, int t){
        items[s].add(t);
    }

    //使用Kahn算法实现拓扑排序
    //如果s需要先于t执行，那就添加一条s指向t的边。
    //所以，如果某个顶点入度为0， 也就表示，没有任何顶点必须先于这个顶点执行，那么这个顶点就可以执行了
    public void topoSortByKhan(){
        //统计每个顶点的入度
        int[] inDegree = new int[v];
        //填充每个顶点的入度值
        for(int i = 0 ; i < v ; i++){
            for(int j = 0 ; j<items[i].size();++j){
                int w =  items[i].get(j);
                inDegree[w]++;
            }
        }
        //找出入度为0的顶点,并放入队列中
        LinkedList<Integer> queue = new LinkedList<>();
        for(int i = 0;i<v ;++i){
            if(inDegree[i] == 0){
                queue.add(i);
            }
        }
        //输出队列中入度为0的顶点(删除)，找到依赖或者被指向这些入度为0的顶点的顶点，把他们的入度减去1
        //减去1后，然后再看这些顶点的入度是否为0,放入队列中，循环这个过程，直到所有顶点的入度为0.
        while(!queue.isEmpty()){
            int i = queue.remove();
            System.out.println("->"+i);
            for(int j = 0 ; j < items[i].size();++j){
                int k = items[i].get(j);
                inDegree[k]--;
                if(inDegree[k] == 0){
                    queue.add(k);
                }
            }
        }
    }

    //DFS算法,实现拓扑排序
    public void topoSortByDFS(){
        //先构建逆临接表，邻接表s->t表示,s先于t,t要依赖s
        //而逆临接表s->t表示 t先于s,s要依赖t.
        LinkedList<Integer> inverseAdj[] = new LinkedList[v];
        for(int i = 0;i<v;++i){
            inverseAdj[i] = new LinkedList<>();
        }
        //通过邻接表生成逆邻接表
        for(int i = 0; i< v;++i){
            for(int j =0;j<items[i].size();++j){
                //邻接表中的指向关系 i -> w
                int w = items[i].get(j);
                //逆邻接表中的指向关系 i <- w
                inverseAdj[w].add(i);
            }
        }
        boolean[] visited = new boolean[v];
        //深度优先遍历图
        for(int i = 0 ;i<v;++i){
            if(!visited[i]){
                visited[i] = true;
                dfs(i,inverseAdj,visited);
            }
        }
    }
    //深度递归打印顶点,完成拓扑排序
    private void dfs(int vertex,LinkedList<Integer> inverseAdj[],boolean[] visited){
        for(int i = 0;i<inverseAdj[vertex].size();i++){
            int w = inverseAdj[vertex].get(i);
            visited[w] = true;
            dfs(w,inverseAdj,visited);
        }
        System.out.println("->"+vertex);
    }
}

package com.ltlon.restapi.leetcode;

/**
 * @program: restapi
 * @description: 0_1背包问题,
 * @author: LTL
 * @create: 2020-06-30 11:05
 */
public class Knapsack0_1 {


    public static void main(String[] args) {
        int[] weight = {2, 2, 4, 6, 3};
        int n = 5;
        int w = 9;
        //System.out.println(knapsack3(weight, n, w));
        System.out.println(System.currentTimeMillis());
    }

    //存储背包中物品总重量的最大值
    public int maxW = Integer.MAX_VALUE;
    //w背包重量,items表示每个物品的重量，n表示物品个数
    //假设背包可承受重量100，物品总的个数为10个,每个物品重量存储在数组a中,f(0,0,a,10,100)

    /**
     * @param i     将要放入的第i个物品
     * @param cw    当前背包的总重量
     * @param items 物品存放的重量数组=背包？
     * @param n     物品总数量
     * @param w     背包最大可承受重量
     * @return void
     * @throws
     * @description 回溯算法解决
     * @author litlb
     * @date 2020/6/30 15:33
     */
    public void in(int i, int cw, int[] items, int n, int w) {
        //cw = w 表示已经装满了,i==n表示已经考察完所有物品了
        if (cw == w || i == n) {
            if (cw > maxW) {
                maxW = cw;
            }
            return;
        }
        //假设有n个物品，则放置背包可以分为n个阶段，每个阶段有两种情况，放入当前物品和不放入当前物品的情况，枚举这两种情况，并递归的处理剩下的物品。
        //不放入当前物品的情况，直接选择下一个物品
        in(i + 1, cw, items, n, w);
        //放入当前物品的情况，若更新后的cw不超过背包可以承受的重量时
        if (cw + items[i] <= w) {
            //选择了当前物品，cw更新为cw+items[i]
            in(i + 1, cw + items[i], items, n, w);
        }
    }

    /**
     * @param wight 物品的重量数组
     * @param n     物品的总个数
     * @param w     背包最大承重
     * @return int  输出最后决策完成后，所有可能状态中最大的背包重量。
     * @throws
     * @description 动态规划解决 ，使用二维状态数组
     * @author litlb
     * @date 2020/7/1 11:07
     */
    public int knapsack2(int[] wight, int n, int w) {
        //状态数组，第一维度表示是第i件物品，从下标0开始，第二维度是当前背包的重量，从下标0开始，有w+1种可能(包括0)
        boolean[][] states = new boolean[n][w + 1];
        //初始状态初始化，第0件物品，有两种状态，这件物品放或者不放。
        states[0][0] = true;
        states[0][wight[0]] = true;
        //然后从1件物品开始遍历
        for (int i = 1; i < n; ++i) {
            //不把第i个物品放入背包的情况,则上个物品的背包重量等于当前物品的背包重量
            for (int j = 0; j <= w; ++j) {
                //基于上个物品的放置情况确定这个物品未放置的状态
                if (states[i - 1][j] == true) {
                    states[i][j] = states[i - 1][j];
                }
            }
            //把第i个物品放入背包的情况
            for (int j = 0; j <= w - wight[i]; ++j) {
                //基于上个物品的放置情况确定这个物品的放置状态
                if (states[i - 1][j] == true) {
                    states[i][j + wight[i]] = true;
                }
            }
        }
        for (int i = w; i >= 0; i--) {
            if (states[n - 1][i] == true) {
                return i;
            }
        }
        return 0;
    }

    /**
     * @param wight 存放物品重量的数组
     * @param n     物品总个数
     * @param w     背包最大承重
     * @return int 输出最后决策完成后，所有可能状态中最大的背包重量
     * @throws
     * @description 0_1背包问题，动态规划，使用一维状态数组，减少空间复杂度O(w+1);
     * @author litlb
     * @date 2020/7/1 15:55
     */

    public static int knapsack3(int wight[], int n, int w) {
        //一维状态数组,下标索引表示背包当前的重量(状态),
        boolean[] status = new boolean[w + 1];
        //初始化初始状态,第一件物品放还是不放是两种可能存在的状态
        status[0] = true;
        status[wight[0]] = true;
        //从第二件物品开始推导
        for (int i = 1; i < n; ++i) {
            //将第i个物品放入背包
            for (int j = 0; j <= w - wight[i]; ++j) {
                //物品放入背包的情况,或者不放入背包的状态都会在上一次的决策完成后导致下标索引为true表示出来
                //故每次的放入后的重量状态都是依据上一次的放入状态决定的
                if (status[j] == true) {
                    status[j + wight[i]] = true;
                }
            }
        }
        //输出结果
        for (int i = w; i >= 0; i--) {
            if (status[i] == true) {
                return i;
            }
        }
        return 0;
    }

    /**
     * @param wight 所有物品的重量数组
     * @param value 所有物品的价值数组
     * @param n     物品总个数
     * @param w     背包最大限制重量
     * @return int
     * @throws
     * @description 动态规划解决0_1背包问题，每个物品附带价值属性，在满足不大于背包总重量的前提下，找出最大价值
     * 分为n个阶段，每个阶段会决策一个物品是否放到背包中，每个阶段决策完成后，背包中的物品的价值和重量，会有多种不同的情况，
     * 使用一个二维数组status[n][w+1],来记录每层可以达到的不同状态，不过这里数组的值存储的不是boolean类型，而是这个状态下的最大总价值
     * ，且把每一层相同的状态节点合并，既[i][cw]相同的节点，只留下cv(价值)最大的那个节点，然后基于这些状态来进行下一层的推导
     * @author litlb
     * @date 2020/7/1 17:06
     */
    public static int knapsack4(int[] wight, int[] value, int n, int w) {
        int[][] states = new int[n][w + 1];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < w + 1; ++j) {
                states[i][j] = -1;
            }
        }
        states[0][0] = 0;
        states[0][wight[0]] = value[0];
        //动态规划，状态转移
        for (int i = 1; i < n; ++i) {
            //不选择第i个物品
            for (int j = 0; j <= w; ++j) {
                //价值大于0,初始价值为0
                if (states[i - 1][j] >= 0) {
                    states[i][j] = states[i - 1][j];
                }
            }
            //选择第i个物品
            for (int j = 0; j <= w - wight[i]; ++j) {
                if (states[i - 1][j] >= 0) {
                    //当上一层和这一层的物品质量相等的时候，很可能这一层有两个背包相同质量的价值状态，我们需要去掉一个，只选用价值最大的那个。
                    //v代表假如上一层没选择物品的情况下的背包的总价值加上选择这一层后的物品后的背包总价值
                    int v = states[i - 1][j] + value[i];
                    //x代表假如上一层选择物品的情况下加上选择这一层物品的情况下的背包总价值,x的值由上一步不选择第i个物品得来。
                    int x = states[i][j + wight[i]];
                    //v和x这两个的状态数组的重量可能相等,需要选择其中价值最大的哪一个,来进行下一步的推导(剪枝)
                    if (v > x) {
                        states[i][j + wight[i]] = v;
                    }
                }
            }
        }
        int maxValue = -1;
        for (int j = 0; j <= w; ++j) {
            if (states[n - 1][j] > maxValue) {
                maxValue = states[n - 1][j];
            }
        }
        return maxValue;
    }

    /**
     * @param items
     * @param n
     * @param w
     * @return void
     * @throws
     * @description 双十一满200 - 50 ,购物车中有n件物品，w表示200,故薅羊毛的限制条件应该是
     * 200*3 = 600 ，优惠 150 , （600-150）/600 = 75折
     * @author litlb
     * @date 2020/7/2 10:53
     */
    public static void double11advance(int[] items, int n, int w) {
        //600 150
        boolean[][] states = new boolean[n][3 * w + 1];
        states[0][0] = true;
        states[0][items[0]] = true;
        for (int i = 1; i < n; ++i) {
            //不买第i个物品的情况
            for (int j = 0; j < 3 * w; ++j) {
                if (states[i - 1][j] == true) {
                    states[i][j] = true;
                }
            }
            //购买第i个物品的情况
            for (int j = 0; j < 3 * w - items[i]; j++) {
                if (states[i - 1][j] == true) {
                    states[i][j + items[i]] = true;
                }
            }
        }
        int j;
        //假如预先规划使用200元，超过600 元的价格则就没必要购物了，故需要找出最省钱的,既输出结果大于等于w的最小值
        for (j = w; j < 3 * w + 1; ++j) {
            if (states[n - 1][j] == true) {
                break;
            }
        }
        //无解，没有合适的
        if (j == -1) {
            return;
        }
        ;
        //states[i][j] 的状态只能由上一层的states[i-1][j-items[i]](买)状态或者states[i-1][j](不买)状态推导过来
        for (int i = n - 1; i >= 1; --i) {
            //买的情况
            if (j - items[i] >= 0 && states[i - 1][j - items[i]] == true) {
                System.out.println(items + " ");
                j = j - items[i];
            }
        }
        if (j != 0) {
            System.out.println(items[0]);
        }
    }

    //杨辉三角最短路径问题，动态规划解决,假设节点都存储在一个整形数组中,每条路径都由上一个节点
    //决定，

    /**
     * @param items  改造后的杨辉数组,每个位置的数字可以随便写
     * @param x 杨辉数组的行数=高度
     * @param y 杨辉数组的列数
     * @return int
     * @throws
     * @description
     * @author litlb
     * @date 2020/7/2 13:35
     */
    private int[][] yhTriangle = {{1}, {2, 3}, {4, 5, 6}, {7, 8, 9, 10}};

    public int caculateYH(int[][] yhTriangle, int x) {
        //状态数组，值记录路径经过的长度
        int[][] states = new int[x][yhTriangle[x - 1].length];
        //初始化第一个节点的状态,根据表可知每一行的节点的状态由上一行的左上，和上方决定
        //选则较小的路径
        //初始化最左边的一列和对角线一列
        states[0][0] = yhTriangle[0][0];
        for (int i = 1; i < x; i++) {
            states[i][0] = yhTriangle[i - 1][0];
            states[i][yhTriangle[i].length - 1] = yhTriangle[i - 1][yhTriangle[i].length - 1];
        }
        //根据状态转移方程写出每个状态
        for (int i = 2; i < x; ++i) {
            for (int j = 1; j < yhTriangle[i].length - 1; ++j) {
                states[i][j] = yhTriangle[i][j] + Math.min(states[i - 1][j - 1], states[i - 1][j]);
            }
        }
        int min = Integer.MIN_VALUE;
        for (int j = 0; j < states[x - 1].length; ++j) {
            if (states[x - 1][j] < min) {
                min = states[x - 1][j];
            }
        }
        return min;
    }


    /**
     * @param matrix
     * @param n
     * @return int
     * @throws
     * @description 状态转移表法求矩阵的起点到终点的最短路径问题
     * @author litlb
     * @date 2020/7/6 10:22
     */

    public int minDistDP(int[][] matrix, int n) {
        int[][] states = new int[n][n];
        int sum = 0;
        //初始化第一行数据
        for (int j = 0; j < n; ++j) {
            states[0][j] = sum;
            sum = sum + matrix[0][j];
        }
        sum = 0;
        //初始化第一列数据
        for (int i = 0; i < n; ++i) {
            states[i][0] = sum;
            sum = sum + matrix[i][0];
        }

        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < n; ++j) {
                states[i][i] = Math.min(states[i - 1][j], states[i][j - 1]);
            }
        }
        return states[n - 1][n - 1];
    }

    public int minCoin(int x, int y, int z, int money) {
        boolean[][] states = new boolean[money][money + 1];
        //初始化第1行的数据
        states[0][1] = true;
        states[0][3] = true;
        states[0][5] = true;
        //根据第一行的数据推导后面的数据
        for (int i = 1; i < money; ++i) {
            for (int j = 1; j <= money; ++j) {
                if (states[i - 1][j] == true){
                    if(j+x <= money){
                        states[i][j+x] = true;
                    }
                    if(j+y <= money){
                        states[i][j+y] = true;
                    }
                    if(j+z <= money){
                        states[i][j+z] = true;
                    }
                }
            }
        }
        for(int i = 0; i < money;++i){
            if(states[i][money] = true){
                return i+1;
            }
        }
        return 0;
    }


}

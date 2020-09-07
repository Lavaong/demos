package com.ltlon.restapi.leetcode;

/**
 * @program: restapi
 * @description: 八皇后问题，回溯算法的经典实现,92种结果
 * @create: 2020-06-29 17:53
 */
public class EightQueen {

    static int count = 0;

    public static void main(String[] args) {
        new EightQueen().cal8Queens(0);
        System.out.println(count);
    }
    //8个皇后
    int[] result = new int[8];
    //默认row是0开始,第一行开始;
    public void cal8Queens(int row) {
        //8个棋子都放置好了，打印所有棋子的位置结果
        if (row == 8) {
            printQueens(result);
            ++count;
            return;
        }
        //枚举出8列，挑选合适的
        for (int column = 0; column < 8; ++column) {
            if (isOK(row, column)) {
                //将这一行的列位置放入数组中
                result[row] = column;
                //计算下一行的皇后位置
                cal8Queens(row + 1);

            }
        }
    }

    //判断row行column列放置是否合适,因为从上往下放置元素，故不用考察下方是否有元素
    //又因为每一行只有一个元素,故不用考察同行是否有元素,只需要考察上右和上左多角线是否有元素
    //和上方是否有元素即可、
    private boolean isOK(int row, int column) {
        //左对角线列坐标，右对角线列坐标
        int leftup = column - 1, rightup = column + 1;
        //逐行往上考察每一行是否有元素
        for (int i = row - 1; i >= 0; --i) {
            //上方是否有元素
            if (result[i] == column) {
                return false;
            }
            //左上对角线
            if (leftup >= 0) {
                if (result[i] == leftup) {
                    return false;
                }
            }
            //右上对角线
            if (rightup < 8) {
                if (result[i] == rightup) {
                    return false;
                }
            }
            leftup--;
            rightup++;
        }
        return true;
    }

    //打印出一个二维矩阵
    public void printQueens(int[] result) {
        for (int i = 0; i < 8; ++i) {
            for (int column = 0; column < 8; ++column) {
                if (result[i] == column) {
                    //*号代表皇后
                    System.out.print(" * ");
                } else {
                    //下划线代表没有任何东西
                    System.out.print(" _ ");
                }
            }
            System.out.println();
        }
    }
}

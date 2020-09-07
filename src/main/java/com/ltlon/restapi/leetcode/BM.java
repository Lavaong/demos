package com.ltlon.restapi.leetcode;

import javax.validation.constraints.Max;

/**
 * @program: restapi
 * @description: BM算法
 * @author: LTL
 * @create: 2020-06-18 10:35
 */
public class BM {

    private static final int SIZE = 256;

    /**
     * @param b  模式串
     * @param m  模式串的长度
     * @param bc 数组型散列表(下标是模式串的字符的ASCII码值，value是模式串字符的下标)
     * @return void
     * @throws
     * @description 通过该散列表去寻找模式串中某个字符的下标
     * @author litlb
     * @date 2020/6/18 13:51
     */
    private void generateBC(char[] b, int m, int[] bc) {
        for (int i = 0; i < SIZE; ++i) {
            bc[i] = -1;
        }
        for (int i = 0; i < m; i++) {
            int ascill = (int) b[i];
            bc[ascill] = i;
        }
    }

    /**
     * @param a 主串
     * @param n 主串的长度
     * @param b 模式串
     * @param m 模式串的长度
     * @return int
     * @throws
     * @description BM算法:分别计算坏字符和好后缀的移动的步数，选择移动步数较大的那个
     * 好后缀同时也是后缀子串
     * @author litlb
     * @date 2020/6/18 14:00
     */
    public int bm(char[] a, int n, char[] b, int m) {
        //记录模式串中每个字符出现的最后的位置
        int[] bc = new int[SIZE];
        //构建坏字符hash表
        generateBC(b, m, bc);
        //key:公共子串长度，value:公共子串(前面那个的)起始下标值
        int[] suffix = new int[m];
        //key:公共子串长度，value:该长度的公共子串是否也是前缀子串。
        boolean[] prefix = new boolean[m];
        generateGS(b, m, suffix, prefix);
        //i表示主串与模式串对齐的第一个字符
        int i = 0;
        while (i <= n - m) {
            int j;
            //模式串从后往前匹配
            for (j = m - 1; j >= 0; --j) {
                if (a[i + j] != b[j]) {
                    //得到坏字符串的下标识j
                    break;
                }
            }
            //匹配成功,返回主串与模式串匹配的第一个字符
            if (j < 0) {
                return i;
            }
            //坏字符移动步数
            int x = j - bc[(int)a[i+j]];
            //好后缀移动步数
            int y = 0;
            //如果有好后缀的话，既坏字符不等于最后一个元素
            if(j < m-1){
                y = moveByGS(j,m,suffix,prefix);
            }
            //模式串往后滑动j-bc[(int)a[i+j]]位
            i = i + Math.max(x,y);
        }
        return -1;
    }

    // b表示模式串，m表示⻓度，suffix，prefix数组事先申请好了
    private void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
        for (int i = 0; i < m; ++i) { // 初始化
            suffix[i] = -1;
            prefix[i] = false;
        }
        //b[0,i]和b[0,m-1]的公共后缀子串,同时填充suffix数组和prefix数组的值
        for (int i = 0; i <= m - 2; ++i) { // b[0, i]
            int j = i;
            int k = 0; // 公共后缀⼦串⻓度
            while (j >= 0 && b[j] == b[m - 1 - k]) { // b[0,i]与b[0, m-1]求公共后缀⼦串
                --j;
                ++k;
                suffix[k] = j + 1; //j+1表示公共后缀⼦串在b[0, i]中的起始下标
            }
            if (j == -1) prefix[k] = true; //如果公共后缀⼦串也是模式串的前缀⼦串
        }
    }

    /**
     * @description 移动长度,好后缀
     * @author litlb
     * @param j
 * @param m
 * @param suffix
 * @param prefix
     * @return int
     * @throws
     * @date 2020/6/22 15:59
    */
    private int moveByGS(int j ,int m,int[] suffix,boolean[] prefix){
        //好后缀长度
        int k = m-1-j;
        //存在公共后缀子串
        if(suffix[k] != -1){
            //返回移动长度：坏字符下标减去公共后缀子串的起始下标+1
            return j-suffix[k] + 1;
        }
        for(int r = j+2;r<=m-1;r++){
            if(prefix[m-r] == true){
                return r;
            }
        }
        return m;
    }
}

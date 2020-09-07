package com.ltlon.restapi.leetcode;

/**
 * @program: restapi
 * @description: KMP算法
 * @author: LTL
 * @create: 2020-06-23 10:49
 */
public class KMP {

    public static int KMP(char[] a, int n, char[] b, int m) {
        int[] next = getNexts(b, m);
        int j = 0;
        for (int i = 0; i < n; i++) {
            //匹配到坏字符,计算可以移动的最大步数
            while(j>0 && a[i] != b[j]){
                //计算移动步数
                j = next[j-1] + 1;
            }
            //匹配到好前缀,i++,j++
            if(a[i] == b[j]){
                j++;
            }
            //找到匹配模式串了
            if(j == m){
                return i-m+1;
            }
        }
        return -1;
    }
    
    /** 
     * @description next数组的key为模式串所有前缀子串结尾字符下标，value为最长可匹配前缀结尾字符下标
     * @author litlb      
     * @param b 模式串
     * @param m 模式串长度
     * @return int
     * @throws        
     * @date 2020/6/23 18:04
    */

    private static int[] getNexts(char[] b , int m){
        int[] next = new  int[m];
        //首字符默认为 -1
        next[0] = -1;
        //模式串中每个前缀子串最长或者次长可匹配前缀结尾字符下标
        int x = -1;
        for(int i = 1; i< m;i++){
            //next[i-1]=x 且b[x+1]==b[i] ==> next[i] = x+1
            //当b[0,i-1]的最长前缀可匹配子串结束下标x的下一个字符不等于b[i],
            //迭代求b[0,i-1]的次长可匹配前缀子串结束下标，因为次长的被包含在最长前缀的子串中，故只需求最长的
            //前缀子串中的最长前缀子串下标即可得到次长前缀子串下标。
            while (x != -1 && b[x+1] != b[i]){
                x = next[x];
            }
            //当b[0,i-1]次长前缀字符串结束下标的下一个字符b[++x]等于b[i],则next[i]等于++x.
            if(b[x+1] == b[i]){
                ++x;
            }
            next[i] = x;
        }
        return next;
    }
}

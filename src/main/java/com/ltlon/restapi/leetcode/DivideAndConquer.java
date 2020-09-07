package com.ltlon.restapi.leetcode;

/**
 * @program: restapi
 * @description: 分治思想
 * @author: LTL
 * @create: 2020-06-29 14:35
 */
public class DivideAndConquer {

    public static void main(String[] args) {
        System.out.println(5.0 / 2.0);
    }

    //使用分治算法求解一组数据的有序对个数或者逆序对个数
    //EX:[1,5,6,2,3,4]
    private int num = 0;

    public int countNum(int[] a, int n) {
        num = 0;
        mergeSortCounting(a, 0, n - 1);
        return num;
    }

    //分而治之
    private void mergeSortCounting(int[] a, int start, int end) {
        if (start >= end) {
            return;
        }
        int q = (start + end) / 2;
        mergeSortCounting(a, start, q);
        mergeSortCounting(a, q + 1, end);
        //归并排序（使用归并算法变形）
        merge(a, start, q, end);
    }

    private void merge(int[] a, int start, int q, int end) {
        //三个指针变量，归并比较使用
        int i = start, j = q + 1, k = 0;
        //临时数组
        int[] tmp = new int[end - start + 1];
        while (i <= q && j <= end) {
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++];
            } else {
                //统计start到q之间,比a[j]大的元素的个数,因为start到q区间的元素已经是有序的
                num += (q - i + 1);
                tmp[k++] = a[j++];
            }
        }
        //处理剩下的
        while (i <= q) {
            tmp[k++] = a[i++];
        }
        //处理剩下的
        while (j <= end) {
            tmp[k++] = a[j++];
        }
        //拷贝到原来的数组
        for (i = 0; i <= end - start; ++i) {
            a[start + i] = tmp[i];
        }
    }

    //有两个n*n的矩阵A，B，如何快速求解两个矩阵的乘积C=A*B？
    //矩阵乘法，行*列然后相加

    private int matrixTotal = 0;

    public int caculateMatrix(int[][] a, int[][] b) {
        int m = a[0].length;
        int n = a.length;
        caculate(a, b, m, n);
        return matrixTotal;
    }

    //矩阵a竖向切割,矩阵b横向切割
    private void caculate(int[][] a, int[][] b, int start,int end) {
        if(start >= end){
            return;
        }
        //分
        int par = (start+end)/2;
        caculate(a,b,start,par);
        caculate(a,b,par,end);
        //合
        mergeMatrix(a,b,start,par,end);
    }

    private void mergeMatrix(int[][] a ,int[][] b ,int start ,int par ,int end){

    }

   // 二维平面上有n个点，如何快速计算出两个距离最近的点对？
}

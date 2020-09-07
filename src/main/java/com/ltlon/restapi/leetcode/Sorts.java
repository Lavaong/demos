package com.ltlon.restapi.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: restapi
 * @description:
 * @author: LTL
 * @create: 2020-05-09 15:45
 **/
public class Sorts {

    //冒泡排序，稳定，O(n), O(n^2),O(n^2)
    public void bubble(int[] arrs,int n){
        if(n <= 1){
            return ;
        }
        for(int i = 0 ; i < n;++i){
            int j = 0;
            boolean flag = false;
            for(;j<n-i-1;++j){
                if(arrs[j] > arrs[j+1]){
                    int temp = arrs[j];
                    arrs[j] = arrs[j+1];
                    arrs[j+1] = temp;
                    flag = true;
                }
            }
            if(!flag){
                break;
            }
        }
    }

    //插入排序 稳定 复杂度O(n),O(n^2),O(n^2)
    public void insert(int[] arrs, int n){
        for (int i = 1; i < n; i++) {
            int value = arrs[i];
            int j = i-1;
            //查找插入的位置
            for(;j>=0;--j){
                if(value < arrs[j]) {
                    arrs[j+1] = arrs[j];
                }else{
                    break;
                }
            }
            //插入数据
            arrs[j+1] = value;
        }
    }
    //选择排序 不稳定，复杂度O(n^2),O(n^2),O(n^2)
    public void select(int[] arrs,int n){

        for(int i=0;i<n;++i){
            int minIndex = i;
            for(int j = i+1;j<n;j++){
                if(arrs[j] < arrs[minIndex]){
                    minIndex = j;
                }
            }
            //交换
            int temp = arrs[i];
            arrs[i] = arrs[minIndex];
            arrs[minIndex] = temp;
        }
    }
    //快速排序，O(nlogn),极端O(n^2); 不稳定,原地排序
    public void quickSort(int[] arrs,int n){
        if(n<=1){
            return;
        }
        quickSort(arrs,0,n-1);
    }

    public void quickSort(int[] arrs,int left,int right){
        if(left >= right){
            return;
        }
        //获取分区点下标
        int pviot = partition(arrs,left,right);
        quickSort(arrs,left,pviot -1);
        quickSort(arrs,pviot+1,right);
    }
    //原地分区函数
    public int partition(int[] arrs,int left,int right){
        //确定中间值
        int pviot = arrs[right];
        int i = left;
        int j = left;
        for (; j < right; j++) {
            //将小于pivot的交换放到最左边
            if(arrs[j] < pviot){
                int temp = arrs[i];
                arrs[i] = arrs[j];
                arrs[j] = temp;
                i++;
            }
        }
        //将最右边的pviot中间值放到中间对应的位置上。
        int temp = arrs[right];
        arrs[right] = arrs[i];
        arrs[i] = temp;
        return i;
    }
    //桶（链表+数组）排序 O(n),数组中的元素必须是非负整数,n 代表数组长度,m 表示桶的个数，适合外部排序
    public  void barrelSort(int[] arrs , int n ,int m){
        if(n<=1){
            return ;
        }
        //查找数组中数据的范围，最大是max,最小是0.
        int max = 0;
        for(int i =0;i<n;++i){
            if(arrs[i] > max){
                max = arrs[i];
            }
        }
        int unit = max/m;
        List<ArrayList<Integer>> arrList = new ArrayList(m);
        for(int i = 0 ; i < max;){
            ArrayList<Integer> temp = new ArrayList<Integer>(unit);
            arrList.add(temp);
            i = i + unit;
        }
        //将源数组的元素放入对应桶中
        for(int i =0;i<n;i++){
            arrList.get(arrs[i]/unit).add(arrs[i]);
        }

        //将所有桶中的元素依次拿出放入一个大的数组中，排序完毕
        //copyBarrels();
    }

    //计数排序 O(n),数组中的元素必须是非负整数，N代表数组长度。计数排序是特殊的桶排序，在于桶的个数等于数据的大小范围，
    //比如排序数据的范围在0-100，则计数的桶的个数则为101；
    public void  countSort(int[] arrs,int n ){
        if(n<=1){
            return ;
        }
        //查找数组中数据的范围,最大是max,最小是0,因为里面的元素都是非负整数;必定小于数据的个数n
        int max = 0;
        for (int i = 0; i < n; i++) {
            if(max < arrs[i]){
                max = arrs[i];
            }
        }
        //申请一个计数数组cArr，下标大小0-max;
        int[] cArr = new int[max+1];

        //计算源中每个元素的个数，放入计数数组
        for(int i = 0; i<n;i++){
            cArr[arrs[i]]++;
        }
        //依次累加，从cArr[0]开始累加
        for(int i=1 ;i<=max;++i){
            cArr[i] = cArr[i-1] + cArr[i];
        }

        //最终结果数组，存储排序之后的结果
        int[] result = new int[n];
        //从后往前遍历源数组,并将源数组中的元素正确放置到结果数组的下标上。
        for(int i = n-1;i>=0;--i){
            int index = cArr[arrs[i]] - 1;
            result[index] = arrs[i];
            cArr[arrs[i]]--;
        }
        //将结果数组中的元素一个一个赋值给源数组，覆盖源数组。
        //arrCopy();
    }
    //二分查找
    public int bsearch(int[] arrs,int n , int value){
        return bSerachInternally(arrs, 0 , n-1,value);
    }
    public int bSerachInternally(int[] arrs, int left, int right,int target){
        if(left > right){
            return -1;
        }
        int mid = left +(right-left)/2;
        if(arrs[mid]  == target){
            return mid;
        }
        if(arrs[mid] > target){
            right = mid -1;
        }else{
            left = mid+1;
        }
        return bSerachInternally(arrs,left,right,target);
    }

    //二分查找变形：查找第一个值等于给定值的元素
    public int bsearchFirstEqual(int[] arrs,int n , int value){
        return bSerachInternally(arrs, 0 , n-1,value);
    }
    public int bSerachInternallyFE(int[] arrs, int left, int right,int target){
        if(left > right){
            return -1;
        }
        int mid = left +(right-left)/2;
        if(arrs[mid]  == target){
            while(mid >= 0 && mid == target){
                mid--;
            }
            return mid+1;
        }
        if(arrs[mid] > target){
            right = mid -1;
        }else{
            left = mid+1;
        }
        return bSerachInternally(arrs,left,right,target);
    }
    //二分查找变形：查找最后一个值等于给定值的元素
    public int bsearchLastEqual(int[] arrs,int n , int value){
        int l = 0;
        int r = n-1;
        while(l<=r){
            int mid = l+((r-l)>>1);
            if(arrs[mid] > value){
                r = mid - 1;
            }else if (arrs[mid] < value){
                l = mid+1;
            }else{
                if(mid == n-1 || arrs[mid+1]!=value){
                    return mid;
                }else{ l = mid+1;}
            }
        }
        return -1;
    }
    //二分查找变形：查找第一个大于等于给定值的元素
    public int bsearchFirstEqualAndGrater(int[] arrs,int n , int value){
        int l = 0;
        int r = n-1;
        while(l<=r){
            int mid = l+((r-l)>>1);
            if(arrs[mid] >= value){
                if(mid == 0 || arrs[mid-1] < value){
                    return mid;
                }else{
                    r = mid - 1;
                }
            }else{
                l = mid+1;
            }
        }
        return -1;
    }
    //二分查找变形：查找最后一个小于等于给定值的元素
    public int bsearchLastEqualAndLess(int[] arrs,int n , int value){
        int l = 0;
        int r = n-1;
        while(l<=r){
            int mid = l+((r-l)>>1);
            if(arrs[mid] <= value){
                if(mid == n-1 || arrs[mid+1] > value){
                    return mid;
                }else{
                    l = mid + 1;
                }
            }else{
                r = mid-1;
            }
        }
        return -1;
    }
}

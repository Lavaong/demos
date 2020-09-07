package com.ltlon.restapi.leetcode;

import lombok.Data;

/**
 * @program: restapi
 * @description: 顺序栈,用数组实现的栈结构
 * @author: LTL
 * @create: 2020-05-06 15:55
 **/
@Data
public class ArrayStack {

    //栈的大小
    private int size;
    //栈中元素个数
    private int nums;
    //存储元素的数组
    private String[] arrs;

    ArrayStack(int size){
        this.arrs = new String[size];
        this.nums = 0;
        this.size = size;
    }

    //入栈操作
    public boolean pushStack(String elements){
        if(nums <= size){
            this.arrs[nums] = elements;
            nums++;
            return true;
        }
        return false;
    }
    //出栈操作
    public String popStack(){
        if(nums == 0){
            return null;
        }
        String result = this.arrs[nums-1];
        this.arrs[nums-1] = null;
        nums--;
        return result;
    }
    //获取栈中元素个数
    //获取栈的大小
}

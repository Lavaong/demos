package com.ltlon.restapi.leetcode;

/**
 * @program: restapi
 * @description: 循环队列
 * @author: LTL
 * @create: 2020-05-08 16:37
 **/
public class CircularQueue {
    //数组大小为n
    private String[] items;
    private int n = 0;
    //头下标，尾下标起始都为0
    private int head = 0;
    private int tail = 0;

    public CircularQueue(int capacity){
        items = new String[capacity];
        n = capacity;
    }

    public boolean enQueue(String item){
        //队列满了
        if((tail+1)%n==head){
            return false;
        }
        items[tail] = item;
        tail = (tail+1)%n;
        return true;
    }

    public String deQueue(){
        if(tail == head){
            return "";
        }
        String result = items[head];
        head = (head+1)%n;
        return result;
    }

}

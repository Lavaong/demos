package com.ltlon.restapi.leetcode;

import lombok.Data;

/**
 * @program: restapi
 * @description: 顺序队列
 * @author: LTL
 * @create: 2020-05-06 18:57
 **/
@Data
public class ArrayQueue {
    /**
     * 存储元素的容器
     */
    private String[] arrs;
    /**
     * 队列的容量
     */
    private int size;
    /**
     * head 表示队列头下标
     */
    private int head = 0;
    /**
     * tail 表示队列尾下标
     */
    private int tail = 0;

    public ArrayQueue(int capacity) {
        arrs = new String[capacity];
        size = capacity;
    }

    public boolean enQueue(String element) {
        if (tail == size) {
            if(head == 0){
                //队列满载
                return false;
            }
            //数据搬移
            for (int i = head; i < tail; i++) {
                arrs[i-head] = arrs[i];
            }
            //搬移完成后重新赋予首尾部指针
            tail = tail - head;
            head = 0;
        }
        arrs[tail] = element;
        tail++;
        return true;
    }

    public String deQueue() {
        if (head == tail) {
            return null;
        }
        String ret = arrs[head];
        ++head;
        return ret;
    }
}

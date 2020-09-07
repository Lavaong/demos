package com.ltlon.restapi.leetcode;

/**
 * @program: restapi
 * @description: 链式队列,先进先出
 * @author: LTL
 * @create: 2020-05-07 09:53
 **/
public class LinkQueue <E>{
    /**队列长度 = 元素长度*/
    private int size;

    private ListNode<E> head  = null;
    private ListNode<E> tail = null;

    public LinkQueue(){
        this.size = 0;
        head = new ListNode<E>(null);
        tail = head;
    }
    public boolean enQueue(E element){

        tail.setNext(new ListNode<>(element));
        tail  = tail.getNext();
        size++;
        return true;
    }

    public ListNode deQueue(){
        head = head.getNext();
        return head;
    }
}

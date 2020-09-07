package com.ltlon.restapi.leetcode;

/**
 * @program: restapi
 * @description: 链式栈，用链表实现的栈结构，先进后出
 * @author: LTL
 * @create: 2020-05-06 15:57
 **/
public class LinkStack {
    /**队列长度 = 元素长度*/
    private int size;

    private ListNode<String> head  = null;
    private ListNode<String> tail = null;

    public LinkStack(){
        this.size = 0;
        head = new ListNode<>("null");
        tail = head;
    }
    public boolean enQueue(String element){
        tail.setNext(new ListNode<>(element));
        tail  = tail.getNext();
        size++;
        return true;
    }
    //先进后出，后进先出，从队尾取元素
    public ListNode deQueue(){
        ListNode temp = head;
        while(temp.getNext().getNext() != null){
            temp  = temp.getNext();
        }
        ListNode result = tail;
        temp.setNext(null);
        tail = temp;
        return result;
    }
}

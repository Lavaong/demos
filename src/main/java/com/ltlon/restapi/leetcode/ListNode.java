package com.ltlon.restapi.leetcode;

/**
 * @program: restapi
 * @description: 单向链表 singly-linked list
 * @author: LTL
 * @create: 2020-04-27 17:05
 **/
public class ListNode<E> {


        //节点数据
        public E item;
        //节点的next节点
        public ListNode<E> next;
        ListNode(E e){
            this.item = e;
        }

        public ListNode<E> getNext(){
            return next;
        }

        public ListNode<E> setNext(ListNode<E> next){
            this.next = next;
            return next;
        }

        public E getItem(){
            return item;
        }
}

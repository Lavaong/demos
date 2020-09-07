package com.ltlon.restapi.leetcode.node;

/**
 * @program: restapi
 * @description: 单向链表 singly-linked list
 * @author: LTL
 * @create: 2020-04-27 17:05
 **/
public class ListNode<E> {


        //节点数据
        private E item;
        //节点的next节点
        private ListNode<E> next;
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

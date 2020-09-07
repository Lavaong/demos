package com.ltlon.restapi.leetcode.node;

/**
 * @program: restapi
 * @description: 树链表
 * @author: LTL
 * @create: 2020-05-26 15:42
 **/
public class TreeNode<E> {


    //节点数据
    private E item;
    //节点的左子节点
    private TreeNode<E> left;
    //节点的右子节点
    private TreeNode<E> right;

    public TreeNode(E e){
        this.item = e;
    }

    public E getItem(){
        return this.item;
    }

    public void setItem(E item){
        this.item = item;
    }
    public TreeNode<E> getLeftNode(){
        return this.left;
    }
    public TreeNode<E> getRightNode(){
        return this.right;
    }

    public void setLeftNode(TreeNode<E> left){
        this.left = left;
    }

    public void setRightNode(TreeNode<E> right){
        this.right = right;
    }
}

package com.ltlon.restapi;

import com.ltlon.restapi.leetcode.ListNode;

public class Teest {

    public static void main(String[] args) {
        LinkList linkList = new LinkListImpl();
        for (int i = 0; i < 1000; i++) {
            linkList.add(new Node(i));
        }
        System.out.println(linkList.isExists(100));
    }

    private static final int MAX_LEVEL = 16;

    public interface LinkList {
        boolean isExists(Integer value);

        void add(Node node);
    }

    private static class LinkListImpl implements LinkList {

        //所有层的头节点，节点内容为空,只使用forward数组存储每层的起始next节点
        private Node head = new Node();
        private int levelCount = 1;

        //O(logn)
        @Override
        public boolean isExists(Integer value) {
            Node tempNode = head;
            for (int i = levelCount - 1; i >= 0; i--) {
                if (tempNode.forward[i] != null && tempNode.forward[i].value < value) {
                    tempNode = tempNode.forward[i];
                }
            }
            //最底层
            if (tempNode.forward[0] != null && tempNode.forward[0].value == value.intValue()) {
                return true;
            }
            return false;
        }

        //O(logn)
        @Override
        public void add(Node node) {
            if (node == null) {
                return;
            }
            if (node.maxLevel == 0) {
                node.maxLevel = randomLevel();
            }
            //声明一个临时节点数组，用于存储找到的所有前驱节点
            Node[] temp = new Node[node.maxLevel];
            for (int i = 0; i <= node.maxLevel - 1; i++) {
                temp[i] = head;
            }
            //从最高层开始往下层找插入位置(0-15)
            Node preNode = head;
            for (int i = node.maxLevel - 1; i >= 0; i--) {
                //从每层的头部开始往尾部查找到一个大于要插入的值的的前一个节点
                while (preNode.forward[i] != null && preNode.forward[i].value < node.value) {
                    preNode = preNode.forward[i];
                }
                temp[i] = preNode;
            }
            //在所有层中建立要插入节点和寻找到的前驱节点的联系
            for (int i = 0; i <= node.maxLevel - 1; ++i) {
                //该节点指向前驱节点的next节点
                node.forward[i] = temp[i].forward[i];
                //前驱节点指向该节点
                temp[i].forward[i] = node;
            }
            if (levelCount < node.maxLevel) {
                levelCount = node.maxLevel;
            }
        }
    }

    public static class Node {
        private Integer value;
        //该数组表示该节点的所有next节点（按照插入顺序排序）在各个层的位置
        //forward[i]表示该节点在第i层的next节点
        private Node forward[] = new Node[MAX_LEVEL];
        //表示节点存在的最大层数;
        private int maxLevel = 0;

        public Node(Integer value) {
            this.value = value;
        }

        public Node() {
        }
    }

    private static int randomLevel() {
        int level = 1;
        while (Math.random() < 0.5 && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

}

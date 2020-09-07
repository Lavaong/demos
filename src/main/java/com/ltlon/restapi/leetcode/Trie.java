package com.ltlon.restapi.leetcode;

import com.ltlon.restapi.leetcode.node.TrieNode;

/**
 * @program: restapi
 * @description: Trie 树并不适合动态集合数据的查找（红黑树和hash表更适合）更加适合前缀字符串的匹配查询
 * N叉树
 * @author: LTL
 * @create: 2020-06-22 14:46
 */
public class Trie {

    //存储根字符，无意义
    private static TrieNode root = new TrieNode('/');

    //往Trie树中插入一个字符串（模式串）
    public static void insert(char[] text){
        TrieNode p = root;
        for (int i = 0; i < text.length; i++) {
            int index = text[i] - 'a';
            //没有元素，直接插入
            if(p.getChildren()[index] == null){
                TrieNode newNode = new TrieNode(text[i]);
                p.getChildren()[index] = newNode;
            }
            //有元素,则无需插入,进入下一层，且将p指针指向刚刚找到的这个元素
            p = p.getChildren()[index];
        }
        //最后一个元素设置结束标记
        p.setEndingChar(true);
    }

    //往Trie树中查找一个字符串
    public boolean find(char[] pattern){
        TrieNode p = root;
        for(int i = 0;i<pattern.length;++i){
            int index = pattern[i] - 'a';
            if(p.getChildren()[index] == null){
                //该字符串不存在其中
                return false;
            }
            p = p.getChildren()[index];
        }
        if(p.isEndingChar() == false) {
            return false;
        }else{
            return  true;
        }
    }

}

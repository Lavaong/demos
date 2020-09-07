package com.ltlon.restapi.leetcode.node;

import lombok.Data;

/**
 * @program: restapi
 * @description: Trie树
 * @author: LTL
 * @create: 2020-06-22 14:33
 */
@Data
public class TrieNode {
    char data;
    TrieNode[] children;
    boolean isEndingChar;

    public TrieNode(char data){
        this.data = data;
        this.children = new TrieNode[26];
    }
}

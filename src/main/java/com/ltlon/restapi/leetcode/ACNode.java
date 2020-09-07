package com.ltlon.restapi.leetcode;

/**
 * @program: restapi
 * @description: AC自动机中的Trie树节点
 * @author: LTL
 * @create: 2020-06-24 11:08
 */
public class ACNode {

    public char data;
    //字符集只包含a-z这26个字符
    public ACNode[] children = new ACNode[26];
    //结尾字符为true
    public boolean isEndingChar = false;
    //当isEndingChar=true时候，记录模式串的长度
    public int length = -1;
    //失败指针，指向最长的可匹配后缀子串对应的前缀子串的下标结束位置
    public ACNode fail;

    public ACNode(char data){
        this.data = data;
    }
}

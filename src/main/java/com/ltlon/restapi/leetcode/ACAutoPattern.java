package com.ltlon.restapi.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: restapi
 * @description: AC自动机匹配算法（Aho-Corasick）。多模式串匹配算法
 * AC自动机的构建，包含两个操作
 * 1) 将多个模式串构建成Trie树，作为字典
 * 2) 在Trie树上构建失败指针，
 * AC自动机匹配主串：尽可能多的匹配模式串，且不漏掉
 * Tire树中的每一个节点都有一个失败指针
 * @author: LTL
 * @create: 2020-06-22 16:59
 */
public class ACAutoPattern {

    private ACNode root = new ACNode('/');

    //构建Trie树
    public void buildTrieTree(char[][] chars) {
        for (char[] ch : chars) {
            Trie.insert(ch);
        }
    }

    //构建失败指针，逐层依次求解每个节点的失败指针（通过已经求得的，深度更小，比较上层的节点的失败指针，
    // 来推导），失败指针的构建过程，是按层遍历树的过程
    public void buildFailurePointer() {
        Queue<ACNode> queue = new LinkedList<>();
        root.fail = null;
        queue.add(root);
        while (!queue.isEmpty()) {
            //返回被移除的节点
            ACNode p = queue.remove();
            //遍历该节点所有的孩子位置,求这些孩子节点的失败指针
            for (int i = 0; i < 26; i++) {
                ACNode pc = p.children[i];
                if (pc == null) {
                    continue;
                }
                if (p == root) {
                    pc.fail = root;
                } else {
                    ACNode q = p.fail;
                    while (q != null) {
                        //查询与PC节点相等的下标元素是否存在在q的孩子节点中
                        ACNode qc = q.children[pc.data - 'a'];
                        if (qc != null) {
                            pc.fail = qc;
                            break;
                        }
                        q = q.fail;
                    }
                    if (q == null) {
                        pc.fail = root;
                    }
                }
                queue.add(pc);
            }
        }
    }

    //main是主串,主串从i=0开始，AC自动机从指针p=root 开始,i从0开始,
    //（1）如果p指向的节点有一个等于模式串b[i]的子节点x,我们就更新p指向x,
    //（2）如果p指向的节点没有等于模式串b[i]的子节点,我们让p=p->fail,然后继续这两个过程
    // 返回主串中每个可以匹配的模式串的出现的位置;
    //当用户输入主串内容,如果主串中的内容可以在模式串树中找到匹配的位置,就可以将该位置设置为*，起到屏蔽关键字的作用
    public void match(char[] main) {
        int n = main.length;
        ACNode p = root;
        for (int i = 0; i < n; ++i) {
            //从主串的首字符开始
            int idx = main[i] - 'a';
            //第一次p=root,故不进入此循环
            //查询模式串Trie树中是否存在某个节点的一个子节点的值不等于主串中某字符的值
            //如果不等于，
            while (p.children[idx] == null && p != root) {
                //
                p = p.fail;
            }
            //查询所有模式串的开头字符中是否存在对应主串中下标的元素
            p = p.children[idx];
            //如果p等于null,既没有匹配到,从root开始重新匹配主字符串的第二个，第三个，第N个字符
            if (p == null) {
                p = root;
            }
            //如果p不等于null,既匹配到了
            ACNode temp = p;
            //则查看匹配到的到p的路径的字符串是不是结束字符，如果是，则输出该字符在主串中的位置，
            // 然后查看p的路径对应的的最长后缀字符串对应的最长前缀字符串（模式串中）的结尾下标
            // 的节点是不是结束节点，如果是，则说明这个模式串也是到p的路径中的主串中的一部分，则输出该模式串的位置,
            // 循环查找该失败节点，一直到最后的根节点，既查找到p路径的所有子串的对应的最长前缀子串,可能有多个模式串被匹配到。
            while (temp != root) {
                //判断该节点是不是模式串树的结束节点,如果是结束节点,则输出匹配到的该模式串在主串中的起始位置和该模式串的长度
                if (temp.isEndingChar) {
                    int pos = i - temp.length + 1;
                    System.out.println("匹配主串中的起始下标为：" + pos + ";长度" + temp.length);
                }
                //如果该节点不是模式串的结束节点，
                // 则将p指针指向p的失败指针,循环检查该节点是不时模式串的尾节点;
                temp = temp.fail;
            }
        }
    }
}

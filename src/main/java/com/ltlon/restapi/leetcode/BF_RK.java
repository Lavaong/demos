package com.ltlon.restapi.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: restapi
 * @description: 字符串匹配算法，BF(Bruit Force 暴力匹配算法)和RK算法
 * @author: LTL
 * @create: 2020-06-17 16:37
 */
public class BF_RK {

    /**
     * @param mainStr    主串
     * @param patternStr 模式串（子串）
     * @return boolean
     * @throws
     * @description 朴素匹配BF算法 时间复杂度O(m*n)，字符串一个一个去匹配
     * @author litlb
     * @date 2020/6/17 16:44
     */
    public boolean bf(String mainStr, String patternStr) {
        boolean flag = false;
        char[] patterns = patternStr.toCharArray();
        char[] mains = mainStr.toCharArray();
        for (int i = 0; i < mainStr.length() - patternStr.length(); ) {
            for (int k = 0; k < patterns.length; k++) {
                if (patterns[k] != mains[i]) {
                    flag = false;
                    break;
                }
                i++;
            }
            if (flag != false) {
                flag = true;
                return flag;
            }
        }
        return flag;
    }


    /**
     * @param mainStr
     * @param patternStr
     * @return boolean
     * @throws
     * @description BF算法的升级版 RK算法 运用hash函数对主串的所有子串求hash值，然后比对子串的hash值和模式串的hash值
     * @author litlb
     * @date 2020/6/17 17:17
     */
    public boolean rk(String mainStr, String patternStr) {
        Integer patternHash = hash(patternStr);
        int m = mainStr.length();
        int n = patternStr.length();
        for (int i = 0; i < m - n + 1; i++) {
            String subStr = mainStr.substring(i,i+n);
            if(hash(subStr) == patternHash){
                break;
            }
        }
        return false;
    }

    /**
     * @description 对a-z类型的字符串求其hash值,规则：a:1,b:2.......z:26。可认为每个字符是这个
     * 26进制中的一员，然后求其10进制的值。若有abc串，则其hash值为：a*26^2+b*26^1+c*26^0 => 1*26^2+2*26^1+3
     * => 731
     * @author litlb
     * @param str
     * @return int
     * @throws
     * @date 2020/6/18 9:57
     */
    Map<String, Integer> dictionary = new HashMap<>();

    {
        dictionary.put("a", 0);
        dictionary.put("b", 1);
        dictionary.put("c", 2);
        dictionary.put("d", 3);
        dictionary.put("e", 4);
        dictionary.put("f", 5);
        dictionary.put("g", 6);
        dictionary.put("h", 7);
        dictionary.put("i", 8);
        dictionary.put("j", 9);
        dictionary.put("k", 10);
        dictionary.put("l", 11);
        dictionary.put("m", 12);
        dictionary.put("n", 13);
        dictionary.put("o", 14);
        dictionary.put("p", 15);
        dictionary.put("q", 16);
        dictionary.put("r", 17);
        dictionary.put("s", 18);
        dictionary.put("t", 19);
        dictionary.put("u", 20);
        dictionary.put("v", 21);
        dictionary.put("w", 22);
        dictionary.put("x", 23);
        dictionary.put("y", 24);
        dictionary.put("z", 25);
    }

    private int hash(String str) {
        char[] chars = str.toCharArray();
        int max = chars.length;
        int total = 0;
        for (int i = 0; i < chars.length; i++) {
            total += dictionary.get(chars[i]) * 26 ^ (max - 1);
            max--;
        }
        return total;
    }
}

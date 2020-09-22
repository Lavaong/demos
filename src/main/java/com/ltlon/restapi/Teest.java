package com.ltlon.restapi;

import com.ltlon.restapi.leetcode.ListNode;

public class Teest {

    public static void main(String[] args) {

    }

    public void findLastElement(ListNode root, int n) {
        ListNode first = root;
        ListNode last = root;
        if (root == null) {
            return;
        }
        for (int i = 0; i < n; i++) {
            first = first.getNext();
        }
        while(first.getNext() != null){
            first = first.getNext();
            last = last.getNext();
        }
        last.setNext(last.getNext().getNext());
    }


}

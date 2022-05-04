package com.ltlon.restapi.leetcode;

import com.ltlon.restapi.leetcode.node.TreeNode;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: restapi
 * @description: 高频算法面试题
 * @author: LTL
 * @create: 2020-04-26 11:36
 **/
public class Solutions {
    private static ListNode head = new ListNode(1);

    static {
        head.setNext(new ListNode(2)).
                setNext(new ListNode(3)).
                setNext(new ListNode(4)).
                setNext(new ListNode(5).
                        setNext(new ListNode(null)));
    }

    public static void main(String[] args) {
/*        String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(s));
        String s2 = "aab";
        //System.out.println(partition(s2));
        System.out.println(isPalindrome2(s));*/
        ConcurrentHashMap<Integer,Integer> h = new ConcurrentHashMap<Integer,Integer>(8);
        h.put(1,4);
        System.out.println(h.computeIfAbsent(1,x -> 3));
        System.out.println(h.get(1));
    }

    // 找出数组中只出现一次的数字，其他数字都是两次
    // 异或交换律 a ^ b ^ c = a ^ c ^ b
    // 0 和 其他数异或是其他数，数和它本身异或是0;
    public int singleNumber(int nums[]) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result = nums[i] ^ result;
        }
        return result;






    }

    //找出众数(1个)，既出现次数大于2/n(数组长度)的元素
    //摩尔投票法: 候选人记为nums[0],票数记录为 1, 遍历数组，若有相同的元素，则票数记录+1,,否则-1，若票数记录为0,
    //则更换候选人,记为nums[i],票数记录为1 ,则遍历完毕数组,必定会剩下元素最多的数。
    // 实际上是拿这个众数中的元素和其他元素一一抵消,因为众数的元素必定大于其他元素的总和,即众数-其他元素和 >= 1;
    public int majorityElement(int[] nums) {
        int ca = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == ca) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    ca = nums[i];
                    count = 1;
                }
            }
        }
        return ca;
    }


    //在矩形中发现特定元素是否存在，递归
    public boolean findTargetInMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        int startX = 0;
        int startY = matrix.length - 1;
        int endX = matrix[0].length - 1;
        int endY = 0;
        return find(matrix, target, startX, endX, startY, endY);
    }

    private boolean find(int[][] matrix, int target, int x, int endX, int y, int endY) {
        if (x > endX || y > endY) {
            return false;
        }
        if (matrix[y][x] == target) {
            return true;
        } else if (matrix[y][x] > target) {
            return find(matrix, target, x, endX, y--, endY);
        } else {
            return find(matrix, target, x++, endX, y, endY);
        }

    }


    //归并排序,数组nums1长度为m，且有序，数组nums2长度为n,且有序,合并到nums1(自动扩容，大于等于m+n)
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int nums1End = m - 1;
        int nums2End = n - 1;
        int totalEnd = m + n - 1;
        while (nums1End >= 0 && nums2End >= 0) {
            //尾部值比较大小填入最终数组
            nums1[totalEnd] = nums1[nums1End] > nums2[nums2End] ? nums1[nums1End--] : nums2[nums2End--];
            totalEnd--;
        }
        //若剩余nums2 还有未合并进去的，则将整个剩余的nums2元素，全部放置到nums1的前头
        System.arraycopy(nums2, 0, nums1, 0, nums2End + 1);
    }

    //使用数组判断字符串是否是回文字符串
    public static boolean isPalindrome(String s) {
        String letter = s.toLowerCase();
        StringBuilder sb = new StringBuilder();
        char[] strs = letter.toCharArray();
        for (char str : strs) {
            if (Character.isLetterOrDigit(str)) {
                sb.append(str);
            }
        }
        int end = sb.length() - 1;
        int half = sb.length() / 2;
        for (int i = 0; i < half; i++, end--) {
            char c = sb.charAt(i);
            char d = sb.charAt(end);
            if (c != d) {
                return false;
            }
        }
        return true;
    }

    //使用单向链表判断字符串是否是回文字符串(快慢指针)
    public static boolean isPalindrome2(String str) {
        if (str == null || StringUtils.isEmpty(str)) {
            return false;
        }
        if (str.length() == 1) {
            return true;
        }
        char[] arrs = str.toLowerCase().toCharArray();
        ListNode<Character> head = new ListNode<Character>(arrs[0]);
        for (int i = 1; i < arrs.length; i++) {
            ListNode ln = new ListNode<>(arrs[i]);
            head.setNext(ln);
            head = head.getNext();
        }
        //中点奇数偶数情况(true 偶数，false 奇数
        boolean flag = str.length() % 2 == 0 ? true : false;
        //慢指针
        ListNode slow = head;
        //快指针
        ListNode fast = head;
        //慢指针的前节点;
        ListNode pre = null;
        //遍历链表并反转slow 前部分
        ListNode aHead = head;
        ListNode bHead = head;
        while (fast != null && fast.getNext() != null) {
            aHead = slow;
            ListNode temp = slow.getNext();
            slow.setNext(pre);
            pre = slow;
            slow = temp;
            fast = fast.getNext().getNext();
        }
        //偶数 slow 指针 最终在 1 <- 2 (X) 3 -> 4 中的 3的位置,2 和 3 节点之间是断开的;
        if (flag) {
            bHead = slow;
        } else {//奇数 slow 指针 最终在 1 <- 2 (X) 3 -> 4 -> 5 中的 3的位置,2 和 3节点之间是断开的
            bHead = slow.getNext();
        }
        while (aHead != null && bHead != null) {
            if (aHead.getItem() != bHead.getItem()) {
                return false;
            }
            aHead = aHead.getNext();
            bHead = bHead.getNext();
        }
        return true;
    }

    //1.单链表反转使用循环迭代
    public static ListNode reverseListUseWhile(ListNode head) {
        ListNode last = null;
        //当前指针节点
        ListNode current = head;
        while (current != null) {
            //将下个节点存储起来
            ListNode temp = current.getNext();
            //设置当前节点的下个节点为上个节点
            current.setNext(last);
            //将当前节点作为上个节点保存起来，以便于下次循环使用
            last = current;
            //将当前节点指向temp中保存的下个节点
            current = temp;
        }
        return current;
    }

    //单链表反转使用递归
    public static ListNode reverseListUseRecurrence(ListNode head) {
        if (head == null || head.getNext() == null) {
            return head;
        }
        ListNode current = reverseListUseRecurrence(head.getNext());
        //反转指向
        current.setNext(head);
        //防止循环指向
        head.setNext(null);
        return current.getNext();
    }

    //2.链表中环的检测，快慢指针
    public boolean hasCycle(ListNode head) {
        if (head == null || head.getNext() == null) {
            return false;
        }
        ListNode fast = head.getNext();
        ListNode lowly = head;
        //快慢指针如果相遇既为有环，不相遇既为无环
        while (fast != lowly) {
            if (fast == null || fast.getNext() == null) {
                return false;
            }
            lowly = lowly.getNext();
            fast = fast.getNext().getNext();
        }
        return true;
    }

    //3.两个有序的链表的合并
    public ListNode mergeTwoLists(ListNode<Integer> l1, ListNode<Integer> l2) {
        ListNode<Integer> aIndex = l1;
        ListNode<Integer> bIndex = l2;
        ListNode newHead = new ListNode(-1);
        ListNode index = newHead;
        while (aIndex != null && bIndex != null) {
            //改变指针指向较小的节点同时指针后移一位
            if (aIndex.getItem() > bIndex.getItem()) {
                index.setNext(bIndex);
                bIndex = bIndex.getNext();
            } else {
                index.setNext(aIndex);
                aIndex = aIndex.getNext();
            }
            index = index.getNext();
        }
        index.setNext(aIndex == null ? bIndex : aIndex);
        return newHead.getNext();
    }

    //4.删除链表倒数第N个节点,并返回头节点
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //建立一个临时节点，指向头结点，处理边界条件
        ListNode temp = new ListNode(-1);
        temp.setNext(head);
        //双指针，并设置其间隔距离为n个节点,既第二个节点距离第一个节点n+1次循环;
        ListNode first = temp;
        ListNode second = temp;
        int i = 0;
        while (i != n + 1) {
            i++;
            second = second.getNext();
        }
        //如果第二个节点到达终点，则第一个节点指向的既为要删除的节点的前一个节点;
        while (second != null) {
            first = first.getNext();
            second = second.getNext();
        }
        //将此节点的下一节点指向下下节点，删除倒数第n个节点,完成。
        first.setNext(first.getNext().getNext());
        return temp.getNext();
    }


    //分割回文串
    public static List<List<String>> partition(String s) {
        int len = s.length();
        List<List<String>> result = new ArrayList();
        if (len == 0) {
            return result;
        }
        Deque<String> stack = new ArrayDeque();
        //回溯递归
        backTracking(s, 0, len, result, stack);
        return result;
    }


    private static void backTracking(String str, int start, int length, List res, Deque path) {
        if (start == length) {
            res.add(new ArrayList(path));
            return;
        }
        for (int i = start; i < length; i++) {
            if (!checkPalindrome(str, start, i)) {
                continue;
            }
            path.addLast(str.substring(start, i + 1));
            //递归直至遇到空节点
            backTracking(str, i + 1, length, res, path);
            //在一次深度优先搜索完毕后，将path中的元素清空，方便下次使用
            path.removeLast();
        }


    }

    //检测是否是回文串
    private static boolean checkPalindrome(String str, int start, int end) {
        while (start < end) {
            if (str.charAt(start) != str.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }


    //求一个数的平方根，精确到第n位
    public float sequareRoot(int target, int n) {
        if (target <= 0) {
            return -1;
        }
        //整数位
        int in = 0;
        for (int i = 0; i < target; i++) {
            if (i * i == target) {
                return new Float(i * 10 ^ -n);
            }
            if (i * i < target && (i + 1) * (i + 1) > target) {
                return i;
            }
        }
        return 0;
    }

    //取得整数位
    public int bSearchSequareInt(int target, int left, int right) {
        int mid = left + (right - left) / 2;
        if (mid * mid == target) {
            return mid;
        } else if (mid * mid > target) {
            return bSearchSequareInt(target, left, mid - 1);
        } else {
            return mid;
        }
    }
/*    //取得小数位
    public double bSearchDouble( int intF,int target,int bits){
        int left = 0;
        int right = 9

        if(mid*mid == target){
            return mid;
        }else if (mid * mid > target){
            return bSearchSequareInt(target,left,mid-1);
        }else{
            return mid;
        }
    }*/

    //二叉树的前序中序递归遍历
    public void preSearch(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root);
        preSearch(root.getLeftNode());
        preSearch(root.getRightNode());
    }

    public void inSearch(TreeNode root) {
        if (root == null) {
            return;
        }
        inSearch(root.getLeftNode());
        System.out.println(root);
        inSearch(root.getRightNode());
    }

    public void postSearch(TreeNode root) {
        if (root == null) {
            return;
        }
        postSearch(root.getLeftNode());
        postSearch(root.getRightNode());
        System.out.println(root);
    }

    //二叉树的前序，中序，后序迭代遍历
    public void preStackSearch(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode temp = stack.pop();
            System.out.println(temp.getItem());
            if(temp.getRightNode() != null){
                stack.push(temp.getRightNode());
            }
            if(temp.getLeftNode() != null){
                stack.push(temp.getLeftNode());
            }
        }
    }
    //计算卡特兰数:应用场景：给定N个节点，能形成多少种不同的二叉树
    //C(n+1) = 2*(2*n+1)/(n+2)) * C(n)
    public int caculateCatelan(int n) {
        if (n < 0) {
            return -1;
        }
        if (n == 0) {
            return 1;
        }
        return (2 * (2 * n + 1) / n + 2) * caculateCatelan(n - 1);
    }

    //二叉树的查找
    public boolean binarySearch(TreeNode<Integer> root, int data) {
        while (root != null) {
            if (data > root.getItem()) {
                root = root.getRightNode();
            } else if (data < root.getItem()) {
                root = root.getLeftNode();
            } else {
                return true;
            }
        }
        return false;
    }

    //二叉查找树的插入操作:如果根节点大于data,则在左子树中查找插入地，若左子树为空，直接插入左子树，若不为空，则将该左子树节点作为根节点递归调用该方法
    //如果根节点小于data,同理。
    public boolean binaryTreeInsert(TreeNode<Integer> root, int data) {
        if (root.getItem() > data) {
            if (root.getLeftNode() == null) {
                root.setLeftNode(new TreeNode<>(data));
                return true;
            } else {
                return binaryTreeInsert(root.getLeftNode(), data);
            }
        } else {
            if (root.getRightNode() == null) {
                root.setRightNode(new TreeNode<>(data));
                return true;
            } else {
                binaryTreeInsert(root.getRightNode(), data);
            }
        }
        return false;
    }

    //二叉查找树的删除操作
    public void binaryTreeDel(TreeNode<Integer> root, int data) {
        //p 指向要删除的节点
        TreeNode<Integer> p = root;
        //pp 指向要删除节点的父节点
        TreeNode<Integer> pp = null;
        //先从树中找到这个节点
        while (p != null && p.getItem() != data) {
            pp = p;
            if (p.getItem() < data) {
                p = p.getRightNode();
            } else {
                p = p.getLeftNode();
            }
        }
        if (p == null) {
            System.out.println("不存在此元素，无法删除");
            return;
        }
        //case 3: 要删除的节点有两个子节点;
        if (p.getLeftNode() != null && p.getRightNode() != null) {
            TreeNode<Integer> minP = p.getRightNode();
            TreeNode<Integer> minPP = p;
            //从右子树中找到最左边的节点
            while (minP.getLeftNode() != null) {
                minPP = minP;
                minP = minP.getLeftNode();
            }
            //将minP的数据替换到p节点中
            p.setItem(minP.getItem());
            //将p指向要被删除的节点
            //pp指向要被删除的节点的父亲节点，在最后统一删除
            p = minP;
            pp = minPP;
        }
        //case 1,2：要删除的节点只有一个子节点(左子节点或右子节点)或者是叶子节点，找到孩子节点，最后统一删除
        TreeNode child;//p的子节点
        if (p.getLeftNode() != null) {
            child = p.getLeftNode();
        } else if (p.getRightNode() != null) {
            child = p.getRightNode();
        } else {
            child = null;
        }
        //统一删除部分
        //删除的是根节点,则根节点原先的指针root指向子节点
        if (pp == null) {
            root = child;
            //删除的不是根节点，如果父节点的左子节点等于要删除的节点的值，则将该父节点的左子节点设置为要删除节点的孩子节点的值。
        } else if (pp.getLeftNode().getItem().equals(p.getItem())) {
            pp.setLeftNode(child);
        } else {
            //删除的不是根节点，如果父节点的右子节点等于要删除的节点的值，则将该父节点的右子节点设置为要删除节点的孩子节点的值。
            pp.setRightNode(child);
        }
    }

    //求包含n个节点的二叉树的高度（根节点到叶子节点的边数）
    int lefeHight = 0;
    int rightHight = 0;
    public int  caculateHeightTree(TreeNode<Integer> treeNode,int height) {
        if(treeNode.getRightNode() == null && treeNode.getLeftNode()==null) {
            return height;
        }
        if(treeNode.getLeftNode() != null) {
            ++lefeHight;
            lefeHight = caculateHeightTree(treeNode.getLeftNode(), lefeHight);
        }
        if(treeNode.getRightNode() != null) {
            ++rightHight;
            rightHight = caculateHeightTree(treeNode.getRightNode(),rightHight);
        }
        return Math.max(lefeHight,rightHight);
    }

    //深度优先搜索(二叉树的深度遍历:先序，中序，后序)
    //广度优先搜索(二叉树的按层遍历)辅助队列
    LinkQueue<TreeNode> queue = new LinkQueue();
    public void widthSearch(TreeNode root) {
        while (queue != null) {
            TreeNode  temp = (TreeNode) queue.deQueue().getItem();
            if (temp.getLeftNode() != null) {
                queue.enQueue(root.getLeftNode());
            }
            if (temp.getRightNode() != null) {
                queue.enQueue(root.getRightNode());
            }
        }
    }
    //找出数组中重复的元素
    public int findReadpeadNumber(int[] nums){
        HashSet hashSet = new HashSet();
        for(int num : nums){
            if(hashSet.add(num) == false){
                return num;

            }
        }
        return -1;

    }

}

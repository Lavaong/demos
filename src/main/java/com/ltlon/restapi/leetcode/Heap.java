package com.ltlon.restapi.leetcode;

/**
 * @program: restapi
 * @description: 用数组实现堆，堆的结构是完全二叉树，用数组进行实现。
 * @author: LTL
 * @create: 2020-05-29 16:57
 **/
public class Heap {
    //数组，从下标1开始存储数据
    private int[] a;
    //堆的大小
    private int n;
    //堆中元素计数
    private int count;

    public static void main(String[] args) {
        int i = 7;
        System.out.println(i/2);
    }
    public Heap(int capacity) {
        this.n = capacity;
        this.count = 0;
        //因为下标开始从1，故capacity要+1;
        this.a = new int[capacity + 1];
    }

    //堆插入，在堆的尾部开始插入，然后启动堆化过程，完成堆的定义
    public void insert(int data) {
        if (count >= n) {
            System.out.println("满载");
            return;
        }
        ++count;
        a[count] = data;
        //递归堆化（从下往上），大顶堆,堆顶元素下标索引为1,
        int i = count;
        while (i / 2 > 0 && a[i] > a[i / 2]) {
            int temp = a[i];
            a[i] = a[i / 2];
            a[i / 2] = temp;
            i = i / 2;
        }
    }

    //堆删除，删除堆顶元素
    public boolean removeMax() {
        if (count == 0) {
            return false;
        }
        //将堆顶元素的指针指向最后一个元素的值,同时也删除了堆顶元素
        a[1] = a[count];
        //删除最后一个元素的位置，整体计数减1
        --count;
        //堆化,从上往下（大顶堆）
        heapify(a, count, 1);
        return true;
    }

    /**
     * @param arr      堆
     * @param count    堆中所有元素的总量
     * @param delIndex 要删除的元素的下标
     * @return void
     * @throws
     * @description 删除堆顶元素的堆化过程，堆的插入和堆的删除主要都是堆化的过程，堆化的路径是
     * 顺着节点所在路径比较交换的，故和树的
     * 高度成正比，完全二叉树的高度不会超过O(log n),故堆化的时间复杂度也是O(log n)
     * @author litlb
     * @date 2020/6/16 10:32
     */
    private static void heapify(int[] arr, int count, int delIndex) {
        if (count <= 1) {
            return;
        }
        while (true) {

            int maxIndex = delIndex;
            //左子节点在范围内，且左子节点的值大于现在父元素的值
            if (delIndex * 2 <= count && arr[delIndex * 2] > arr[maxIndex]) {
                //父元素的下标应该为左子节点的下标
                maxIndex = delIndex * 2;
            }
            //右子节点在范围内，且右子节点的值大于现在父元素的值
            if (delIndex * 2 + 1 <= count && arr[delIndex * 2 + 1] > arr[maxIndex]) {
                //父元素的下标应该为右子节点的下标
                maxIndex = delIndex * 2 + 1;
            }
            //若上述一个都没执行，则该元素就是父元素，大于等于左右子节点,不需要在进行堆化过程
            if (maxIndex == delIndex) {
                break;
            }
            //若上述有一个执行,则交换两个元素的位置，递归进行下一步堆化
            int temp = arr[maxIndex];
            arr[maxIndex] = arr[delIndex];
            arr[delIndex] = temp;
            //交换后的子节点的位置继续往下进行堆化操作
            delIndex = maxIndex;
        }
    }

    /**
     * @param arr 待构建为堆的数组
     * @param n   数组长度
     * @return void
     * @throws
     * @description 构建一个堆O(n)
     * @author litlb
     * @date 2020/6/16 11:56
     */
    public static void buildHeap(int[] arr, int n) {
        //从最后一个叶子节点的父节点开始向上堆化，对于完全二叉树来说，该节点后的节点，既n/2到n的节点
        //都是叶子节点。
        for (int i = n / 2; i >= 1; --i) {
            heapifyForBuild(arr, n, i);
        }
    }

    /**
     * @param arr 待构建为堆的数组
     * @param n   数组长度
     * @param i   当前下标
     * @return void
     * @throws
     * @description 构建堆过程中的堆化
     * @author litlb
     * @date 2020/6/16 12:08
     */
    private static void heapifyForBuild(int[] arr, int n, int i) {
        while (true) {
            int maxIndex = i;
            if (i * 2 <= n && arr[i] < arr[i * 2]) {
                maxIndex = i * 2;
            }
            if (i * 2 + 1 <= n && arr[maxIndex] < arr[i * 2 + 1]) {
                maxIndex = i * 2 + 1;
            }
            if (maxIndex == i) {
                break;
            }
            //若上述有一个执行,则交换两个元素的位置，递归进行下一步堆化
            int temp = arr[maxIndex];
            arr[maxIndex] = arr[i];
            arr[i] = temp;
            //交换后的子节点的位置的元素继续进行下次的循环堆化
            i = maxIndex;
        }
    }

    /**
     * @param arr 待排序数组，先将数组构建成堆O(n)，然后再排序O(nlog n),每次堆化都是O(log n),需要进行n次。故整体的时间复杂度为O(n*log n)
     * @param n   数组中的所有元素
     * @return void
     * @throws
     * @description 利用堆进行排序O(nlogn)
     * @author litlb
     * @date 2020/6/16 15:07
     */
    public static void sortByHeap(int[] arr, int n) {
        if (n <= 1) {
            return;
        }
        //指向最后一个元素,
        int count = n;
        while(count > 1){
            //堆顶元素和最后一个元素交换
            int temp = arr[count];
            arr[count] = arr[1];
            arr[1] = temp;
            //同时标记堆中的最后一个元素为已经删除
            --count;
            heapify(arr,n,1);
        }
    }
}

package DataStructures;


import java.util.Arrays;

/**
 * 线段树
 * 线段树是标准的平衡二叉树
 * 线段树中真实的元素都在叶子节点上，非叶子节点均为区间属性
 * 【【数据结构】线段树（Segment Tree）】https://www.bilibili.com/video/BV1cb411t7AM
 */

public class SegmentTree {
    private static final int MAX_LEN = 1000;  // 保证能cover建树后对应完全二叉树的节点数量
    int[] arr;
    int[] tree;

    public SegmentTree(int[] arr) {
        this.arr = arr;
        this.tree = new int[MAX_LEN];
        buildTree(0, 0, arr.length-1);
    }

    public void buildTree(int node, int left, int right) {
        if (left == right) {
            tree[node] = arr[left];
            return;
        }
        int mid = (left + right) / 2;
        buildTree(node * 2 + 1, left, mid);
        buildTree(node * 2 + 2, mid + 1, right);
        tree[node] = tree[node*2+1] + tree[node*2+2];
    }

    public void update(int idx, int val) {
        update(0, 0, arr.length-1, idx, val);
    }
    private void update(int node, int left, int right, int idx, int val) {  // node left right 为线段树自有属性, idx val 为要更新的信息
        if (idx < left || idx > right) return;
        int mid = (left + right) / 2;
        int lNode = node * 2 + 1;
        int rNode = node * 2 + 2;
        if (left == right) {  // 如果left==right，那么它们一定等于idx，因为不包含idx的区间已经被剪掉了
            arr[idx] = val;
            tree[node] = arr[idx];
            return;
        }
        update(lNode, left, mid, idx, val);
        update(rNode, mid + 1, right, idx, val);
        tree[node] = tree[lNode] + tree[rNode];
    }
    public int query(int queryL, int queryR) {
        return query(0, 0, arr.length-1, queryL, queryR);
    }
    private int query(int node, int left, int right, int queryL, int queryR) {  // node left right 为线段树自有属性, queryL queryR 为要查询的信息
        if (queryL > right || queryR < left) return 0;  // 无交集
        System.out.println(left + "," + right + ",   " + tree[node]);
        if (queryL <= left && queryR >= right) return tree[node]; // 全部cover，全部返回，当前区间不需要裁剪
        int mid = (left + right) / 2;
        int leftSum = query(node * 2 + 1, left, mid, queryL, queryR);
        int rightSum = query(node * 2 + 2, mid + 1, right, queryL, queryR);
        return leftSum + rightSum;
    }


    public static void main(String[] args) {
        SegmentTree segmentTree = new SegmentTree(new int[]{1,3,5,7,9,11});
        System.out.println(Arrays.toString(segmentTree.tree));
        System.out.println(segmentTree.query(2, 5));// 查询区间索引为2-5的元素总和
        segmentTree.update(4, 6); // 将索引为4的元素改为6
        System.out.println(Arrays.toString(segmentTree.tree));
        System.out.println(segmentTree.query(2, 5));
    }
}

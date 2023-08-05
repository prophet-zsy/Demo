import javax.print.DocFlavor;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/* 堆排

class Solute {
    public static void adjustHeap(int [] data, int pos, int len) {
        int tem, child;
        for (tem = data[pos];2*pos+1 < len; pos = child) {
            child = 2 * pos + 1;
            if (2*pos+2 < len && data[child] < data[child+1])
                child ++;
            if (data[child] > tem)
                data[pos] = data[child];
            else
                break;
        }
        data[pos] = tem;
    }

    public static int[] heapSort(int [] data) {
        int len = data.length;
        for (int i = len / 2; i >= 0; i --)
            adjustHeap(data, i, len);
        for (int i = len - 1; i > 0; i --) {
            int tem = data[0];
            data[0] = data[i];
            data[i] = tem;
            adjustHeap(data, 0, i);
        }
        return data;
    }
}


public class demo {
    public static void main(String []args) throws InterruptedException {
//        client c = new client();
//        c.test();
        int []data = {1,5,46,9,6,9,49,4,9,56,6,32,66,78,56,2,56};
        Solute.heapSort(data);
        for (int ite : data) {
            System.out.print(ite + " ");
        }
    }
}

*/

/*
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class demo {
    public static void main(String []args) {
        Solution s = new Solution();
        System.out.println(s.kthSmallest());
    }
}


class Algorithm.TreeNode{
    Algorithm.TreeNode left;
    Algorithm.TreeNode right;
    int val;
}


class Solution {
    public int kthSmallest(Algorithm.TreeNode root, int k) {
        // 中序遍历生成数值列表
        List<Integer> inorderList = new ArrayList<Integer>();
        inorder(root, inorderList);

        // 构造平衡二叉搜索树
        AVL avl = new AVL(inorderList);

        // 模拟1000次插入和删除操作
        int[] randomNums = new int[1000];
        Random random = new Random();
        for (int i = 0; i < 1000; ++i) {
            randomNums[i] = random.nextInt(10001);
            avl.insert(randomNums[i]);
        }
        shuffle(randomNums); // 列表乱序
        for (int i = 0; i < 1000; ++i) {
            avl.delete(randomNums[i]);
        }

        return avl.kthSmallest(k);
    }

    private void inorder(Algorithm.TreeNode node, List<Integer> inorderList) {
        if (node.left != null) {
            inorder(node.left, inorderList);
        }
        inorderList.add(node.val);
        if (node.right != null) {
            inorder(node.right, inorderList);
        }
    }

    private void shuffle(int[] arr) {
        Random random = new Random();
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            int randIndex = random.nextInt(length);
            int temp = arr[i];
            arr[i] = arr[randIndex];
            arr[randIndex] = temp;
        }
    }
}

// 平衡二叉搜索树（AVL树）：允许重复值
class AVL {
    Node root;

    // 平衡二叉搜索树结点
    class Node {
        int val;
        Node parent;
        Node left;
        Node right;
        int size;
        int height;

        public Node(int val) {
            this(val, null);
        }

        public Node(int val, Node parent) {
            this(val, parent, null, null);
        }

        public Node(int val, Node parent, Node left, Node right) {
            this.val = val;
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.height = 0; // 结点高度：以node为根节点的子树的高度（高度定义：叶结点的高度是0）
            this.size = 1; // 结点元素数：以node为根节点的子树的节点总数
        }
    }

    public AVL(List<Integer> vals) {
        if (vals != null) {
            this.root = build(vals, 0, vals.size() - 1, null);
        }
    }

    // 根据vals[l:r]构造平衡二叉搜索树 -> 返回根结点
    private Node build(List<Integer> vals, int l, int r, Node parent) {
        int m = (l + r) >> 1;
        Node node = new Node(vals.get(m), parent);
        if (l <= m - 1) {
            node.left = build(vals, l, m - 1, node);
        }
        if (m + 1 <= r) {
            node.right = build(vals, m + 1, r, node);
        }
        recompute(node);
        return node;
    }

    // 返回二叉搜索树中第k小的元素
    public int kthSmallest(int k) {
        Node node = root;
        while (node != null) {
            int left = getSize(node.left);
            if (left < k - 1) {
                node = node.right;
                k -= left + 1;
            } else if (left == k - 1) {
                break;
            } else {
                node = node.left;
            }
        }
        return node.val;
    }

    public void insert(int v) {
        if (root == null) {
            root = new Node(v);
        } else {
            // 计算新结点的添加位置
            Node node = subtreeSearch(root, v);
            boolean isAddLeft = v <= node.val; // 是否将新结点添加到node的左子结点
            if (node.val == v) { // 如果值为v的结点已存在
                if (node.left != null) { // 值为v的结点存在左子结点，则添加到其左子树的最右侧
                    node = subtreeLast(node.left);
                    isAddLeft = false;
                } else { // 值为v的结点不存在左子结点，则添加到其左子结点
                    isAddLeft = true;
                }
            }

            // 添加新结点
            Node leaf = new Node(v, node);
            if (isAddLeft) {
                node.left = leaf;
            } else {
                node.right = leaf;
            }

            rebalance(leaf);
        }
    }

    // 删除值为v的结点 -> 返回是否成功删除结点
    public boolean delete(int v) {
        if (root == null) {
            return false;
        }

        Node node = subtreeSearch(root, v);
        if (node.val != v) { // 没有找到需要删除的结点
            return false;
        }

        // 处理当前结点既有左子树也有右子树的情况
        // 若左子树比右子树高度低，则将当前结点替换为右子树最左侧的结点，并移除右子树最左侧的结点
        // 若右子树比左子树高度低，则将当前结点替换为左子树最右侧的结点，并移除左子树最右侧的结点
        if (node.left != null && node.right != null) {
            Node replacement = null;
            if (node.left.height <= node.right.height) {
                replacement = subtreeFirst(node.right);
            } else {
                replacement = subtreeLast(node.left);
            }
            node.val = replacement.val;
            node = replacement;
        }

        Node parent = node.parent;
        delete(node);
        rebalance(parent);
        return true;
    }

    // 删除结点p并用它的子结点代替它，结点p至多只能有1个子结点
    private void delete(Node node) {
        if (node.left != null && node.right != null) {
            return;
            // throw new Exception("Node has two children");
        }
        Node child = node.left != null ? node.left : node.right;
        if (child != null) {
            child.parent = node.parent;
        }
        if (node == root) {
            root = child;
        } else {
            Node parent = node.parent;
            if (node == parent.left) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        }
        node.parent = node;
    }

    // 在以node为根结点的子树中搜索值为v的结点，如果没有值为v的结点，则返回值为v的结点应该在的位置的父结点
    private Node subtreeSearch(Node node, int v) {
        if (node.val < v && node.right != null) {
            return subtreeSearch(node.right, v);
        } else if (node.val > v && node.left != null) {
            return subtreeSearch(node.left, v);
        } else {
            return node;
        }
    }

    // 重新计算node结点的高度和元素数
    private void recompute(Node node) {
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        node.size = 1 + getSize(node.left) + getSize(node.right);
    }

    // 从node结点开始（含node结点）逐个向上重新平衡二叉树，并更新结点高度和元素数
    private void rebalance(Node node) {
        while (node != null) {
            int oldHeight = node.height, oldSize = node.size;
            if (!isBalanced(node)) {
                node = restructure(tallGrandchild(node));
                recompute(node.left);
                recompute(node.right);
            }
            recompute(node);
            if (node.height == oldHeight && node.size == oldSize) {
                node = null; // 如果结点高度和元素数都没有变化则不需要再继续向上调整
            } else {
                node = node.parent;
            }
        }
    }

    // 判断node结点是否平衡
    private boolean isBalanced(Node node) {
        return Math.abs(getHeight(node.left) - getHeight(node.right)) <= 1;
    }

    // 获取node结点更高的子树
    private Node tallChild(Node node) {
        if (getHeight(node.left) > getHeight(node.right)) {
            return node.left;
        } else {
            return node.right;
        }
    }

    // 获取node结点更高的子树中的更高的子树
    private Node tallGrandchild(Node node) {
        Node child = tallChild(node);
        return tallChild(child);
    }

    // 重新连接父结点和子结点（子结点允许为空）
    private static void relink(Node parent, Node child, boolean isLeft) {
        if (isLeft) {
            parent.left = child;
        } else {
            parent.right = child;
        }
        if (child != null) {
            child.parent = parent;
        }
    }

    // 旋转操作
    private void rotate(Node node) {
        Node parent = node.parent;
        Node grandparent = parent.parent;
        if (grandparent == null) {
            root = node;
            node.parent = null;
        } else {
            relink(grandparent, node, parent == grandparent.left);
        }

        if (node == parent.left) {
            relink(parent, node.right, true);
            relink(node, parent, false);
        } else {
            relink(parent, node.left, false);
            relink(node, parent, true);
        }
    }

    // trinode操作
    private Node restructure(Node node) {
        Node parent = node.parent;
        Node grandparent = parent.parent;

        if ((node == parent.right) == (parent == grandparent.right)) { // 处理需要一次旋转的情况
            rotate(parent);
            return parent;
        } else { // 处理需要两次旋转的情况：第1次旋转后即成为需要一次旋转的情况
            rotate(node);
            rotate(node);
            return node;
        }
    }

    // 返回以node为根结点的子树的第1个元素
    private static Node subtreeFirst(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // 返回以node为根结点的子树的最后1个元素
    private static Node subtreeLast(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    // 获取以node为根结点的子树的高度
    private static int getHeight(Node node) {
        return node != null ? node.height : 0;
    }

    // 获取以node为根结点的子树的结点数
    private static int getSize(Node node) {
        return node != null ? node.size : 0;
    }
}


*/

/*
public class demo {
    static class Father{
        public int money = 1;

        public Father(){
            money = 2;
            show();
        }

        public void test() {
            System.out.println("test: $" + money);
        }

        public void show() {
            System.out.println("father: $" + money);
        }
    }

    static class Son extends Father{
        public int money = 3;

        public Son() {
            money = 4;
            show();
        }

//        public void test() {
//            System.out.println("test: $" + money);
//        }

        public void show() {
            test();
            System.out.println("son: $" + money);
        }
    }

    public static void main(String[] args) {
        Father gay = new Son();
        System.out.println("gay: $" + gay.money);
//        new Father().test();
    }
}
*/

/*
import java.util.HashMap;
import java.util.Map;

public class demo {
    public static void recur(int [] tem, int left, int right, int n) {
        if (right <= left) return ;
        int mid = tem[left];
        int i = left, j = right;
        while (i < j) {
            while (i < j && tem[j] >= mid) {
                j --;
            }
            if (i < j && tem[j] < mid) {
                tem[i++] = tem[j];
            }
            while (i < j && tem[i] <= mid) {
                i ++;
            }
            if (i < j && tem[i] > mid) {
                tem[j--] = tem[i];
            }
        }
        tem[i] = mid;
        if (i == n) return;
        else if (i > n)
            recur(tem, 0, i-1, n);
        else
            recur(tem, i + 1, right, n);
        return ;
    }
//    public static void quickSort(int [] tem) {
//        recur(tem, 0, tem.length - 1);
//    }
    public static void nthEle(int [] tem, int n) {
        recur(tem, 0, tem.length - 1, n-1);
    }


    public static void main (String [] args) {
//        int [] tem = {5,6,9,87,4,2,3,6,5,6,9,5,6,3};
////        quickSort(tem);
//        nthEle(tem, 12);
//        for (int item :tem)
//            System.out.print(item + " ");

        int res = new Solution().arrangeCoins(8);
        System.out.print(res);
    }
}


class Solution {
    public int arrangeCoins(int n) {
        int l = 1, r = n, m;
        while (l < r) {
            m = (l + r) / 2;
            int res = (m + 1) * m / 2;
            if (res <= n) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        return l;
    }
}

*/



class LRUCache {

    int capacity;
    private Map<Integer, Integer> tem;
    private LinkedList<Integer> order;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        tem = new HashMap<>();
        order = new LinkedList<>();
    }

    public int get(int key) {
        if (tem.containsKey(key)) {
            int k = order.remove(key);
            order.addLast(k);
            return tem.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if (tem.size() == capacity) {
            int k = order.removeFirst();
            tem.remove(k);
        }
        order.addLast(key);
        tem.put(key, value);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

// TODO 多线程锁的示例
class MyThread extends Thread {

    public static int q = 0;

    private void recur () {
        if (q >= 200) return;
        q ++;
        recur();
    }

    @Override
    public void run() {
        System.out.println("Mythread -" + currentThread().getId());
        recur();
    }
}

class myThread2 implements Runnable {
    @Override
    public void run() {

    }
}


class A {
//    int []tem = new int[10000];
    B b;
}
class B {
//    int []tem = new int[10000];
    A a;
}


public class demo {
    public static void main (String[]args) {
        Object [] tem = new Object[5];
        tem[0] = 58;
        tem[1] = '5';
        for (int i = 0; i < 5; i ++)
            System.out.println(tem[i]);
//
//        Object [] t = new String[12];
//        t[0] = 1;

        //TODO 借此总结这些容器常用增删改的接口，只总结最常用的就好
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new LinkedList<>();
        List<Integer> c = new Vector<>();
        List<Integer> d = new Stack<>();
        Queue<Integer> e = new PriorityQueue<>();
        Map<Integer, Integer> f = new HashMap<>();
        Map<Integer, Integer> f1 = new LinkedHashMap<>();
        Map<Integer, Integer> g = new TreeMap<>();
        Set<Integer> h = new HashSet<>();
        Set<Integer> h1 = new LinkedHashSet<>();
        Set<Integer> i = new TreeSet<>();
        String j = new String("Asfdsf");


        // TODO 多线程锁的示例
        Lock l = new ReentrantLock();
        for (int it = 0; it < 10; it ++)
            new MyThread().start();

        System.out.println(MyThread.q);

        for (int z = 0; z < 10; z ++) {
            A m1 = new A();
            B m2 = new B();
            m1.b = m2;
            m2.a = m1;
            m1 = null;
            m2 = null;
        }



    }
//    public <T>T gen(T m) {
//        T a = m;
//        return a;
//    }
}


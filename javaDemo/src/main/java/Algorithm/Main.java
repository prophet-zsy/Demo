package Algorithm;


import java.io.IOException;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();


        System.out.printf("%d,%f", 10, 12.34);
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000 + "ms");
    }
}



//class Solution {
//    int otherHeight = 0;
//    int originDepth = 0;
//    int originHeight = 0;
//    boolean findOrigin = false;
//    public int amountOfTime(TreeNode root, int start) {
//        recur(root, start, 0);
//        System.out.println(originDepth + ", " + originHeight + ", " + otherHeight);
//        return Math.max(originDepth + otherHeight, originHeight);
//    }
//    public void recur(TreeNode root, int start, int height) {
//        if (findOrigin) {
//            originHeight = Math.max(originDepth, height);
//        } else {
//            otherHeight = Math.max(otherHeight, height - 1);
//        }
//        if (root.val == start) {
//            originDepth = height;
//            findOrigin = true;
//            if (root.left != null) recur(root.left, start, 1);
//            if (root.right != null) recur(root.right, start, 1);
//            findOrigin = false;
//        } else {
//            if (root.left != null) recur(root.left, start, height + 1);
//            if (root.right != null) recur(root.right, start, height + 1);
//        }
//    }
//}
//
//
//class Solution {
//    public String shiftingLetters(String s, int[][] shifts) {
//        int len = s.length();
//        int[] arr = new int[len];
//        for (int i = 0; i < len; i++) {
//            arr[i] = s.charAt(i) - 'a';
//        }
//        for (int i = 0; i < shifts.length; i++) {
//            int[] shift = shifts[i];
//            int start = shift[0];
//            int end = shift[1];
//            int direction = shift[2] == 1 ? 1 : -1;
//            for (int j = start; j <= end; j++) {
//                arr[j] += direction + 26;
//                arr[j] %= 26;
//            }
//        }
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < len; i++) {
//            stringBuilder.append((char) ('a' + arr[i]));
//        }
//        return stringBuilder.toString();
//    }
//}
//
//
//
//
//class Solution {
//    int[] memo = new int[10];
//    public int countNumbersWithUniqueDigits(int n) {
//        boolean[] used = new boolean[10];
//        recur(0, n, used, true);
//        return memo[n];
//    }
//    public void recur(int id, int n, boolean[] used, boolean alwaysZero) {
//        if (memo[n-id] > 0) return;
//        if (id == n) {
//            memo[n - id] = 1;
//            return;
//        }
//        int cnt = 0;
//        for (int i = 0; i <= 9; i++) {
//            if (i == 0 &&  alwaysZero || !used[i]) {
//                if (memo[n-id-1] > 0) {
//                    cnt += memo[n - id - 1];
//                    continue;
//                }
//                used[i] = true;
//                recur(id + 1, n, used, alwaysZero && i == 0);
//                used[i] = false;
//            }
//        }
//        memo[n-id] = cnt;
//    }
//}
//
//
//
//class Solution {
//    public int countSpecialNumbers(int n) {
//        Stack<Integer> stack = new Stack<>();
//        while (n > 0) {
//            stack.push(n % 10);
//            n /= 10;
//        }
//        int len = stack.size();
//        return recur(stack, len - 1, 10);
//    }
//    public int recur(Stack<Integer> stack, int resBit, int resChose) {
//        if (stack.size() == 0) return 0;
//        int num = stack.pop();
//        int multi = 1;
//        for (int i = 0; i < resBit; i++) {
//            multi *= (resChose - 1 - i);
//            multi /= (i + 1);
//        }
//        int res = (num - 1) * multi;
//        System.out.println(res);
//        return res + recur(stack, resBit - 1, resChose - 1);
//    }
//}



class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


//class Solution {
//    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
//        long cnt = k1 + k2;
//        int n = nums1.length;
//        List<Integer> list1 = new ArrayList<>();
//        int maxVal = 0;
//        for (int i = 0; i < n; i++) {
//            int sub = Math.abs(nums1[i] - nums2[i]);
//            list1.add(sub);
//            maxVal = Math.max(maxVal, sub);
//        }
//        int l = -1, r = maxVal + 1, m;
//        System.out.println("cnt : " + cnt);
//        while (l + 1 < r) {
//            m = (l + r) / 2;
//            long tem = 0;
//            for (int i = 0; i < list1.size(); i++) {
//                int item = list1.get(i);
//                tem += item < m ? 0 : item - m;
//            }
//            System.out.println("m :  " + m);
//            if (tem <= cnt) r = m;
//            else l = m;
//        }
//        System.out.println("r : " + r);
//        long res = 0;
//        for (int i = 0; i < list1.size(); i++) {
//            System.out.println(list1.get(i));
//            res += Math.pow(Math.min(list1.get(i), r), 2);
//        }
//        return res;
//    }
//}
//



class Solution {
    public int countPaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] cnt = new int[m][n];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cnt[i][j] = 1;
                if (isValid(i-1, j, m, n) && grid[i-1][j] > grid[i][j]) queue.add(new int[]{i-1, j, i, j});
                if (isValid(i+1, j, m, n) && grid[i+1][j] > grid[i][j]) queue.add(new int[]{i+1, j, i, j});
                if (isValid(i, j-1, m, n) && grid[i][j-1] > grid[i][j]) queue.add(new int[]{i, j-1, i, j});
                if (isValid(i, j+1, m, n) && grid[i][j+1] > grid[i][j]) queue.add(new int[]{i, j+1, i, j});
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            Queue<int[]> ops = new LinkedList<>();
            for (int q = 0; q < size; q++) {
                System.out.println(queue);
                int[] item = queue.poll();
                System.out.println(Arrays.toString(item));
                int i = item[0];
                int j = item[1];
                int x = item[2];
                int y = item[3];

                ops.add(new int[]{i, j, cnt[i][j]+cnt[x][y]});
                if (isValid(i-1, j, m, n) && grid[i-1][j] > grid[i][j]) queue.add(new int[]{i-1, j, i, j});
                if (isValid(i+1, j, m, n) && grid[i+1][j] > grid[i][j]) queue.add(new int[]{i+1, j, i, j});
                if (isValid(i, j-1, m, n) && grid[i][j-1] > grid[i][j]) queue.add(new int[]{i, j-1, i, j});
                if (isValid(i, j+1, m, n) && grid[i][j+1] > grid[i][j]) queue.add(new int[]{i, j+1, i, j});
                for (int k = 0; k < m; k++) {
                    System.out.println(Arrays.toString(cnt[k]));
                }
                System.out.println();
            }
            while (!ops.isEmpty()) {
                int[] op = ops.poll();
                int i = op[0];
                int j = op[1];
                int val = op[2];
                cnt[i][j] = val % 1000000007;
            }
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res += cnt[i][j];
                res %= 1000000007;
            }
        }
        return res;
    }
    public boolean isValid(int i, int j, int m, int n) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }
}


//class Solution {
//    public int minimumNumbers(int num, int k) {
//        if (num == 0) return 0;
//        int[][] dp = new int[num + 1][301];
//        for (int i = 1; i < num + 1; i++) {
//            for (int j = 0; j < 301; j++) {
//                int capacity = i;
//                int item = j * 10 + k;
//                if (j == 0) {
//                    if (capacity % item == 0) {
//                        dp[i][j] = capacity / item;
//                    } else {
//                        dp[i][j] = -1;
//                    }
//                } else {
//                    if (i >= item) {
//                        dp[i][j] = Math.min(dp[i][j-1], dp[i-item][j-1] + 1);
//                    } else {
//                        dp[i][j] = dp[i][j-1];
//                    }
//                }
//            }
//        }
//
//    }
//}


//class Solution {
//    public int longestSubsequence(String s, int k) {
//        int len = s.length();
//        int[] dp = new int[len];
//        for (int i = 0; i < len; i++) {
//
//        }
//    }
//}
//
//
//
//class Solution {
//    public int totalSteps(int[] nums) {
//        int len = nums.length;
//        int temIdx = 0;
//        List<List<Integer>> idxs = new ArrayList<>();
//        for (int i = 1; i < len; i++) {
//            if (nums[i] >= nums[temIdx]) {
//                if (i > temIdx + 1) {
//                    List<Integer> item = new ArrayList<>();
//                    item.add(temIdx);
//                    item.add(i);
//                    idxs.add(item);
//                }
//                temIdx = i;
//            }
//        }
//        if (len > temIdx + 1) {
//            List<Integer> item = new ArrayList<>();
//            item.add(temIdx);
//            item.add(len);
//            idxs.add(item);
//        }
//        System.out.println(idxs);
//        int res = 0;
//        int[] dp = new int[len];
//        for (int i = 0; i < idxs.size(); i++) {
//            int a = idxs.get(i).get(0);
//            int b = idxs.get(i).get(1);
////            找a-b区间的最长上升子序列的长度
//            int maxLen = 0;
//            for (int j = a + 1; j < b; j++) {
//                int tem = 0;
//                for (int k = a + 1; k < j; k++) {
//                    if (nums[j] >= nums[k]) {
//                        tem = Math.max(tem, dp[k]);
//                    }
//                }
//                dp[j] = tem + 1;
//                maxLen = Math.max(maxLen, dp[j]);
//            }
//            res = Math.max(res, maxLen);
//        }
//        System.out.println(Arrays.toString(dp));
//        return res;
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//






class BookMyShow {

    int[] show;
    int colMax;
    int rowStart;
    int[] segmentTree;  // 线段树，维护row区间 剩余座位的数量 的最大值

    public BookMyShow(int n, int m) {
        show = new int[n];
        colMax = m;
        rowStart = 0;
        segmentTree = new int[n * 5];   // 叶子数为n，总结点数量 翻五倍 肯定够了
        buildTree(0, 0, n-1);
    }

    public int buildTree(int node, int start, int end) {  // 返回剩余空间的最大值
        if (start == end) {
            segmentTree[node] = colMax - show[start];
            return colMax - show[start];
        }
        int mid = (start + end) / 2;
        int leftMax = buildTree(node * 2 + 1, start, mid);
        int rightMax = buildTree(node * 2 + 2, mid + 1, end);
        int curRes = Math.max(leftMax, rightMax);
        segmentTree[node] = curRes;
        return curRes;
    }

    public boolean queryMax(int node, int selfStart, int selfEnd, int start, int end, int k) {
        if (end < selfStart || start > selfEnd) return false;
        if (start <= selfStart && selfEnd <= end) return segmentTree[node] >= k;
        int mid = (selfStart + selfEnd) / 2;
        return queryMax(node * 2 + 1, selfStart, mid, start, end, k) || queryMax(node * 2 + 2, mid + 1, selfEnd, start, end, k);
    }
//    todo 写出对应的 查询和更新总和的函数
    public boolean querySum(int node, int selfStart, int selfEnd, int start, int end, int k) {
        if (end < selfStart || start > selfEnd) return false;
        if (start <= selfStart && selfEnd <= end) return segmentTree[node] >= k;
        int mid = (selfStart + selfEnd) / 2;
        return queryMax(node * 2 + 1, selfStart, mid, start, end, k) || queryMax(node * 2 + 2, mid + 1, selfEnd, start, end, k);
    }

    public void update(int node, int selfStart, int selfEnd, int targetNode, int val) {
        if (selfStart == selfEnd) {  // 这时一定等于targetNode
            segmentTree[node] = val;
            return ;
        }
        int leftNode = node * 2 + 1;
        int rightNode = node * 2 + 2;
        int mid = (selfStart + selfEnd) / 2;
        if (targetNode <= mid) update(leftNode, selfStart, mid, targetNode, val);
        else update(rightNode, mid + 1, selfEnd, targetNode, val);
        segmentTree[node] = Math.max(segmentTree[leftNode], segmentTree[rightNode]); // 更新
    }

    public int[] gather(int k, int maxRow) {
//        System.out.println(Arrays.toString(show));
//        System.out.println(Arrays.toString(segmentTree));
//        区间查询，线段树
//        二分查找，找出 最左侧能够安排的row值
        int l = rowStart - 1, r = maxRow + 1, m;
//        System.out.println("l = " + l + " , r = " + r);
        while (l + 1 < r) {
            m = (l + r) / 2;
//            System.out.println("m = " + m + " , " + query(0, 0, show.length - 1, 0, m, k));
            if (queryMax(0, 0, show.length - 1, rowStart, m, k)) r = m;
            else l = m;
        }
//        System.out.println(r);
        if (r == maxRow + 1) return new int[]{};  // 没找到
        int start = show[r];
        show[r] += k;
        update(0, 0, show.length - 1, r, colMax - show[r]);  // 更新线段树
        return new int[]{r, start};
    }

    public boolean scatter(int k, int maxRow) {
        System.out.println(Arrays.toString(show));
        System.out.println(Arrays.toString(segmentTree));
        List<List<Integer>> forPut = new ArrayList<>();
//        区间查询，线段树
//        二分查找，找出 最左侧能够安排的row值
//        todo 直接找到 能够安排的最左区间
        while (k > 0) {  // 试分配，记录分配历史
            int l = rowStart - 1, r = maxRow + 1, m;
            while (l + 1 < r) {
                m = (l + r) / 2;
                if (querySum(0, 0, show.length - 1,rowStart, m, 1)) r = m;
                else l = m;
            }
            System.out.println("find r : " + r);
            if (r == maxRow + 1) break;  // 没找到
//            记录更新过的值
            int cnt = Math.min(colMax - show[r], k);
            List<Integer> itemPut = new ArrayList<>();
            itemPut.add(r);
            itemPut.add(show[r]);
            itemPut.add(cnt);
            forPut.add(itemPut);
            show[r] += cnt;
            update(0, 0, show.length - 1, r, colMax - show[r]);  // 更新线段树
            k -= cnt;
        }
        if (k == 0) {  // 人数 k 值用完了，位置 足够分配
            return true;
        } else {  // 不够分配， 恢复 座位状态
            for (int i = 0; i < forPut.size(); i++) {
                int row = forPut.get(i).get(0);
                int start = forPut.get(i).get(1);
                int cnt = forPut.get(i).get(2);
                show[row] = start;  // 恢复 row 的座位
                update(0, 0, show.length - 1, row, colMax - show[row]);  // 更新线段树
                if (show[row] == colMax) rowStart = row + 1;
            }
            return false;
        }
    }
}


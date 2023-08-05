// 本题为考试单行多行输入输出规范示例，无需提交，不计分。
import java.util.Scanner;
public class hello {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            int n = in.nextInt();
            int [][] pan = new int[n][n];
            for (int i = 0; i < n; i ++) {
                for (int j = 0; j < n; j ++) {
                    pan[i][j] = in.nextInt();
                }
            }
            int [] scoreUp = new int[2*n-1];
            int [] scoreDown = new int[2*n-1];
            int x = 0, y = n - 1;
            for (int i = 0; i < n; i ++) {
                scoreUp[i] = cntUp(x, y, n, pan);
                y --;
            }
            x = 1; y = 0;
            for (int i = n; i < 2*n-1; i ++) {
                scoreUp[i] = cntUp(x, y, n, pan);
                x ++;
            }

            x = 0; y = 0;
            for (int i = 0; i < n; i ++) {
                scoreDown[i] = cntDown(x,y,n,pan);
            }
            x = 1; y = n-1;
            for (int i = n; i < 2*n-1; i ++) {
                scoreDown[i] = cntDown(x,y,n,pan);
                x ++;
            }

            int [][] score = new int[n][n];
            for (int i = 0; i < n; i ++) {
                for (int j = 0; j < n; j ++) {
                    if (i > j) {
                        score[i][j] = scoreUp[i-j];
                    }

                }
            }



            // 找两个条件下最大值
            int resA = 0, resB = 0;
            for (int i = 0; i < n; i ++) {
                for (int j = 0; j < n; j ++) {
                    // System.out.println(score[i][j]);
                    if (i + j % 2 == 0 && score[i][j] > resA) {
                        resA = score[i][j];
                    } else if (i + j % 2 != 0 && score[i][j] > resB) {
                        resB = score[i][j];
                    }
                }
            }
            System.out.println(resA + resB);
        }
    }

    public static int cntUp(int x, int y, int n, int[][] pan) {
        //左下往右上，加和
        int res = 0;
        int a = x - n, b = y - n;
        while(a < n && b < n) {
            if (a >= 0 && b >= 0) {
                res += pan[a][b];
            }
            a ++; b ++;
        }
        return res;
    }

    public static int cntDown(int x, int y, int n, int[][] pan) {
        // 从左上往右下走，加和
        int res = 0;
        int a = x - n, b = y + n;
        while(a < n && b >= 0) {
            if (a >= 0 && b < n) {
                res += pan[a][b];
            }
            a ++; b --;
        }
        return res;
    }


}
/*
9/29拼多多笔试  两个小时（多练习这种复杂的题目）
1、长度为n的钢筋，切分为3种长度的元件，分别为A、B、C；
要求按如下顺序优先级逐渐降低：原料全部用完、元件数量最多、先保证A再保证B再保证C
输入四个整数：N，A，B，C；范围1-2000
输出ABC三类元件各自的数量；-1表示无解
示例：
5 2 2 3
1 0 1
或
11 3 9 10
-1

2、N*N的棋盘，每个坐标上均有对应的积分Sij，在棋盘上放置两枚棋子，可以获得对应积分路径上的所有积分，
积分路径定义为：以该位置为交点的对角线上的所有坐标的位置
现想放置两枚棋子，在它们对应的积分路径不存在相同坐标的情况下，获取的积分总和最大。
输入一个整数N表示棋盘大小，接下来是n行n列的二维整数数组，第i行j列表示对应位置的积分值，n为2-1000，积分为0-1000
输出积分总和的最大值
示例：
入
2
1 2
3 4
出
10
入
4
1 2 1 1
2 1 1 3
1 1 1 0
1 0 0 1
出
13

3、修路，将路面统一到同一水平高度
道路可以看作N个连续的单位线段组成（编号1-n），经勘测得知道路位置i的高度为Hi
修路机每次可以将一段连续道路增加1或减少1
同时修路机每次使用必须经过位于M的监测点，以接受指令
求最少需要使用多少次修路机，才能把道路的所有位置统一到水平0的位置
输入N为道路总长，M为监测点位置（N为1-20000，M为1-N）
第二行为N个整数，道路i的高度，为-1000-1000
示例：
入
3 2
-1 2 1
出
4

入
10 3
6 0 3 3 2 10 3 7 4 9
出
37

*/



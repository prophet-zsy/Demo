//
// Created by SM2887 on 2023/1/21.
//


#include "bits/stdc++.h"
using namespace std;

//class Solution {
//public:
//    int minSideJumps(vector<int>& obstacles) {
//
//    }
//};


class Solution {
public:
    static int kk;
    static bool compareArr(vector<int> a, vector<int> b) {
        return a[kk] > b[kk];
    }
    vector<vector<int>> sortTheStudents(vector<vector<int>>& score, int k) {
        kk = k;
        sort(score.begin(), score.end(), compareArr);
        return score;
    }
};
int Solution::kk = 0;   // 私有的静态变量如何在类外初始化


int main() {
    long start = clock();



    long end = clock();
    cout << "used time : " << (end - start) * 1000 / CLOCKS_PER_SEC << " ms" << endl;

    return 0;
}

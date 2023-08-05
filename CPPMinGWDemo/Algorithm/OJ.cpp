//
// Created by SM2887 on 2022/12/31.
//

#include "bits/stdc++.h"

using namespace std;

int QuickSort(int arr[]) {

}

// todo 数组引用 作为 函数参数
//template<class T>
void PrintArr(int( & arr) [7], int size) {  // 这种带括号的写法
    int len = sizeof(arr) / sizeof(arr[0]);
    cout << sizeof(arr) << ", " << sizeof(arr[0]) << endl;
    cout << "Arr {";
    for (int i = 0; i < len; ++i) {
        cout << arr[i] << ", ";
    }
    cout << "}" << endl;
}



//[6284. 使字符串总不同字符的数目相等] https://leetcode.cn/contest/weekly-contest-327/problems/make-number-of-distinct-characters-equal/
class Solution {
public:
    bool isItPossible(string word1, string word2) {
        int len1 = word1.size();
        int len2 = word2.size();
        int wc1[26], wc2[26];  // todo 弄清楚数组的栈上初始化和堆上的初始化
        for (int i = 0; i < 26; ++i) {
            wc1[i] = 0;
            wc2[i] = 0;
        }
        for (int i = 0; i < len1; ++i) {
            wc1[word1[i] - 'a'] ++;
        }
        for (int i = 0; i < len2; ++i) {
            wc2[word2[i] - 'a'] ++;
        }

//        看两个字符串分别有多少种字符
        int cnt1 = 0;
        for (int i = 0; i < 26; ++i) {
            if (wc1[i] > 0) cnt1 ++;
        }
        int cnt2 = 0;
        for (int i = 0; i < 26; ++i) {
            if (wc2[i] > 0) cnt2 ++;
        }

        bool sameWord = false;
        for (int i = 0; i < 26; ++i) {
            if (wc1[i] > 0 && wc2[i] > 0)
                sameWord = true;
        }
        bool allSame = true;
        for (int i = 0; i < 26; ++i) {
            if (wc1[i] != wc2[i])
                allSame = false;
        }
        if (!sameWord || allSame) { // 两个字符串没有交集 或者没有完全重合对称差集，怎么换都是跟原来一样
            return cnt1 == cnt2;
        } else {  // 有对称差集

        }
    }
};

int main() {
    int num[] = {2, 34, 57, 89, 78, 17, 6};
    QuickSort(num);
    PrintArr(num, 7);



    Solution solution = Solution();
    vector<int> nums = vector<int>();
    for (int i = 0; i < 5; ++i) {
        nums.push_back(10);
    }
    long long res = solution.isItPossible("abc", "bcc");
    cout << res << endl;
}








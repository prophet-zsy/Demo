package Algorithm;

import java.util.Arrays;


/*
这题不错，比较考验代码功底
魔术师现有一摞牌组要用于表演节目，其中每张卡牌都印有唯一的正整数。 定义如下的表演步骤： - 从牌组顶部摸一张卡牌，翻开展示其数字，不再放回牌组。 - 将当前牌组顶部的第一张卡牌放到牌组底部。 - 重复循环上述步骤，直到牌组无牌可摸。 尝试写一个算法预先整理牌组里卡牌的顺序，模拟魔术师高超的洗牌手法； 使他后续能通过上述表演步骤，以数字递增的顺序依次展示牌组中的卡牌，完成这场精彩的表演。
        输入：
        数组代表原始牌组内卡牌顺序，数组第0位代表牌组顶的卡牌
        输出：
        数组代表洗牌后的牌组内卡牌顺序，数组第0位代表牌组顶的卡牌
        输入：
        [17,13,11,2,3,5,7]
        输出：
        [2,13,3,11,5,17,7]
*/

class Solutionn {
    public int[] shuffleDeck(int[] deck) {
        /* Write Your Code Here */
        int len = deck.length;
        int [] res = new int[len];
        Arrays.sort(deck);
        int cnt = 0;  // 标识偶数还是奇数，空一个填一个
        int idx = 0;
        int i = 0;
        while (i < len) {
            if (res[i] == 0) {  //  需要填
                if((cnt & 1) == 0) res[i] = deck[idx++];
                cnt ++;
                if (idx == len) break;
            }
            i = (i + 1) % len;  // 数组内转圈
        }

        return res;
    }
}


public class OJ {
    public static void main(String[] args) {
        int[] deck = new int[]{17,13,11,2,3,5,7};
        long start = System.currentTimeMillis();
        String res = Arrays.toString(new Solutionn().shuffleDeck(deck));
        long end = System.currentTimeMillis();
        System.out.println(res);
        System.out.println((end - start) / 1000 + "ms");
    }
}




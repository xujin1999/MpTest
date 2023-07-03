package com.example.mptest;

import java.util.HashMap;
import java.util.Map;

public class Odd {

    class Solution {
        public int minNumberOfFrogs(String croakOfFrogs) {
            Map<Character, Integer> map = new HashMap<>();
            int n = croakOfFrogs.length();
            int ans = 0;
            //正在鸣叫的青蛙个数
            int runningCount = 0;

            for (int i = 0; i < n; i++) {
                char cur = croakOfFrogs.charAt(i);
                // 为 k 时，不用记录出现数量
                if (cur != 'k') {
                    map.put(cur, map.getOrDefault(cur, 0) + 1);
                }

                if (cur == 'c') {
                    runningCount++;
                    //取每次正在鸣叫的最大值
                    ans = Math.max(ans, runningCount);
                    continue;
                }

                char pre = 'c';
                if (cur == 'o') {
                    pre = 'r';
                } else if (cur == 'a') {
                    pre = 'o';
                } else if (cur == 'k') {
                    pre = 'a';
                }

                //没有符合的情况，直接返回 -1
                int tmpC = map.getOrDefault(pre, 0);
                if (tmpC == 0) {
                    return -1;
                }

                map.put(pre, map.get(pre) - 1);
                if (cur == 'k') {
                    runningCount--;
                }
            }

            //如 mapmap 中还有未完成的croakcroak，则返回 -1
            for (Integer tmpValue : map.values()) {
                if (tmpValue > 0) {
                    return -1;
                }
            }

            return ans;
        }
    }

}

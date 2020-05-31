/*
 * StringLength.java
 * Copyright 2020 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.interview.alg.string;

import javax.sound.midi.SysexMessage;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author cnstonefang@gmail.com
 */
public class StringLength {
    public static void main(String[] args) {
        String str1 = "pwwkew";
        System.out.println(lengthOfLongestSubstring(str1));
        System.out.println(lengthOfLongestSubstringV2(str1));
        System.out.println(lengthOfLongestSubstringV3(str1));
    }


    /**
     * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
     * 遍历字符串,一个指针移动，判断子串
     * 空间复杂度可以降低，set不需要hash表，计数即可
     * 击败12.5%
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        int point = 0;
        Set<Character> set = new HashSet();
        Set<Character> tmp = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (set.size() >= s.length() - i) {
                return set.size();
            }
            tmp.add(s.charAt(i));
            point = i + 1;
            while (point < s.length()) {
                if (!tmp.contains(s.charAt(point))) {
                    tmp.add(s.charAt(point));
                    point++;
                    continue;
                }
                break;
            }
            if (tmp.size() > set.size()) {
                set.clear();
                set.addAll(tmp);
            }
            tmp.clear();
        }
        return set.size();
    }

    /**
     * 优化
     * 18,.17%
     * 没有本质提升
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstringV2(String s) {
        int point = 0;
        int max = 0;
        Set<Character> tmp = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (max >= s.length() - i) {
                return max;
            }
            tmp.add(s.charAt(i));
            point = i + 1;
            while (point < s.length()) {
                if (!tmp.contains(s.charAt(point))) {
                    tmp.add(s.charAt(point));
                    point++;
                    continue;
                }
                break;
            }
            if (tmp.size() > max) {
                max = tmp.size();
            }
            tmp.clear();
        }
        return max;
    }

    /**
     * 滑动窗口的思想
     * 不需要重复计算，前面计算过的没有重复的可以利用起来，移调最前面1位即可
     * O（2N)
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstringV3(String s) {
        int point = 0;
        int max = 0;
        int i = 0;
        Set<Character> tmp = new HashSet<>();
        int length = s.length();
        while (i < length && point < length) {
            if (!tmp.contains(s.charAt(point))) {
                tmp.contains(s.charAt(point++));
                max = Math.max(max, point - i);
            } else {
                tmp.remove(s.charAt(i++));
            }
        }
        return max;
    }

    /**
     * 滑动窗口的优化
     * 存储字符位置的映射，这样不需要遍历两个指针
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstringV4(String s) {
        int n = s.length(), max = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            max = Math.max(max, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return max;
    }

}

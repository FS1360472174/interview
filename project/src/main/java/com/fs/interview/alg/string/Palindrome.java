/*
 * Palindrome.java
 * Copyright 2020 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.interview.alg.string;

/**
 * @author cnstonefang@gmail.com
 */
public class Palindrome {
    public static void main(String[] args) {
        String str1 = "pwwkew";
        String str2 = "madam";
        String str3 = "ac";
        String str4 = "cbbd";
        String str5 = "babad";
        System.out.println(isPalindrome(str1));
        System.out.println(isPalindrome(str2));
        System.out.println(longestPalindrome(str2));
        System.out.println(longestPalindrome(str3));
        System.out.println(longestPalindrome(str4));
        System.out.println(longestPalindromeV2(str2));
        System.out.println(longestPalindromeV2(str3));
        System.out.println(longestPalindromeV2(str4));
        System.out.println(longestPalindromeV2(str5));
    }

    /**
     * 头尾遍历
     * 时间复杂度：O(n)，空间复杂度：O(1)。
     * @param str
     * @return
     */
    public static boolean isPalindrome(String str) {
        int length = str.length();
        int right = 0;
        int left = length - 1;
        while (right < left) {
            if (str.charAt(right) != str.charAt(left)) {
                return false;
            }
            right++;
            left--;
        }
        return true;
    }


    /**
     * 暴力方案，最后超时不通过
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        int length = s.length();
        if (length < 2) {
            return s;
        }
        String res = s.substring(0, 1);
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (isPalindrome(s.substring(i, j + 1))) {
                    res = j + 1 - i > res.length() ? s.substring(i, j + 1) : res;
                }
            }
        }
        return res;
    }

    /**
     *  动态规划
     *  dp[i][j] 表示[i->j]是否是回文，
     *  主要解决不要重复计算的问题
     * @param s
     * @return
     */
    public static String longestPalindromeV2(String s) {
        int length = s.length();
        if (length < 2) {
            return s;
        }
        boolean[][] dp = new boolean[length][length];
        // 初始化
        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }

        int start = 0;
        int maxLen = 1;
        for (int j = 1; j < length; j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) == s.charAt(j) && (dp[i + 1][j - 1] || j - i < 2)) {
                    dp[i][j] = true;
                }
                // 只要 dp[i][j] == true 成立，就表示子串 s[i, j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j]) {
                    int curLen = j - i + 1;
                    if (curLen > maxLen) {
                        maxLen = curLen;
                        start = i;
                    }
                }
            }
        }
        return s.substring(start, start + maxLen);
    }


}

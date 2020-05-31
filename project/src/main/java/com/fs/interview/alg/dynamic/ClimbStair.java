/*
 * ClimbStair.java
 * Copyright 2020 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.interview.alg.dynamic;

/**
 * @author cnstonefang@gmail.com
 * 爬梯子
 */
public class ClimbStair {
    public static void main(String[] args) {
        System.out.println(climbStairs(10));
        System.out.println(climbStairs2(10));
    }

    /**
     * 非递归方式
     * O(n)时间复杂度
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        if (n < 1) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return n;
        }
        int pre[] = new int[]{ 1, 2 };
        int result = pre[0] + pre[1];
        for (int i = 3; i <= n; i++) {
            result = pre[0] + pre[1];
            pre[0] = pre[1];
            pre[1] = result;
        }
        return result;
    }

    /**
     * 使用矩阵算法做
     * 算法复杂度logn
     *           (1,1)
     * (2,1)     (1,0)
     * @param n
     * @return
     */
    public static int climbStairs2(int n) {
        if (n < 1) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return n;
        }
        // m * n 矩阵表达式 int[m][n]
        int[][] pre = { { 1, 1 }, { 1, 0 } };
        int[][] result = matrixPower(pre, n - 2);
        return 2 * result[0][0] + result[1][0];
    }

    public static int[][] matrixPower(int[][] m, int p) {
        int[][] result = new int[m.length][m[0].length];
        for (int i = 0; i < result.length; i++) {
            result[i][i] = 1;
        }

        int[][] tmp = m;
        // 矩阵的N次方 3 = 2^0 + 2^1
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                // 这位数上为1，则加入乘积和
                result = multiMatrix(result, tmp);
            }
            // 2 次方的计算，每个位上都是 2^n
            tmp = multiMatrix(tmp, tmp);
        }
        return result;
    }

    /**
     * 矩阵乘法
     * @param m1
     * @param m2
     * @return
     */
    public static int[][] multiMatrix(int[][] m1, int[][] m2) {
        int[][] result = new int[m1.length][m2[0].length];
        for (int i = 0; i < m2[0].length; i++) {
            for (int j = 0; j < m1.length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    result[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return result;
    }
}

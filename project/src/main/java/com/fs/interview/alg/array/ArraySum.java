/*
 * ArraySum.java
 * Copyright 2020 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.interview.alg.array;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author cnstonefang@gmail.com
 * 数组求和问题
 */
public class ArraySum {
    public static void main(String[] args) {
        int[] nums = new int[]{ -1, 0, 1, 2, -1, -4 };
        System.out.print(threeSum(nums));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // 保卫代码
        if (nums == null || nums.length < 3) {
            return Collections.emptyList();
        }
        // 先排序
        Arrays.sort(nums);


        for (int i = 0; i < nums.length; i++) {
            // 排序后第一个数最小，不能大于sum = 0
            if (nums[i] > 0) {
                return res;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int cur = nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int tmp = cur + nums[left] + nums[right];
                if (tmp == 0) {
                    res.add(Arrays.asList(cur, nums[left], nums[right]));
                    // 一组匹配了，还需要匹配其他组
                    left++;
                    right--;
                    // 如果值重复,去掉重复值
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }


                } else if (tmp < 0) {
                    // 左指针，增加三数之和
                    left++;
                    // 如果值重复,去掉重复值
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                } else {
                    // 右指针，减少三数之和
                    right--;
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                }
            }
        }
        return res;
    }
}

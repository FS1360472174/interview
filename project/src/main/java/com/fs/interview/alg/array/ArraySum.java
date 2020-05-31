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
        //System.out.print(threeSum(nums));
        int[] nums2 = new int[]{ 1, 0, -1, 0, -2, 2 };
        //System.out.println(fourSum(nums2, 0));
        int[] nums3 = new int[]{ 1, -2, -5, -4, -3, 3, 3, 5 };
        System.out.println(fourSum(nums3, -11));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        return threeSumTarget(nums, 0, true);
    }

    /**
     * 四位数求和可以转化为3数求和
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // 保卫代码
        if (nums == null || nums.length < 4) {
            return Collections.emptyList();
        }
        // 先排序
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if (i + 3 < nums.length) {
                int min1 = nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3];
                if (min1 > target) {
                    break;
                }
                int max1 = nums[i] + nums[length - 1] + nums[length - 2] + nums[length - 3];
                if (max1 < target) {
                    continue;
                }
            }
            int[] other = Arrays.copyOfRange(nums, i + 1, nums.length);
            List<List<Integer>> three = threeSumTarget(other,
                    target - nums[i],
                    false);
            if (three.size() > 0) {
                for (int j = 0; j < three.size(); j++) {
                    List<Integer> valid = new ArrayList<>();
                    valid.add(nums[i]);
                    valid.addAll(three.get(j));
                    result.add(valid);
                }
            }
        }
        return result;
    }

    public static List<List<Integer>> threeSumTarget(int[] nums, int target, boolean sort) {
        List<List<Integer>> res = new ArrayList<>();
        // 保卫代码
        if (nums == null || nums.length < 3) {
            return Collections.emptyList();
        }
        if (sort) {
            // 先排序
            Arrays.sort(nums);
        }
        for (int i = 0; i < nums.length; i++) {
            // 排序后第一个数最小，不能大于sum = 0
            if (target > 0 && nums[i] > target) {
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
                if (tmp == target) {
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


                } else if (tmp < target) {
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

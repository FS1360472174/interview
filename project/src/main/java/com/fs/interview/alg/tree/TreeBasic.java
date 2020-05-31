/*
 * TreeBasic.java
 * Copyright 2020 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.interview.alg.tree;

import java.util.List;
import java.util.ArrayList;

/**
 * @author cnstonefang@gmail.com
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/7/trees/47/
 */
public class TreeBasic {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(2);
        treeNode.left = new TreeNode(1);
        treeNode.right = new TreeNode(3);
        treeNode.left.left = new TreeNode(4);
        System.out.println(isValidBST(treeNode));
        System.out.println(isValidBST2(treeNode));

    }

    /**
     * 最大深度
     * 树的深度就是求Max(左子树深度，右子树深度) +1，
     * 求子树深度就是一个递归问题。
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return left < right ? right + 1 : left + 1;
    }

    public static boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean left = isValidBST2(root.left);
        boolean right = isValidBST2(root.right);
        return left && right;
    }

    /**
     * 是否是二叉搜索树
     * @param root
     * @return
     */
    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        List<Integer> values = new ArrayList<Integer>();
        inOrder(root, values);
        for (int i = 0; i < values.size() - 1; i++) {
            if (values.get(i) >= values.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public static void inOrder(TreeNode root, List<Integer> values) {
        if (root == null) {
            return;
        }
        inOrder(root.left, values);
        values.add(root.val);
        inOrder(root.right, values);
    }
}

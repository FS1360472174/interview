/*
 * TreeTraversal.java
 * Copyright 2020 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.interview.alg.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author cnstonefang@gmail.com
 * 树的遍历
 */
public class TreeTraversal {
    // 中序就是左根右
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        inorderTraversal(root, result);
        return result;
    }

    /**
     * 广度搜索
     * @param node
     * @param list
     */
    public void inorderTraversal(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left, list);
        list.add(node.val);
        inorderTraversal(node.right, list);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        List<Integer> level0 = new ArrayList<Integer>();
        level0.add(root.val);
        result.add(level0);
        if (root.left != null) {
            queue.add(root.left);
        }
        if (root.right != null) {
            queue.add(root.right);
        }
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            int count = queue.size();
            while (count-- > 0) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            if (!level.isEmpty()) {
                result.add(level);
            }
        }
        return result;
    }

}

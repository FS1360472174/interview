/*
 * Lru.java
 * Copyright 2020 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.interview.alg;

import sun.misc.LRUCache;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author cnstonefang@gmail.com
 * LRU实现
 * 双向链表+Map
 * 链表保证有序
 * 为什么要双向链表
 * 因为删除某个Node值，单链表可以做到O(1)
 * 但是删除最后一个节点值，做不到
 */
public class Lru {
    public static void main(String[] a) {
        LRUCache1<String,Integer> lru = new LRUCache1<>(3);

    }

    private static class LRUCache2<K,V> {
        private int size;
        private final Map<K,Node> value;
        private Node head;
        private Node tail;
        public LRUCache2(int init) {
            value = new HashMap<>();
            this.size = init;
        }
        public LRUCache2.Node getEntry(K key) {
            if (!value.containsKey(key)) {
                return null;
            }
            LRUCache2.Node node = value.get(key);
            topNode(node);
            return node;
        }

        public void putEntry(K key,V v) {
            // 已经存在
            if (value.containsKey(key)) {
                // 更新值
                Node node = value.get(key);
                node.value = v;

                removeNode(node);
                topNode(node);
            } else {
                //构建一个Node
                Node node = new Node(key, v);

                if (value.size() >= size) {
                    value.remove(key);
                    // 末尾的删除
                    removeNode(tail);
                }
                topNode(node);
                value.put(key, node);
            }
        }

        /**
         * 双向链表的删除
         * https://medium.com/@krishankantsinghal/my-first-blog-on-medium-583159139237
         * @param node
         */
        private void removeNode(Node node) {
            if (node.pre != null) {
                node.pre.next = node.next;
            } else {
                head = node.next;
            }

            if (node.next != null) {
                node.next.pre = node.pre;
            } else {
                tail = node.pre;
            }
        }

        /**
         * 将节点置顶,就是链表的置顶
         * 双向链表的头节点
         * @param node
         */
        public void topNode(Node node) {
            node.next = head;
            node.pre = null;
            if (head == null) {
                head.pre = node;
            }
            if (tail == null) {
                tail = head;
            }
        }
         class Node {
            K key;
            V value;
            Node pre, next;
            Node(K key, V value) {
                this.key = key;
                this.value = value;
                pre = this;
                next = this;
            }
        }
    }
    private static class LRUCache1<K,V> {
        private int size;
        private final Map<K,V> value;
        public LRUCache1(int init) {
            value = new LinkedHashMap<>(init, 0.75f, true);
            this.size = init;
        }
    }
}

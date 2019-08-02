package com.CK;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(1, 1);
        myHashMap.put(2, 3);
        myHashMap.put(3, 9);
        myHashMap.put(4, 5);
        myHashMap.get(3);
        myHashMap.get(4);
        myHashMap.get(2);
        myHashMap.remove(2);
        myHashMap.get(2);

    }
}

class MyHashMap {
    List<Integer> keys;
    List<Integer> values;

    /**
     * Initialize your data structure here.
     */
    public MyHashMap() {
        keys = new ArrayList<>();
        values = new ArrayList<>();
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        if (keys.isEmpty() || !keys.contains(key)) {
            keys.add(key);
            values.add(value);
        } else {
            int i = keys.indexOf(key);
            values.set(i, value);
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        if (keys.isEmpty() || !keys.contains(key)) return -1;
        else return values.get(keys.indexOf(key));
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        if (!keys.isEmpty() && keys.contains(key)) {
            int i = keys.indexOf(key);
            keys.remove(i);
            values.remove(i);
        }
    }
}

//LinkedList
class MyHashMap2 {
    final ListNode[] nodes = new ListNode[10000];

    public void put(int key, int value) {
        int i = idx(key);
        if (nodes[i] == null)
            nodes[i] = new ListNode(-1, -1);
        ListNode prev = find(nodes[i], key);
        if (prev.next == null)
            prev.next = new ListNode(key, value);
        else prev.next.val = value;
    }

    public int get(int key) {
        int i = idx(key);
        if (nodes[i] == null)
            return -1;
        ListNode node = find(nodes[i], key);
        return node.next == null ? -1 : node.next.val;
    }

    public void remove(int key) {
        int i = idx(key);
        if (nodes[i] == null) return;
        ListNode prev = find(nodes[i], key);
        if (prev.next == null) return;
        prev.next = prev.next.next;
    }

    int idx(int key) { return Integer.hashCode(key) % nodes.length;}

    ListNode find(ListNode bucket, int key) {
        ListNode node = bucket, prev = null;
        while (node != null && node.key != key) {
            prev = node;
            node = node.next;
        }
        return prev;
    }

    class ListNode {
        int key, val;
        ListNode next;

        ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
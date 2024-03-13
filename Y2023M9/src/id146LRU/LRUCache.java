package id146LRU;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class LRUCache {
    private Node head, tail;
    private int capacity;
    private Map<Integer, Node> map;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = this.tail = new Node(0,0);
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        changePriority(key);
        return map.get(key).val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            get(key);
            map.get(key).val = value;
            return;
        }


        Node node = new Node(key, value);

        if (map.size() == capacity) {
            // 删除已有节点
            Node pass =  head.next;
            deleteFromHead(pass);
            map.remove(pass.key);
            map.put(key, node);
            addOneAtTail(node);
            return;
        }

        addOneAtTail(node);
        map.put(key, node);
    }

    private void changePriority(int key) {
        Node node = map.get(key);
        deleteFromHead(node);
        addOneAtTail(node);
    }

    private void deleteFromHead(Node node) {
        if (node == tail) {
            tail = node.prev;
        }
        // 删除已有节点
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        node.prev.next = node.next;
    }

    private void addOneAtTail(Node node) {
        node.next = tail.next;
        tail.next = node;
        node.prev = tail;
        tail = node;
    }

    class Node {
        public int key, val;
        public Node next, prev;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        int i = cache.get(2);
        cache.put(2, 6);
        int val = cache.get(1);
        cache.put(1, 5);
        cache.put(1, 2);
        val = cache.get(1);
        int i1 = cache.get(2);

    }
}

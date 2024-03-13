package id1933Operations_on_tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class LockingTree {
    private int[] parent;
    private int[] isLock;
    private List<Integer>[] tree;

    public LockingTree(int[] parent) {
        int n =  parent.length;
        this.parent = parent;
        this.isLock = new int[n];

        // 构造一个可以通过父节点找到子节点的数
        this.tree = new List[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            if (parent[i] == -1) {
                continue;
            }

            tree[parent[i]].add(i);
        }
    }

    public boolean lock(int num, int user) {
        if (isLock[num] != 0) {
            return false;
        }

        isLock[num] = user;
        return true;
    }

    public boolean unlock(int num, int user) {
        if (isLock[num] != user) {
            return false;
        }

        isLock[num] = 0;

        return true;
    }

    public boolean upgrade(int num, int user) {
        if (isLock[num] != 0) {
            return false;
        }

        int temp = num;
        while (parent[temp] != -1) {
            if (isLock[parent[temp]] != 0) {
                return false;
            }

            temp = parent[temp];
        }

        int cnt = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(num);
        while (!q.isEmpty()) {
            Integer cur = q.poll();
            if (isLock[cur] != 0) {
                cnt++;
                isLock[cur] = 0;
            }
            for (Integer child : tree[cur]) {
                q.add(child);
            }
        }

        if (cnt > 0) {
            isLock[num] = user;
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(17 / (double)3 % 2);
    }
}


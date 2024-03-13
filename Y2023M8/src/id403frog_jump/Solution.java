package id403frog_jump;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Solution {
    private Map<Status, Boolean> memo;

    public boolean canCross(int[] stones) {
        memo = new HashMap<>();
        return dfs(stones, 0, 1);
    }

    private boolean dfs(int[] stones, int i, int k) {
        if (i == stones.length - 1) {
            return true;
        }

        Status status = new Status(i, k);
        if (memo.containsKey(status)) {
            return memo.get(status);
        }

        boolean ans = false;
        for (int j = i + 1; j < stones.length; j++) {
            if (stones[j] - stones[i] > k + 1) {
                break;
            }
            if (stones[j] - stones[i] == k - 1) {
                ans |= dfs(stones, j, k - 1);
            }
            if (stones[j] - stones[i] == k) {
                ans |= dfs(stones, j, k);
            }

            if (stones[j] - stones[i] == k + 1) {
                ans |= dfs(stones, j, k + 1);
            }
        }
        memo.put(status, ans);

        return ans;
    }
}

class Status {
    int pos;
    int k;

    public Status(int pos, int k) {
        this.pos = pos;
        this.k = k;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return pos == status.pos && k == status.k;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos, k);
    }
}
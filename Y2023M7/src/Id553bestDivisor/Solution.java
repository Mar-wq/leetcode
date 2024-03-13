package Id553bestDivisor;

public class Solution {
    private Node[][] memo;

    public String optimalDivision(int[] nums) {
        int n = nums.length;
        memo = new Node[n][n];

        return dfs(0, n - 1, nums).maxStr;


    }


    private Node dfs(int i, int j, int[] nums) {
        if (i == j) {
            memo[i][j] = new Node();
            memo[i][j].maxVal = nums[i];
            memo[i][j].minVal = nums[i];
            memo[i][j].minStr = String.valueOf(nums[i]);
            memo[i][j].maxStr = String.valueOf(nums[i]);
        }

        if (memo[i][j] != null) {
            return memo[i][j];
        }
        memo[i][j] = new Node();
        for (int k = i; k < j; k++) {
            Node l = dfs(i, k, nums);
            Node r = dfs(k + 1, j, nums);
            if (memo[i][j].maxVal < l.maxVal / r.minVal) {
                memo[i][j].maxVal = l.maxVal / r.minVal;
                if (k + 1 == j) {
                    memo[i][j].maxStr = l.maxStr + "/" + r.minStr;
                } else {
                    memo[i][j].maxStr = l.maxStr + "/(" + r.minStr + ")";
                }
            }

            if (memo[i][j].minVal > l.minVal / r.maxVal) {
                memo[i][j].minVal = l.minVal / r.maxVal;
                if (k + 1 == j) {
                    memo[i][j].minStr = l.minStr + "/" + r.maxStr;
                } else {
                    memo[i][j].minStr = l.minStr + "/(" + r.maxStr + ")";
                }
            }
        }

        return memo[i][j];
    }
}

class Node {
    double maxVal, minVal;
    String minStr, maxStr;

    public Node() {
        this.minVal = 10000.0;
        this.maxVal = 0;
    }
}

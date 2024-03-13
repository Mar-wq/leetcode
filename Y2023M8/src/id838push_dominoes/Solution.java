package id838push_dominoes;

class Solution {
    public String pushDominoes(String dominoes) {
        int n = dominoes.length();
        int[][] left = new int[n][2];
        int[][] right = new int[n][2];
        
        left[0][0] = dominoes.charAt(0) == 'L' ? 0 : Integer.MAX_VALUE / 2;
        left[0][1] = dominoes.charAt(0) == 'R' ? 0 : Integer.MAX_VALUE / 2;
        right[n - 1][0] = dominoes.charAt(n - 1) == 'L' ? 0 : Integer.MAX_VALUE / 2;
        right[n - 1][1] = dominoes.charAt(n - 1) == 'R' ? 0 : Integer.MAX_VALUE / 2;

        for (int i = 1; i < n; i++) {
            left[i][0] = dominoes.charAt(i) == 'L' ? 0 : Integer.MAX_VALUE / 2;
            left[i][1] = dominoes.charAt(i) == 'R' ? 0 : Integer.MAX_VALUE / 2;
            left[i][0] = Math.min(left[i][0], left[i - 1][0] + 1);
            left[i][1] = Math.min(left[i][1], left[i - 1][1] + 1);
        }

        for (int i = n - 2; i >= 0 ; i--) {
            right[i][0] = dominoes.charAt(i) == 'L' ? 0 : Integer.MAX_VALUE / 2;
            right[i][1] = dominoes.charAt(i) == 'R' ? 0 : Integer.MAX_VALUE / 2;
            right[i][0] = Math.min(right[i][0], right[i + 1][0]);
            right[i][1] = Math.min(right[i][1], right[i + 1][1]);
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (dominoes.charAt(i) != '.') {
                res.append(dominoes.charAt(i));
                continue;
            }
            if (i == n - 1) {
                System.out.println(left[i][0]);
                System.out.println(left[i][1]);
            }
            int l = left[i][1] >= left[i][0]  ? Integer.MAX_VALUE / 2 : left[i][0];
            int r = right[i][0] >= right[i][1] ? Integer.MAX_VALUE / 2 : right[i][1];

            if (i == n - 1) {
                System.out.println(l);
                System.out.println(r);
            }

            if (l == r) {
                res.append('.');
            } else if (l < r) {
                res.append('L');
            } else {
                res.append('R');
            }
        }

        return res.toString();
    }
}

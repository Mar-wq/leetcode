package Id2451diffValueString;

import java.lang.reflect.Array;
import java.util.*;

class Solution {
    /*public String oddString(String[] words) {
        int n = words.length;
        int[][] diff = new int[n][n - 1];


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < words[i].length() - 1; j++) {
                diff[i][j] = words[i].charAt(j + 1) - words[i].charAt(j);
            }
        }
        



        if (compare(diff[0], diff[1])) {
            for (int i = 2; i < n; i++) {
                if (!compare(diff[i], diff[0])) {
                    return words[i];
                }
            }
        } else {
            for (int i = 2; i < n; i++) {
                if (!compare(diff[i], diff[0])) {
                    return words[0];
                } else {
                    return words[1];
                }
            }
        }
        
        return "";
    }

    private boolean compare(int[] a, int[] b) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }

        return true;
    }*/
    public String oddString(String[] words) {
        int[] diff0 = get(words[0]);
        int[] diff1 = get(words[1]);

        if (Arrays.equals(diff0, diff1)) {
            for (int i = 2; i < words.length; i++) {
                if (!Arrays.equals(diff0, get(words[i]))) {
                    return words[i];
                }
            }
        }

        return Arrays.equals(diff0, get(words[2])) ? words[1] : words[0];
    }

    private int[] get(String word) {
        int[] diff = new int[word.length() - 1];

        for (int i = 0; i < word.length() - 1; i++) {
            diff[i] = word.charAt(i + 1) - word.charAt(i);
        }

        return diff;
    }
}
package id833.find_and_replace_instring;

import java.util.Arrays;

class Solution {
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int last = 0, n = indices.length;
        Integer[] sortIndices = new Integer[n];
        for (int i = 0; i < n; i++) {
            sortIndices[i] = i;
        }
        Arrays.sort(sortIndices, (a, b) -> indices[a] - indices[b]);
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < sortIndices.length; i++) {
            int len = sources[sortIndices[i]].length();
            if (indices[sortIndices[i]] + len <= s.length() && s.substring(indices[sortIndices[i]], indices[sortIndices[i]] + len).equals(sources[sortIndices[i]])) {
                res.append(s.substring(last, indices[sortIndices[i]]));
                res.append(targets[sortIndices[i]]);
                last = indices[sortIndices[i]] + len;
            }
        }
        res.append(s.substring(last, s.length()));

        return res.toString();
    }
}
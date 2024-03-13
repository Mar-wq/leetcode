package id2511maximum_enemy_forts_that_can_be_captured;

import java.util.Stack;

public class Solution {
    public int captureForts(int[] forts) {
        Stack<Integer> enemy_pos = new Stack<>();
        Stack<Integer> friend_pos = new Stack<>();
        int res = 0;
        for (int i = 0; i < forts.length; i++) {
            if (forts[i] == 0) {
                continue;
            } else if (forts[i] == 1) {
                if (!enemy_pos.isEmpty()) {
                    res = Math.max(res, i - enemy_pos.peek() - 1);
                }
                enemy_pos.clear();
                friend_pos.push(i);
            } else {
                if (!friend_pos.isEmpty()) {
                    res = Math.max(res, i - friend_pos.peek() - 1);
                }

                friend_pos.clear();
                enemy_pos.push(i);
            }
        }

        return res;
    }
}

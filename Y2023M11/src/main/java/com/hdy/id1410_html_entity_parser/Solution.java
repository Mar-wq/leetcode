package com.hdy.id1410_html_entity_parser;

import java.util.ArrayList;
import java.util.List;

class Solution {
    class EntityChar {
        String entity;
        char character;

        public EntityChar(String entity, char character) {
            this.entity = entity;
            this.character = character;
        }
    }

    List<EntityChar> entityList = new ArrayList<>();

    public String entityParser(String text) {
        entityList.add(new EntityChar("&quot;", '"'));
        entityList.add(new EntityChar("&apos;", '\''));
        entityList.add(new EntityChar("&amp;", '&'));
        entityList.add(new EntityChar("&gt;", '>'));
        entityList.add(new EntityChar("&lt;", '<'));
        entityList.add(new EntityChar("&frasl;", '/'));
        StringBuffer res = new StringBuffer();

        int length = text.length();
        int pos = 0;

        while (pos < length) {
            boolean isEntity = false;
            if (text.charAt(pos) == '&') {
                for (EntityChar entityChar : entityList) {
                    String e = entityChar.entity;
                    char c = entityChar.character;
                    if (pos + e.length() <= text.length() && text.substring(pos, pos + e.length()).equals(e)) {
                        res.append(c);
                        pos += e.length();
                        isEntity = true;
                        break;
                    }
                }
            }

            if (!isEntity) {
                res.append(text.charAt(pos++));
                continue;
            }
        }

        return res.toString();
    }
}

class Solution2 {
    public int rob(int[] nums) {
        int n = nums.length;

        int[] f = new int[n + 1];
        f[0] = 0;
        f[1] = nums[0];

        for (int i = 2; i <= n; i++) {
            f[i] = Math.max(f[i - 2] + nums[i - 1], f[i - 1]);
        }

        return f[n];
    }
}

class Solution1 {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];

        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i >= coin && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
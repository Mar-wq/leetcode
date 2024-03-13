package Id1079print;

import java.util.*;

/*
class Solution {
    public int numTilePossibilities(String tiles) {
        Map<Character, Integer> count = new HashMap<>();

        for (char t : tiles.toCharArray()) {
            count.put(t, count.getOrDefault(t, 0) + 1);
        }

        Set<Character> tile = new HashSet<>(count.keySet());

        // 这里的处理是真的很巧妙  用一个
        return dfs(tiles.length(), count, tile) - 1;
    }

    private int dfs(int i, Map<Character, Integer> count, Set<Character> tile) {
        if (i == 0) {
            return 1;
        }

        int res = 1;

        for (char t : tile) {
            if (count.get(t) > 0) {
                count.put(t, count.get(t) - 1);
                res += dfs(i - 1, count, tile);
                count.put(t, count.get(t) + 1);
            }
        }

        return res;
    }
}*/





/*
class Solution {
    private int n;

    public int numTilePossibilities(String tiles) {
        Map<Character, Integer> map = new HashMap<>();
        n = tiles.length();

        for (int i = 0; i < n; i++) {
            char c = tiles.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        Set<Character> set = new HashSet<>(map.keySet());

        return dfs(0, map, set);
    }


    // 注意  这里是边才表示信息     节点是没有意义的
    private int dfs(int pos , Map<Character, Integer> map, Set<Character> set) {
        if (pos == n) {
            return 1;
        }

        int res = 1;

        for (char c : set) {
            if (map.get(c) <= 0) {
                continue;
            }
            map.put(c, map.get(c) - 1);
            res += dfs(pos + 1, map, set);
            map.put(c, map.get(c) + 1);
        }

        return res;
    }
};*/


/*class Solution {
    private int n;

    public int numTilePossibilities(String tiles) {
        Map<Character, Integer> map = new HashMap<>();
        n = tiles.length();

        for (int i = 0; i < n; i++) {
            char c = tiles.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        Set<Character> set = new HashSet<>(map.keySet());

        int res = 0;
        for(char c : set) {
            map.put(c, map.get(c) - 1);
            res += dfs(1, map, set);
            map.put(c, map.get(c) + 1);
        }

        return res;
    }


    // 注意  这里是边才表示信息     节点是没有意义的
    private int dfs(int pos , Map<Character, Integer> map, Set<Character> set) {
        if (pos == n) {
            return 1;
        }

        int res = 1;

        for (char c : set) {
            if (map.get(c) <= 0) {
                continue;
            }
            map.put(c, map.get(c) - 1);
            res += dfs(pos + 1, map, set);
            map.put(c, map.get(c) + 1);
        }

        return res;
    }
};*/



class Solution {
    private int n;

  /*  public int numTilePossibilities(String tiles) {
        Map<Character, Integer> map = new HashMap<>();
        n = tiles.length();

        for (int i = 0; i < n; i++) {
            char c = tiles.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        Set<Character> set = new HashSet<>(map.keySet());



        int res = 0;
        for(char c : set) {
            map.put(c, map.get(c) - 1);
            res += dfs(1, map, set);
            map.put(c, map.get(c) + 1);
        }

        return res;
    }


    // 注意  这里是边才表示信息     节点是没有意义的
    private int dfs(int pos , Map<Character, Integer> map, Set<Character> set) {
        if (pos == n) {
            return 1;
        }

        int res = 1;

        for (char c : set) {
            if (map.get(c) <= 0) {
                continue;
            }
            map.put(c, map.get(c) - 1);
            res += dfs(pos + 1, map, set);
            map.put(c, map.get(c) + 1);
        }

        return res;
    }*/

    public static void main(String[] args) {
        Map<Character, Integer> map = new HashMap<Character, Integer>() {{
            put('c', 0);
            put('r', 1);
            put('o', 2);
            put('a', 3);
            put('k', 4);
        }};

        Collection<Character> c = new HashSet<>(map.keySet());

        Iterator<Character> iterator = c.iterator();

        while (iterator.hasNext()) {
            Character next = iterator.next();
            if (next == 'o') {
                c.remove('o');
            }
            System.out.println(next);
        }

        System.out.println(c);

        //c.forEach(character -> System.out.println(character));
    }
};

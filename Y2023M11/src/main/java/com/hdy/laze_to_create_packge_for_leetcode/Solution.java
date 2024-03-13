package com.hdy.laze_to_create_packge_for_leetcode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.function.IntFunction;

class Solution1 {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }
}


/*
class ExamRoom {
    TreeSet<int[]> set;
    Map<Integer, Integer> leftSart;
    Map<Integer, Integer> rightEnd;
    int N;


    public ExamRoom(int n) {
        this.set = new TreeSet<>((a, b) ->  {
           if (distance(a) == distance(b)) {
               return b[0] - a[0];
           }

           return distance(a) - distance(b);
        });
        leftSart = new HashMap<>();
        rightEnd = new HashMap<>();
        this.N = n;
        set.add(new int[]{-1, n});
        leftSart.put(-1, n);
        rightEnd.put(n, -1);
    }

    int distance(int[] interv) {
        return interv[1] - interv[0] - 1;
    }

    public int seat() {
        int[] longest = set.last();
        int x = longest[0], y = longest[1];
        int seat;
        if (x == -1) {
            seat = 0;
        } else if (y == N) {
            seat = N - 1;
        } else {
            seat = (x + y) / 2;
        }

        int[] left = new int[]{x, seat};
        int[] right = new int[]{seat, y};

        removeInterval(longest);
        addInterval(left);
        addInterval(right);

        return seat;
    }

    public void leave(int p) {
        int x= leftSart.get(p);
        int y = rightEnd.get(p);
        int[] right = new int[] {p, y};
        int[] left= new int[] {x, p};
        int[] merged = new int[]{x, y};
        removeInterval(right);
        removeInterval(left);
        addInterval(merged);
    }

   private void removeInterval(int[] intv) {
        set.remove(intv);
        leftSart.remove(intv[0]);
        leftSart.remove(intv[1]);
   }

   private void addInterval(int[] intv) {
        set.add(intv);
        leftSart.put(intv[0], intv[1]);
        rightEnd.put(intv[1], intv[0]);
   }
}
*/


class ExamRoom {
    TreeSet<int[]> set;
    Map<Integer, int[]> leftSart;
    Map<Integer, int[]> rightEnd;
    int N;


    public ExamRoom(int n) {
        this.set = new TreeSet<>((a, b) ->  {
            int distA = distance(a);
            int distB = distance(b);
            // 如果长度相同，就比较索引
            if (distA == distB)
                return b[0] - a[0];
            return distA - distB;
        });
        leftSart = new HashMap<>();
        rightEnd = new HashMap<>();
        this.N = n;
        set.add(new int[]{-1, n});
        leftSart.put(-1, new int[]{-1, n});
        rightEnd.put(n, new int[]{-1, n});
    }

    int distance(int[] interv) {
        int x = interv[0];
        int y = interv[1];
        if (x == -1) return y;
        if (y == N) return N - 1 - x;
        // 中点和端点之间的长度
        return (y - x) / 2;
    }

    public int seat() {
        int[] longest = set.last();
        int x = longest[0], y = longest[1];
        int seat;
        if (x == -1) {
            seat = 0;
        } else if (y == N) {
            seat = N - 1;
        } else {
            seat = (x + y) / 2;
        }

        int[] left = new int[]{x, seat};
        int[] right = new int[]{seat, y};

        removeInterval(longest);
        addInterval(left);
        addInterval(right);

        return seat;
    }

    public void leave(int p) {
        int[] left = rightEnd.get(p);
        int[] right= leftSart.get(p);
        int[] merged = new int[]{left[0], right[1]};
        removeInterval(right);
        removeInterval(left);
        addInterval(merged);
    }

    private void removeInterval(int[] intv) {
        set.remove(intv);
        leftSart.remove(intv[0]);
        rightEnd.remove(intv[1]);
    }

    private void addInterval(int[] intv) {
        set.add(intv);
        leftSart.put(intv[0], intv);
        rightEnd.put(intv[1], intv);
    }


    public static void main(String[] args) {
        ExamRoom examRoom = new ExamRoom(10);
        int seat = examRoom.seat();
        seat =  examRoom.seat();
        int seat1 = examRoom.seat();
        int seat2 = examRoom.seat();
        examRoom.leave(4);
        examRoom.leave(0);
    }
}



class Solution2 {
    public boolean isSubsequence(String s, String t) {
        int slow = 0, fast = 0;

        while (slow < s.length() && fast < t.length()) {
            if (s.charAt(slow) == t.charAt(fast)) {
                slow++;
            }

            fast++;
        }


        return slow == s.length();
    }
}



class Solution3 {
    public int numMatchingSubseq(String s, String[] words) {
        List<Integer>[] index = new List[256];
        for (int i = 0; i < 256; i++) {
            index[i] = new ArrayList<Integer>();
        }

        int n = s.length();

        for (int i = 0; i < n; i++) {
            index[s.charAt(i)].add(i);
        }

        int res = 0;
        for (String word : words) {
            int slow = 0, fast = 0;
            for (; slow < word.length(); slow++) {
                int idx =  binarySearch(index[word.charAt(slow)], fast);
                if (idx >= index[word.charAt(slow)].size()) {
                    break;
                }
                fast = index[word.charAt(slow)].get(idx) + 1;
            }

            if (slow == word.length()) {
                res++;
            }
        }

        return res;
    }

    private int binarySearch(List<Integer> index, int target) {
        int res = index.size();
        int l = 0, r = index.size() - 1;

        while (l <= r) {
            int mid = (l + r) / 2;
            if (index.get(mid) >= target) {
                res = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return res;
    }
}



class DifferenceSum {

    public static int calculateDifferenceSum(int[] array) {
        int sum = 0;
        int partialSum = 0;

        for (int i = 0; i < array.length; i++) {
            sum += (i * array[i]) - partialSum;
            partialSum += array[i];
        }

        return sum;
    }

    public static void main(String[] args) {
        int[] array = {1, 3, 5};
        int result = calculateDifferenceSum(array);

        System.out.println("所有两数之差的和为: " + result);
    }
}


class Solution4 {
    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '-' || c == '*' || c == '+') {
                List<Integer> left = diffWaysToCompute(expression.substring(0, i));
                List<Integer> right = diffWaysToCompute(expression.substring(i + 1));

                for (int a : left) {
                    for (int b : right) {
                        if (c == '+') {
                            res.add(a + b);
                        } else if (c =='-') {
                            res.add(a - b);
                        } else {
                            res.add(a * b);
                        }
                    }
                }
            }
        }

        if (res.isEmpty()) {
            res.add(Integer.parseInt(expression));
        }

        return res;
    }
}



class Solution5 {
    class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }


    private int[][] options = new int[][]{{0,0}, {0, 1}, {1, 0}, {1, 1}};

    public boolean isRectangleCover(int[][] rectangles) {
        int X = rectangles[0][0], Y = rectangles[0][1];
        int A = rectangles[0][2], B = rectangles[0][3];

        Set<Point> points = new HashSet<>();
        int area = 0;

        for (int[] rectangle : rectangles) {
            int x = rectangle[0], y = rectangle[1];
            int a = rectangle[2], b = rectangle[3];

            X = Math.min(X, x); Y = Math.min(Y, y);
            A = Math.max(A, a); B = Math.max(B, b);
            int length = b - y, width = a - x;
            area += length * width;

            for (int[] option : options) {
                Point point = new Point(x + option[0] * width, y + option[1] * length);
                if (points.contains(point)) {
                    points.remove(point);
                } else {
                    points.add(point);
                }
            }
        }

        int expertArea = (A - X) * (B - Y);
        if (expertArea != area) {
            return false;
        }

        if (points.size() != 4) {
            return false;
        }

        int width = A - X, length = B - Y;

        for (int[] option : options) {
            Point point = new Point(X + width * option[0], Y + length * option[1]);

            if (!points.contains(point)) {
                return false;
            }
        }


        return true;
    }
}





/*class Solution {
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>(), need = new HashMap<>();
        

        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        for (int num : nums) {
            if (freq.get(num) == 0) {
                continue;
            }

            if (need.getOrDefault(num, 0) > 0 && freq.get(num) > 0) {
                need.put(num , need.getOrDefault(num, 0) - 1);
                freq.put(num, freq.get(num) - 1);
                need.put(num + 1 , need.getOrDefault(num + 1, 0) + 1);
            } else if (need.getOrDefault(num, 0) == 0 && freq.getOrDefault(num, 0) > 0 && freq.getOrDefault(num + 1, 0) > 0 && freq.getOrDefault(num + 2, 0) > 0) {
                need.put(num + 3, need.getOrDefault(num + 3, 0) + 1);
                freq.put(num, freq.get(num) - 1);
                freq.put(num + 1, freq.get(num + 1) - 1);
                freq.put(num + 2, freq.get(num + 2) - 1);
            } else {
                return false;
            }
        }

        return true;
    }
}*/


class Solution6 {
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, List<List<Integer>>> need = new HashMap<>();


        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        for (int num : nums) {
            if (freq.get(num) == 0) {
                continue;
            }

            if (need.containsKey(num)  && !need.get(num).isEmpty()) {

                List<Integer> seq = need.get(num).remove(need.get(num).size() - 1);
                seq.add(num);
                freq.put(num, freq.get(num) - 1);
                if (!need.containsKey(num + 1)) {
                    need.put(num + 1, new ArrayList<>());
                }

                need.get(num + 1).add(seq);
            } else if (freq.get(num) > 0 && freq.getOrDefault(num + 1, 0) > 0 && freq.getOrDefault(num + 1, 0) > 0) {
                freq.put(num, freq.get(num) - 1);
                freq.put(num + 1, freq.get(num + 1) - 1);
                freq.put(num + 2, freq.get(num + 2) - 1);
                List<Integer> seq = new ArrayList<>();
                seq.add(num);
                seq.add(num + 1);
                seq.add(num + 2);

                if (!need.containsKey(num + 3)) {
                    need.put(num + 3, new ArrayList<>());
                }

                need.get(num + 3).add(seq);
            } else {
                return false;
            }

            for (List<List<Integer>> allSeq : need.values()) {
                for (List<Integer> seq : allSeq) {
                    if (seq.size() > 3) {
                        System.out.println(seq);
                    }
                }
            }
        }

        return true;
    }
}


class Solution7 {
    public static int[] corpFlightBookings(int[][] bookings, int n) {
        int diff[] = new int[n + 1];
        for (int[] booking : bookings) {
            diff[booking[0]] += booking[2];
            if (booking[1] < n) {
                diff[booking[1] + 1] -= booking[3];
            }
        }

        int ans[] = new int[n];

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += diff[i + 1];
            ans[i] = sum;
        }

        return ans;
    }

}




class Solution8 {
    Map<Integer, Integer> mapping;
    int sz;
    public Solution8(int n, int[] blacklist) {
        mapping = new HashMap<>();
        sz = n - blacklist.length;
        int last = n - 1;
        for (int b : blacklist) {
            mapping.put(b, -1);
        }
        for (int b : blacklist) {
            if (b >= sz) {
                continue;
            }

            while (mapping.containsKey(last)) {
                last--;
            }
            mapping.put(b, last--);
        }
    }

    public int pick() {
        Random random = new Random();
        int index = random.nextInt(sz);
        if (mapping.containsKey(index)) {
            return mapping.get(index);
        }

        return index;
    }


}


class Solution9 {
    Map<Character, Integer> need = new HashMap<>();
    Map<Character, Integer> current = new HashMap<>();

    public String minWindow(String s, String t) {
        int n = s.length();
        int start = 0, end = Integer.MAX_VALUE;

        int left = 0, right = 0;

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        while (right < n) {
            char c = s.charAt(right++);
            if (need.containsKey(c)) {
                current.put(c, current.getOrDefault(c, 0) + 1);
            }

            while (check() && left < right) {
                if (right - left < end - start) {
                    start = left;
                    end = right;
                }

                char c1 = s.charAt(left--);
                if (current.containsKey(c1)) {
                    current.put(c1, current.get(c1) - 1);
                }
            }
        }


        return end == Integer.MAX_VALUE ? "" : s.substring(start, end);
    }

    private boolean check() {
        Iterator<Map.Entry<Character, Integer>> iterator = need.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Character, Integer> entry = iterator.next();
            Character key = entry.getKey();
            Integer value = entry.getValue();
            if (current.getOrDefault(key, current.getOrDefault(key, 0)) < value) {
                return false;
            }
        }

        return true;
    }

}

class BSTIterator {
    List<Integer> midOrder = new ArrayList<>();
    Iterator<Integer> iterator;

    public BSTIterator(TreeNode root) {
        dfs(root);
        iterator = midOrder.iterator();
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        midOrder.add(root.val);
        dfs(root.right);
    }

    public int next() {
        return iterator.next();
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }
}


class Solution11 {
    public int minStoneSum(int[] piles, int k) {
        Queue<Integer> q = new PriorityQueue<>((a, b) -> b - a);

        for (int pile : piles) {
            q.add(pile);
        }

        while (k > 0) {
            int curpile  = q.poll();
            int last = curpile - (curpile / 2);
            q.add(last);
            k--;
        }

       return  q.stream().mapToInt(a -> a).sum();
    }
}


class Solution12 {
    public int[] numberGame(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        for (int i = 0; i < n; i += 2) {
            swap(nums, i, i + 1);
        }

        return nums;
    }

    private void swap(int[] nums, int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }
}




class Solution13 {
    private int mod = 1000000007;
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        List<Integer> hfens = new ArrayList<>();
        List<Integer> vfens = new ArrayList<>();
        hfens.add(1);
        hfens.add(m);
        vfens.add(1);
        vfens.add(n);
        for (int hFence : hFences) {
            hfens.add(hFence);
        }

        for (int vFence : vFences) {
            vfens.add(vFence);
        }


        hfens.sort((a, b) -> a - b);
        vfens.sort((a, b) -> a - b);

        Set<Integer> h = new HashSet<>();
        Set<Integer> v = new HashSet<>();

        for (int i = 0; i < hfens.size(); i++) {
            for (int j = i + 1; j < hfens.size(); j++) {
                h.add(hfens.get(j) - hfens.get(i));
            }
        }

        for (int i = 0; i < vfens.size(); i++) {
            for (int j = i + 1; j < vfens.size(); j++) {
                v.add(vfens.get(j) - vfens.get(i));
            }
        }

        long width = 0;
        for (Integer x : h) {
            if (v.contains(x)) {
                width = Math.max(width, x);
            }
        }

        return width == 0 ? -1 : (int) (width * width % mod);
    }
}









class Solution14 {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        int n = cost.length;
        int m = source.length();
        int[][] G = new int[26][26];
        for (int i = 0; i < 26; i++) {
            Arrays.fill(G[i], Integer.MAX_VALUE);
            G[i][i] = 0;
        }

        for (int i = 0; i < n; i++) {
            int a = original[i] - 'a';
            int b = changed[i] - 'a';
            G[a][b] = Math.min(G[a][b], cost[i]);
        }

        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (G[i][k] != Integer.MAX_VALUE && G[k][j] != Integer.MAX_VALUE) {
                        G[i][j] = Math.min(G[i][j], G[i][k] + G[k][j]);
                    }
                }
            }
        }


        long res = 0;
        for (int i = 0; i < m; i++) {
            int a = source.charAt(i) - 'a';
            int b = target.charAt(i) - 'a';
            if (a == b) {
                continue;
            }
            if (G[a][b] == Integer.MAX_VALUE) {
                return -1;
            }

            res += G[a][b];
        }

        return res;
    }

}




class Solution16 {
    static public int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] mat = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = matrix[i][j] - '0';
            }
        }


        int width = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    continue;
                }
                if (mat[i][j] == 1) {
                    mat[i][j] = Math.min(mat[i - 1][j - 1], Math.min(mat[i - 1][j], mat[i][j - 1])) + 1;
                    width = Math.max(width, mat[i][j]);
                }
            }
        }

        return width * width;
    }

}




class Solution18 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();

        for (int i = m - 2; i >= 0 ; i--) {
            List<Integer> row = triangle.get(i);
            int n = row.size();
            for (int j = 0; j < n; j++) {
                row.set(j, Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)) + row.get(j));
            }
        }

        return triangle.get(0).get(0);
    }
}



class Solution19 {
    public int buyChoco(int[] prices, int money) {
        int smallest = Integer.MAX_VALUE / 2, smaller = Integer.MAX_VALUE / 2;
        for (int price : prices) {
            if (price <= smallest) {
                smaller = smallest;
                smallest = price;
            } else {
                smaller = Math.min(smaller, price);
            }
        }


        int cost = smaller + smallest;
        return money >= cost ? money - cost : money;
    }
}


class Solution20 {
    private int[][] dirs = new int[][]{{1, 0}, {-1, 1}};

    public String convert(String s, int numRows) {
        int n = s.length();
        int[] pos = new int[n];
        Integer[] ch = new Integer [n];
        int dir = 1;
        int x = 1, y = -1;
        for (int i = 0; i < n; i++) {
            ch[i] = i;
            x = x + dirs[dir][0]; y = y + dirs[dir][1];
            pos[i] = x * n + y;
            if (x == 0 || x == numRows - 1) {
                dir = (dir + 1) % 2;
            }
        }

        Arrays.sort(ch, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
               return pos[o1] - pos[o2];
            }
        });

        StringBuilder res = new StringBuilder();
        for (Integer i : ch) {
            res.append(s.charAt(i));
        }

        return res.toString();
    }

}






class Solution21 {
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;

        int area = 0;
        while (l < r) {
            area = Math.max(area, (r - l) * Math.min(height[l], height[r]));
            if (height[l] <= height[r]) {
                l++;
            } else {
                r--;
            }
        }


        return area;
    }
}










class Solution22 {
    public List<String> summaryRanges(int[] nums) {
        int n = nums.length;
        List<String> res = new ArrayList<>();

        if (n == 0) {
            return res;
        }

        int start = nums[0], end = nums[0];

        List<int[]> ranges = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (i == n) {
                ranges.add(new int[]{start, end});
                continue;
            }

            if (nums[i] != end + 1) {
                ranges.add(new int[]{start, end});
                start = end = nums[i];
            } else {
                end = nums[i];
            }
        }

        for (int[] range : ranges) {
            if (range[0] == range[1]) {
                res.add("" + range[0]);
            } else {
                res.add(range[0] + "->" + range[1]);
            }
        }

        return res;
    }
}



class Solution23 {
    public List<String> summaryRanges(int[] nums) {
        // 分组循环
        List<String> ret = new ArrayList<>();
        int n = nums.length, i = 0;

        while (i < n) {
            int low = i;
            i++;
            while (i < n && nums[i] == nums[i - 1] + 1) {
                i++;
            }

            int high = i - 1;

            StringBuilder temp = new StringBuilder(Integer.toString(nums[low]));

            if (low < high) {
                temp.append("->");
                temp.append(Integer.toString(nums[high]));
            }

            ret.add(temp.toString());
        }

        return ret;
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode(ListNode head) {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
class Solution25 {
    public ListNode removeNodes(ListNode head) {
        Stack<Integer> stk = new Stack<>();
        return dfs(head, stk);
    }

    private ListNode dfs(ListNode head, Stack<Integer> stk) {
        if (head == null) {
            return null;
        }

        ListNode nxt = dfs(head.next, stk);
        while (!stk.isEmpty() && stk.peek() <= head.val) {
            stk.pop();
        }

        stk.add(head.val);

        if (stk.size() == 1) {
            head.next = nxt;
            return head;
        } else {
            return nxt;
        }
    }
}





class Solution24 {
    public int maxPower(String s) {
        int n  = s.length();

        int i = 0;

        int ret = 1;
        while (i < n) {
            i++;
            int len = 1;
            while (i < n && s.charAt(i) == s.charAt(i - 1)) {
                len++;
                i++;
            }
            ret = Math.max(ret, len);

        }

        return ret;
    }
}


class Solution26 {
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int n = nums.length;
        int ret = 0;
        for (int i = 0; i < n; ) {
            if (nums[i] > threshold || nums[i] % 2 != 0) {
                continue;
            }

            int j = i + 1;
            while (j < n && nums[j] <= threshold && nums[j] % 2 != nums[j - 1] % 2) {
                j++;
            }

            ret = Math.max(j - i, ret);
            i = j;
        }

        return ret;
    }
}



class Solution27 {
    public long getDescentPeriods(int[] prices) {
        int n = prices.length;
        int res = 0;

        for (int i = 0; i < n; ) {
            int j = i + 1;
            while (j < n && prices[j] + 1 == prices[j - 1]) {
                j++;
            }

            res += (1 + (j - i)) * (j - 1) / 2;
        }

        return res;
    }

    public static void main(String[] args) {

    }

    class A<T>  {

    }

    class B extends A<String> {

    }
}









class Solution28 {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        boolean[] used = new boolean[n];

        while (k > 0) {
            k--;
            int investPos = -1;
            for (int i = 0; i < n; i++) {
                if (!used[i] && capital[i] <= w) {
                    if (investPos == -1) {
                        investPos = i;
                    } else  {
                        if (profits[i] > profits[investPos]) {
                            investPos = i;
                        }
                    }
                }
            }
            if (investPos != -1) {
                w += profits[investPos];
                used[investPos] = true;
            }

        }

        return w;
    }
}


class Solution29 {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = profits[i];
            arr[i][1] = capital[i];
        }

        Arrays.sort(arr, (a, b) -> a[1] - b[1]);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        int cur = 0;
        while (k > 0) {
            k--;
            while (cur < n && arr[cur][1] <= w) {
                pq.add(arr[cur]);
            }
            if (!pq.isEmpty()) {
                w += pq.poll()[0];
            }
        }

        return w;
    }
}



class Solution30 {
    private int[] alter = new int[]{1, -1};

    public int alternatingSubarray(int[] nums) {
        int n = nums.length;

        int ret = 0;
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n) {
                int diff = nums[j] - nums[j - 1];
                if (!(diff == alter[(j - i - 1) % 2])) break;
                j++;
            }

            if (j - i > 1) {
                ret = Math.max(ret, j - i);
            }
            i = j - 1;

        }

        return ret;
    }
}


class Solution32 {
    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        int[] res = new int[n];

        Stack<Integer> stk = new Stack<>();

        for (int i = n - 1; i >= 0 ; i++) {
            while (!stk.isEmpty() && heights[stk.peek()] <= heights[i]) {
                int pos = stk.pop();
                res[i] += (res[pos] + 1);
            }

            if (!stk.isEmpty()) {
                res[i] += 1;
            }
            stk.add(i);
        }

        return res;
    }
}


class Solution31 {
    public int numberOfBoomerangs(int[][] points) {
        int ans = 0;
        for (int[] p : points) {
            HashMap<Integer, Integer> cnt = new HashMap<>();
            for (int[] p1 : points) {
                int dis = (p[0] - p1[0]) * (p[0] - p1[0]) + (p[1] - p1[1]) * (p[1] - p1[1]);
                cnt.put(dis, cnt.getOrDefault(dis, 0) + 1);
            }

            for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
                int m = entry.getValue();
                ans += m * (m - 1);
            }
        }

        return ans;
    }
}



// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}


class Solution33 {
    Map<Node, Node> cacheNode = new HashMap<>();


    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        if (!cacheNode.containsKey(head)) {
            Node newHead = new Node(head.val);
            cacheNode.put(head, newHead);
            newHead.next = copyRandomList(head.next);
            newHead.random = copyRandomList(head.random);
        }

        return cacheNode.get(head);
    }
}


class Solution34 {
    private Set<String> set = new HashSet<>();
    Map<String, Integer> memo = new HashMap<>();

    public int minExtraChar(String s, String[] dictionary) {
        for (String s1 : dictionary) {
            set.add(s1);
        }

        return dfs(s);
    }

    private int dfs(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }

        if (memo.containsKey(s)) {
            return memo.get(s);
        }


        int res = Integer.MAX_VALUE / 2;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (set.contains(s.substring(i, j))) {
                    res = Math.min(res, i + dfs(s.substring(j)));
                }
            }
        }

        res  = res >= Integer.MAX_VALUE / 2 ? n : res;
        memo.put(s, res);

        return res;
    }
}



class Solution35 {
    public int minExtraChar(String s, String[] dictionary) {
        int n = s.length();
        int[] d = new int[n + 1];
        Arrays.fill(d, Integer.MAX_VALUE);
        Map<String, Integer> map = new HashMap<>();
        for (String s1 : dictionary) {
            map.put(s1, map.getOrDefault(s1, 0) + 1);
        }
        d[0] = 0;
        for (int i = 0; i <= n; i++) {
            d[i] = d[i - 1] + 1;
            for (int j = i - 1; j >= 0 ; j--) {
                if (map.containsKey(s.substring(j, i))) {
                    d[i] = Math.min(d[i], d[j]);
                }
            }
        }

        return d[n];
    }
}



class Solution36 {
    public int minLength(String s) {
        int n = s.length();
        Stack<Character> stk = new Stack<>();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'B') {
                if (!stk.isEmpty() && stk.peek() == 'A') {
                    stk.pop();
                } else {
                    stk.add(c);
                }
            } else if (c == 'D') {
                if (!stk.isEmpty() && stk.peek() == 'C') {
                    stk.pop();
                } else {
                    stk.add(c);
                }
            } else {
                stk.add(c);
            }
        }

        return stk.size();
    }
}



class Solution37 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(0, head);

        ListNode cur = dummy;

        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int x = cur.next.val;
                while (cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }

        return dummy.next;
    }
}



class Solution38 {
    public int addMinimum(String word) {
        int n = word.length();





        int res = 0;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = i - 1; j >= 0 && j >= i - 3; j--) {
               dp[i] = Math.min(dp[i], dp[j] + insert(word.substring(j, i)));
            }
        }

        return dp[n];
    }

    private int insert(String s) {
        int n = s.length();
        if (n == 1) {
            return 2;
        }

        int cnt = 0;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) < s.charAt(i - 1)) {
                cnt += 3;
            }
        }

        return Math.max(n * 2 - cnt, 0);
    }
}



class Solution39 {
    public ListNode rotateRight(ListNode head, int k) {
        int len = 0;
        ListNode dummy = new ListNode(0, head);
        ListNode precur = dummy;
        ListNode cur = head;
        while (cur != null) {
            len++;
            precur = cur;
            cur = cur.next;
        }

        k = k % len;
        if (k == 0) {
            return head;
        }



        Stack<ListNode> stk = new Stack<>();

        cur = dummy.next;
        int target = len - k + 1;
        int cnt = 0;
        while (cur != null) {
            cnt++;
            ListNode back = cur;
            if (cnt >= target) {
                stk.add(cur);
            }
            cur = cur.next;
            if (cnt == target - 1) {
                back.next = null;
            }

        }


        ListNode start = dummy.next, end = precur;
        while (k > 0) {
            k--;
            ListNode pop = stk.pop();
            pop.next = dummy.next;
            dummy.next = pop;
        }

        return dummy.next;
    }
}



class Solution40 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;

        int i = 0, j = m - 1;

        int targetRow = -1;
        while (i <= j) {
            int mid = (i + j) / 2;
            if (matrix[mid][0] <= target) {
                targetRow = mid;
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }

        if (targetRow == -1) {
            return false;
        }

        i = 0; j = n;

        while (i < j) {
            int mid = (i + j) / 2;

            if (matrix[targetRow][mid] == target) {
                return true;
            } else if (matrix[targetRow][mid] < target) {
                i = mid + 1;
            } else {
                j = mid;
            }
        }

        return false;
    }
}



class Solution41 {
    public int count(String num1, String num2, int min_sum, int max_sum) {
        int n1 = Integer.parseInt(num1);
        int n2 = Integer.parseInt(num2);

        int cnt = 0;
        for (int i = n1; i <= n2; i++) {
            int digitSum = digitSum(i);
            if (digitSum >= min_sum && digitSum <= max_sum) {
                cnt++;
            }
        }

        return cnt;
    }

    private int digitSum(int num) {
        int res = 0;
        while (num > 0) {
            res += num % 10;
            num /= 10;
        }

        return res;
    }
}



class Solution42 {
    int n;
    int[] upLimit;
    int[] downLimit;
    int minSum;
    int maxSum;
    private static final int MOD = 1000000007;
    Map<String, Integer> memo = new HashMap<>();


    public int count(String num1, String num2, int min_sum, int max_sum) {
        n = num2.length();
        upLimit =  stringToIntArray(num2);
        downLimit = stringToIntArray(String.format("%" + n + "s", num1).replace(' ', '0'));
        minSum = min_sum;
        maxSum = max_sum;

        return f(0, 0,  true, true, false);
    }

    int f(int pos, int s, boolean dLimit, boolean uLimit, boolean isNum) {
        if (pos == n) {
            return isNum && s >= minSum && s <= maxSum ? 1 : 0;
        }

        String key = pos + "," + s + "," + dLimit + "," + uLimit + "," + isNum;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int up = uLimit ? upLimit[pos] : 9;
        int down = dLimit ? downLimit[pos] : 0;


        int res = 0;
        for (int i = down; i <= up; i++) {
            res = (res + f(pos + 1, (s + i) % MOD, dLimit && i == down, uLimit && i == up, isNum || i != 0)) % MOD;
        }

        res = res % MOD;
        memo.put(key, res);
        return res;
    }

    private int[] stringToIntArray(String str) {
        return str.chars().map(c -> c - '0').toArray();
    }
}



class Solution43 {
    char[] s;

    public int findIntegers(int n) {
        s = Integer.toBinaryString(n).toCharArray();

        return f(0, false, true);
    }

    private int f(int i, boolean pre1, boolean isLimit) {
        if (i == s.length) {
            return 1;
        }

        int up  = isLimit ? s[i] - '0' : 1;
        int res = f(i + 1, false, isLimit && up == 0);
        if (!pre1 && up == 1) {
            res += f(i + 1, true, isLimit);
        }

        return res;
    }
}

class Solution44 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;

        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int val = cur.next.val;
                ListNode nxt = cur.next;
                while (nxt != null && nxt.val == val) {
                    nxt = nxt.next;
                }

                cur.next = nxt;
                cur = nxt;
            } else {
                cur = cur.next;
            }
        }


        return dummy.next;
    }
}



class Solution45 {
    int[] num;
    Map<String, Integer> memo = new HashMap<>();

    public int numberOf2sInRange(int n) {
        num = String.valueOf(n).chars().map(c -> c - '0').toArray();
        return f(0, 0, true);
    }

    private int f(int pos, int cnt, boolean isLimit) {
        if (pos == num.length) {
            return cnt;
        }

        String key = pos + "," + cnt + "," + isLimit;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int up = isLimit ? num[pos] : 9;

        int res = 0;
        for (int i = 0; i <= up; i++) {
            res += f(pos + 1, cnt + (i == 2 ? 1 : 0), isLimit && i == up);
        }

        memo.put(key, res);

        return res;
    }

}






class Solution46 {
    int[] num;
    int[] numCnt = new int[10];
    Map<String, Integer> memo = new HashMap<>();

    public int numDupDigitsAtMostN(int n) {
        num = String.valueOf(n).chars().map(c -> c - '0').toArray();

        return f(0, false, true);
    }

    private int f(int pos, boolean isNum, boolean isLimit) {
        if (pos == num.length) {
            if (!isNum) {
                return 0;
            }

            for (int cnt : numCnt) {
                if (cnt >= 2) {
                    return 1;
                }
            }

            return 0;
        }

        String key = pos + "," + isNum + "," + isLimit;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }


        // 这里的numcnt其实也是一种状态

        int up = isLimit ? num[pos] : 9;


        int res = 0;
        for (int i = 0; i <= up; i++) {
            if (isNum || i != 0) {
                numCnt[i]++;
                res += f(pos + 1, true, isLimit && i == up);
                numCnt[i]--;
            } else {
                res += f(pos + 1, false, isLimit && i == up);
            }
        }

        memo.put(key, res);

        return res;
    }

    public static void main(String[] args) {
        System.out.println("hello world");
    }

}


class MinStack {
    Deque<Integer> xStk;
    Deque<Integer> minStk;

    public MinStack() {
        xStk = new LinkedList<Integer>();
        minStk = new LinkedList<>();
        minStk.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        xStk.push(val);
        minStk.push(Math.min(val, minStk.peek()));
    }

    public void pop() {
        xStk.pop();
        minStk.pop();
    }

    public int top() {
        return xStk.peek();
    }

    public int getMin() {
        return minStk.peek();
    }
}




class Solution47 {
    int min_sum;
    int max_sum;

    public int count(String num1, String num2, int min_sum, int max_sum) {
        String s = subtractOne(num1);
        int[] num1x = s.chars().map(c -> c - '0').toArray();
        int[] num2x = num2.chars().map(c -> c - '0').toArray();
        this.min_sum = min_sum;
        this.max_sum = max_sum;

        return f(0, 0, true, num2x) - f(0,  0, true, num1x);
    }

    private int f(int pos, int s, boolean isLimt, int[] num) {
        if (pos == num.length) {
            if (s <= max_sum && s >= min_sum) {
                return 1;
            }

            return 0;
        }

        int up = isLimt ? num[pos] : 9;

        int res = 0;
        for (int i = 0; i <= up; i++) {
            res += f(pos + 1, s + i, isLimt && i == up, num);
        }

        return res;
    }


    public String subtractOne(String num) {
        char[] chars = num.toCharArray();
        int length = chars.length;
        boolean shouldContinue = true;

        for (int i = length - 1; i >= 0 && shouldContinue; i--) {
            if (chars[i] > '0') {
                chars[i]--;
                shouldContinue = false;
            } else {
                chars[i] = '9';
            }
        }

        // 如果最高位是0（即原始数字是10的幂），需要去掉这个最高位
        String result = new String(chars);
        if (result.charAt(0) == '0') {
            result = result.substring(1);
        }

        return result;
    }
}


class Solution48 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
         int spare = 0;

         int minSpare  = Integer.MAX_VALUE;
         int minIndex  = 0;

        for (int i = 0; i < n; i++) {
            spare += gas[i] - cost[i];

            if (spare < minSpare) {
                minSpare = spare;
                minIndex = i;
            }
        }


        return spare < 0 ? -1 : (minIndex + 1) % n;
    }
}




class Solution49 {
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size();

        int res = 0;
        //列举每个可能的峰值
        for (int i = 0; i < n; i++) {
           res = Math.max(res , maxHeights.get(i)+ sumHeightBypos(i, maxHeights));
        }

        return res;
    }

    private int sumHeightBypos(int i, List<Integer> maxHeights) {
        int res = 0;

        int last = maxHeights.get(i);
        for (int j = i - 1; j >= 0 ; j--) {
            int temp = Math.min(last, maxHeights.get(j));
            res += temp;
            last = temp;
        }

        last = maxHeights.get(i);

        for (int j = i + 1; j < maxHeights.size(); j++) {
            int temp = Math.min(last, maxHeights.get(j));
            res += temp;
            last = temp;
        }

        return res;
    }
}




class Solution50 {
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size();
        long res = 0;
        long[] prefix = new long[n];
        long[] suffix = new long[n];

        Deque<Integer> stk1 = new ArrayDeque<>();
        Deque<Integer> stk2 = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!stk1.isEmpty() && maxHeights.get(i) < maxHeights.get(stk1.peek())) {
                stk1.pop();
            }

            if (stk1.isEmpty()) {
                prefix[i] = (long) (i + 1) * maxHeights.get(i);
            } else {
                prefix[i] = prefix[stk1.peek()] + (long) (i - stk1.peek()) * maxHeights.get(i);
            }

            stk1.push(i);
        }

        for (int i = n - 1; i >= 0 ; i--) {
            while (!stk2.isEmpty() && maxHeights.get(stk2.peek()) > maxHeights.get(i)) {
                stk2.pop();
            }

            if (stk2.isEmpty()) {
                suffix[i] = (long) (n - i) * maxHeights.get(i);
            } else {
                suffix[i] = suffix[stk2.peek()] + (long) (stk2.peek() - i) * maxHeights.get(i);
            }

            stk2.push(i);

            res = Math.max(res, prefix[i] + suffix[i] - maxHeights.get(i));
        }

        return res;
    }
}



class Solution51 {
    public int sumIndicesWithKSetBits(List<Integer> nums, int k) {
        int res = 0;
        int n = nums.size();
        for (int i = 0; i < n; i++) {
            if (Integer.bitCount(i) == k) {
                res += nums.get(i);
            }
        }

        return res;
    }
}





class Solution52 {
    public int maxPoints(int[][] points) {
        int n = points.length, ans = 1;
        if (n <= 2) {
            return n;
        }

        for (int i = 0; i < n; i++) {
            int[] x = points[i];
            for (int j = i + 1; j < n; j++) {
                int[] y = points[j];
                int cnt = 2;
                for (int k = j + 1; k < n; k++) {
                    int[] p = points[k];
                    int s1 = (y[1] - x[1]) * (y[0] - p[0]);
                    int s2 = (y[1] - p[1]) * (y[0] - x[0]);

                    if (s1 == s2) {
                        cnt++;
                    }

                    ans = Math.max(ans, cnt);
                }
            }
        }

        return ans;
    }
}





class Solution53 {
    public int maxPoints(int[][] points) {
        int n = points.length, ans = 1;
        for (int i = 0; i < n; i++) {
            Map<String, Integer> map = new HashMap<>();
            int x = points[i][0], y = points[i][1];
            for (int j = i + 1; j < n; j++) {
                int x1 = points[j][0], y1 = points[j][1];
                int up = y1 - y, down = x1 - x;
                int d = gcd(up, down);
                up /= d; down /= d;
                String key = up + "/" + down;
                int cnt = map.getOrDefault(key, 1) + 1;
                ans = Math.max(ans, cnt);
                map.put(key, cnt);
            }
        }

        return ans;
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}


class Solution54 {
    public int maxArea(int[] height) {
        int ans = 0;
        int n = height.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                ans = Math.max((j - i) * Math.min(height[i], height[j]), ans);
            }
        }

        return ans;
    }
}



class Solution55 {
    public int maxArea(int[] height) {
        int n = height.length;
        int i = 0, j = n - 1;
        int ans = 0;

        while (i < j) {
            ans = Math.max((j - i) * Math.min(height[i], height[j]), ans);
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }

        return ans;
    }
}



class Solution56 {
    public int findMin(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        int ans = Integer.MAX_VALUE;

        while (l <= r) {
            int mid = (l + r) / 2;
            int temp = nums[mid];
            ans = Math.min(temp, ans);
            if (temp < nums[r]) {
                r = mid - 1;
            } else  {
                l = mid + 1;
            }
        }

        return ans;
    }
}



class Solution57 {
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int orangesRotting(int[][] grid) {
        Deque<Integer> q = new ArrayDeque<>();
        int m = grid.length, n = grid[0].length;
        int freshOranges = 0; // 新鲜橘子的数量
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    q.add(i * n + j);
                } else if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }

        // 如果没有新鲜橘子，直接返回0
        if (freshOranges == 0) {
            return 0;
        }

        int time = 0;

        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                int cur = q.poll();
                int x = cur / n, y = cur % n;
                for (int[] dir : dirs) {
                    int nx = x + dir[0], ny = y + dir[1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 1) {
                        q.add(nx * n + ny);
                        grid[nx][ny] = 2;
                        freshOranges--; // 新鲜橘子的数量减少
                    }
                }
            }
            time++;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }

        // 如果还有新鲜橘子剩余，返回-1
        if (freshOranges > 0) {
            return -1;
        } else {
            return time - 1; // 减去最后一轮，因为最后一轮没有橘子变腐烂
        }
    }
}



class Solution58 {
    private int n;
    private List<List<Integer>> ans;

    public List<List<Integer>> permute(int[] nums) {
        n = nums.length;
        ans = new ArrayList<>();

        xzj(0, nums, 0, new ArrayList<Integer>());

        return ans;
    }

    private void xzj(int i, int[] nums, int used, ArrayList<Integer> premute) {
        if (i == n) {
            ans.add(premute);
        }

        for (int j = 0; j < n; j++) {
            if ((1 << j & used) == 0) {
                premute.add(nums[j]);
                xzj(i + 1, nums, used | (1 << j), premute);
                premute.remove(nums[j]);
            }
        }

    }
}


class Solution59 {
    private int n;
    List<String> ans;
    Map<Character, String> phoneMap = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};

    public List<String> letterCombinations(String digits) {
        n = digits.length();
        ans = new ArrayList<>();
        if (n == 0) {
            return ans;
        }


        f(0, digits,  new StringBuilder());

        return ans;
    }

    private void f(int i, String digits, StringBuilder s) {
        if (i == n) {
            ans.add(s.toString());
            return;
        }

        char c = digits.charAt(i);
        String s1 = phoneMap.get(c);
        for (int j = 0; j < s1.length(); j++) {
            s.append(s1.charAt(j));
            f(i + 1, digits, s);
            s.deleteCharAt(i);
        }
    }

}


class Solution60 {
    private int n;
    List<String> ans;

    public List<String> generateParenthesis(int n) {
        this.n = 2 * n;
        ans = new ArrayList<>();
        f(0, 0, new StringBuilder());

        return ans;
    }

    private void f(int i, int diff, StringBuilder sb) {
        if (diff < 0 || diff > n - i) {
            return;
        }

        if (i == n) {
            if (diff == 0) {
                ans.add(sb.toString());
            }
            return ;
        }

        sb.append('(');
        f(i + 1, diff + 1, sb);
        sb.deleteCharAt(sb.length() - 1);

        sb.append(')');
        f(i + 1, diff - 1, sb);
        sb.deleteCharAt(sb.length() - 1);
    }

}



class Solution61 {
    public List<String> splitWordsBySeparator(List<String> words, char separator) {
        List<String> res = new ArrayList<>();

        for (String word : words) {
            int n = word.length();
            int i = 0;
            while (i < n) {
                int j = i;
                while (j < n && word.charAt(j) != separator) {
                    j++;
                }

                if (j - i > 0) {
                    res.add(word.substring(i, j));
                }
                i = j + 1;
            }
        }

        return res;
    }
}


class Solution62 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = postorder.length;
        if (n == 0) {
            return null;
        }

        int val = postorder[n - 1];
        TreeNode treeNode = new TreeNode(val);
        int index = findIndexOfInorder(inorder, val);
        int[] leftInorder = Arrays.copyOfRange(inorder, 0, index);
        int[] rightInorder = Arrays.copyOfRange(inorder, index + 1, n);
        int[] leftPostorder = Arrays.copyOfRange(postorder, 0, index);
        int[] rightPostorder = Arrays.copyOfRange(postorder, index, n - 1);
        treeNode.left = buildTree(leftInorder, leftPostorder);
        treeNode.right = buildTree(rightInorder, rightPostorder);

        return treeNode;
    }

    private int findIndexOfInorder(int[] inorder, int val) {
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == val) {
                return i;
            }
        }

        return -1;
    }
}



class Solution63 {
    private int n;
    private List<List<Integer>> res;
    public List<List<Integer>> subsets(int[] nums) {
        n = nums.length;
        res = new ArrayList<>();

        f(nums, 0, new ArrayList<>());

        return res;
    }

    private void f(int[] nums, int i, ArrayList<Integer> temp) {
        List<Integer> back = List.copyOf(temp);
        res.add(back);

        if (temp.size() == n) {
            return ;
        }
        for (int j = i; j < n; j++) {
            temp.add(nums[i]);
            f(nums, j + 1, temp);
            temp.remove(temp.size() - 1);
        }
    }

}


class Solution64 {
    private int n;
    private List<List<Integer>> res;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        n = candidates.length;
        res = new ArrayList<>();


        f(candidates, 0,  new ArrayList<>(), target);

        return res;
    }

    private void f(int[] candidates, int pos, List<Integer> temp, int target) {
        if (target < 0) {
            return ;
        }

        if (0 == target) {
            res.add(List.copyOf(temp));
        }

        for (int i = pos; i < n; i++) {
            temp.add(candidates[i]);
            f(candidates, pos, temp, target - candidates[i]);
            temp.remove(temp.size() - 1);
        }
    }

}



class Solution65 {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[i] = true;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] | col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}












class Solution66 {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int n = houses.length;

        float radius = houses[0] + (float) (houses[n - 1] - houses[0]) / 2;

        int heater = binarySearch(heaters, radius);

        int result = Math.max(heater - houses[0], houses[n - 1] - heater);

        return result;
    }

    private int binarySearch(int[] heaters, float radius) {
        int l = 0, r = heaters.length;
        int res = heaters[heaters.length - 1];

        while (l < r) {
            int mid = (l + r) / 2;
            if (heaters[mid] >= radius) {
                res = Math.abs(heaters[mid] - radius) < Math.abs(res - radius) ? heaters[mid] : res;
                r = mid;
            } else {
                res = Math.abs(heaters[mid] - radius) < Math.abs(res - radius) ? heaters[mid] : res;
                l = mid + 1;
            }
        }

        return res;
    }
}



class Solution67 {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        int radius = Integer.MAX_VALUE;

        for (int heater : heaters) {
            int maxDis = binarySearch(houses, heater);
            radius = Math.max(radius, maxDis);
        }

        return radius;
    }

/*    private int binarySearch(int[] houses, int heater) {
        int l = 0, r = houses.length;

        int res = houses[houses.length - 1];
        while (l < r) {
            if ()
        }

        return 0;
    }*/

    private int binarySearch(int[] heaters, int radius) {
        int l = 0, r = heaters.length;
        int res = heaters[heaters.length - 1];

        while (l < r) {
            int mid = (l + r) / 2;
            if (heaters[mid] >= radius) {
                res = Math.abs(heaters[mid] - radius) > Math.abs(res - radius) ? heaters[mid] : res;
                l = mid + 1;
            } else {
                res = Math.abs(heaters[mid] - radius) > Math.abs(res - radius) ? heaters[mid] : res;
                r = mid;
            }
        }

        return Math.abs(res - radius);
    }
}



class Solution68 {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);


        int radius = 0;
        for (int house : houses) {
            radius = Math.max(radius, binarySearch(heaters, house));
        }

        return radius;
    }

    private int binarySearch(int[] heaters, int house) {
        int l = 0, r = heaters.length;
        int result = heaters[heaters.length - 1];

        while (l < r) {
            int mid = (l + r) / 2;
            if (heaters[mid] >= house) {
                r = mid;
                result = Math.abs(heaters[mid] - house) < Math.abs(result - house) ? heaters[mid] :result;
            } else {
                l = mid + 1;
                result = Math.abs(heaters[mid] - house) < Math.abs(result - house) ? heaters[mid] :result;
            }
        }


        return Math.abs(result - house);
    }


}



class Solution69 {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }

            return a[0] - b[0];
        });
        ArrayList<int[]> temp = new ArrayList<>();

        int start = intervals[0][0], end = intervals[0][1];
        for (int[] interval : intervals) {
            if (interval[0] > end) {
                temp.add(new int[]{start, end});
                start = interval[0];
                end = interval[1];
            } else {
                end = Math.max(end, interval[1]);
            }
        }

        temp.add(new int[]{start, end});
        return temp.stream().toArray(new IntFunction<int[][]>() {
            @Override
            public int[][] apply(int value) {
                return new int[value][];
            }
        });
    }
}


class Solution70 {
    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> {
            return -1;
        });

        return 0;
    }



    private static String f() {
        System.out.println("11111111111111111");
        return "33333333333333333333333";
    }
}





class Solution6666 {
    private int res;

    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }

            return a[0] - b[0];
        });

        dfs(events, 0, 0, k, new int[]{-1, -1});

        return res;
    }

    private void dfs(int[][] events, int pos, int val, int k, int[] lastChose) {
        if (k == 0 || pos == events.length) {
            res = Math.max(res, val);
            return;
        }

        int start = events[pos][0], end = events[pos][1], value = events[pos][2];
        if (start > lastChose[1]) {
            dfs(events, pos + 1, val + value, k - 1, events[pos]);
        }
        dfs(events, pos + 1, val, k, lastChose);
    }

}

// 这里的备忘录还是让人学到了很多                  还可以更新最优解  且不用全部的状态作为备忘录     要记住咯

class Solution66666 {
    private Map<String, Integer> memo; // 使用Map作为备忘录

    public int maxValue(int[][] events, int k) {
        memo = new HashMap<>();
        Arrays.sort(events, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        return dfs(events, 0, k, new int[]{-1, -1});
    }

    private int dfs(int[][] events, int pos, int k, int[] lastChose) {
        if (k == 0 || pos == events.length) {
            return 0;
        }

        // 使用pos, k和lastChose[1]构建一个唯一的key
        String key = pos + "," + k + "," + lastChose[1];
        if (memo.containsKey(key)) {
            // 如果这个状态已经计算过了，直接返回之前计算的结果
            return memo.get(key);
        }

        int taken = 0;
        int start = events[pos][0], end = events[pos][1], value = events[pos][2];
        if (start > lastChose[1]) {
            taken = value + dfs(events, pos + 1, k - 1, events[pos]);
        }
        int notTaken = dfs(events, pos + 1, k, lastChose);

        // 存储当前状态的最大结果到备忘录中，并返回这个结果加上当前的val
        memo.put(key, Math.max(taken, notTaken));
        return memo.get(key);
    }
}

class Sort {
    static public void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    static public void quickSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }

        int index = partion(arr, low, high);
        quickSort(arr, low, index - 1);
        quickSort(arr, index + 1, high);
    }

    private static int partion(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j <= high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        return i;
    }

    public static void main(String[] args) {
        int[] arr = {3, 9, 5, 1, 2, 0};
        Sort.quickSort(arr);
        for (int num : arr) {
            System.out.println(num);
        }
    }
}






































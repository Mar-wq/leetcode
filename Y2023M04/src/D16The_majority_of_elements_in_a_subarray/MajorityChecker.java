package D16The_majority_of_elements_in_a_subarray;

//随机化  加   二分查找    还有一个摩尔投票 + 线段树


import java.util.*;

/*class MajorityChecker {
    public static final int K = 20;
    private int[] arr;
    private Map<Integer, List<Integer>> valToIndex;  //java中Map的用法   跟List一样   先定义一个引用，然后用于接受HashMap;
    private Random random;   //随机数类


    public MajorityChecker(int[] arr) {
        this.arr = arr;
        this.valToIndex = new HashMap<Integer, List<Integer>>();

        for (int i = 0; i < arr.length; i++) {
            valToIndex.putIfAbsent(arr[i], new ArrayList<Integer>());  //HashMap的方法记一记
            valToIndex.get(arr[i]).add(i);   //同上
        }
        this.random = new Random();
    }

    public int query(int left, int right, int threshold) {
        int length = right - left + 1;

        for (int i = 0; i < K; i++) {
            int x = arr[left + random.nextInt(length)];  //生成[0, length]的随机数
            List<Integer> pos = valToIndex.get(x);

            int cnt = searchEnd(pos, right) - searchStart(pos, left);

            if (cnt >=  threshold) {
                return x;
            }
            else if (cnt * 2 >= length) {
                return -1;
            }
        }

        return -1;
    }


    //  这里的边界问题我最后得出的结论就是   要么下边界取得到    上边界娶不到      要么都取得到
    private int searchStart(List<Integer> pos, int target) {
        int low = 0, high = pos.size();

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (pos.get(mid) < target) {
                low = mid + 1;
            }
            else if (pos.get(mid) == target) {
                return mid;
            }
            else {
                high = mid;
            }
        }

        return low;
    }


    // List相当于接口   多态
    private int searchEnd(List<Integer> pos, int target) {
        int low = 0, high = pos.size();

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (pos.get(mid)  <  target) {
                low = mid + 1;
            }
            else if (pos.get(mid) == target) {
                return mid;
            }

            else {
                high = mid;
            }
        }

        return low;
    }

}*/


class MajorityChecker {
    private int[] arr;
    private Map<Integer, List<Integer>> loc;
    private int n;
    private Node[] tree;

    public MajorityChecker(int[] arr) {
        this.n = arr.length;    //就是这里的一个小问题  能让我改一个小时   真的是醉了
        this.arr = arr;
        this.loc = new HashMap<Integer, List<Integer>>();

        for (int i = 0; i < n; i++) {
            loc.putIfAbsent(arr[i], new ArrayList<Integer>());
            loc.get(arr[i]).add(i);
        }

        this.tree = new Node[n * 4];

        buildTree(1, 0, n - 1);
    }

    private void buildTree(int id, int left, int right) {
        if (left == right) {
            tree[id] = new Node(arr[left], 1);

            return ;
        }

        int mid = left + (right - left) / 2;
        buildTree(id * 2, left, mid);
        buildTree(id * 2 + 1, mid + 1, right);

        tree[id] = new Node();
        tree[id].add(tree[id * 2]);
        tree[id].add(tree[id * 2 + 1]);
    }

    public int query(int left, int right, int threshold) {
        Node prob = new Node();
        queryInTree(1, 0, n - 1, left, right, prob);
        List<Integer> pos = loc.getOrDefault(prob.x, new ArrayList<Integer>());   //getOrDefault()这个函数是干什么的呢
        //List<Integer> pos = loc.get(prob.x);  这个写法有问题  还不清楚问题在哪  有可能pos为null

        int cnt = searchEnd(pos, right) - searchStart(pos, left);

        return cnt >= threshold ? prob.x : -1;

    }

    private void queryInTree(int id, int l, int r, int left, int right, Node prob) {
        if (l > right || r < left) {
            return ;
        }

        if (l >= left && r <= right) {
            prob.add(tree[id]);
            return ;
        }

        int mid = l + (r - l) / 2;
        queryInTree(id * 2, l, mid, left, right, prob);
        queryInTree(id * 2 + 1, mid + 1, r, left, right, prob);
    }


    private int searchStart(List<Integer> pos, int target) {
        int low = 0, high = pos.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (pos.get(mid) >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private int searchEnd(List<Integer> pos, int target) {
        int low = 0, high = pos.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (pos.get(mid) > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }


};

class Node {
    int x;
    int cnt;

    public Node() {
        this(0, 0);
    }

    public Node(int x, int cnt) {
        this.x = x;
        this.cnt = cnt;
    }

    public void add (Node that) {
        if (x == that.x) {
            cnt += that.cnt;
        } else if (cnt > that.cnt) {
            cnt -= that.cnt;
        } else {
            x = that.x;
            cnt = that.cnt - cnt;
        }

        return ;
    }
}

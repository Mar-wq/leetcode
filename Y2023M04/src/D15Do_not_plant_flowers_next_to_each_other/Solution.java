package D15Do_not_plant_flowers_next_to_each_other;

import java.util.ArrayList;
import java.util.List;


//  光标变成粗的就是覆盖式输入       再按一下insert键就可以退出了


class Solution {
    public int[] gardenNoAdj(int n, int[][] paths) {
        List<Integer>[] graph  = new List[n];

        for (int i  = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for (int[] path : paths) {
            graph[path[0] - 1].add(path[1] - 1);
            graph[path[1] - 1].add(path[0] - 1);
        }

        int[] ans = new int[n];


        //这里只要求输出一种   直接用迭代的方式比较简单   也可以dfs遍历全部可行的方式
        for (int i = 0; i < n; i++) {
            boolean[] colored = new boolean[5];

            //java中数组的遍历   有一次了
            for (int adj : graph[i]) {
                colored[ans[adj]] = true;
            }
            //颜色从1开始巧妙地避开了没有还没有给定花种类的花园
            for (int j = 1; j < 5; j++) {
                if (!colored[j]) {
                    ans[i] = j;
                }
            }
        }

        return ans;
    }
}
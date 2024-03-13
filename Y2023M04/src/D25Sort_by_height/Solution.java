package D25Sort_by_height;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public String[] sortPeople(String[] names, int[] heights) {
        int n = names.length;

        Integer[] indices = new Integer[n];

        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }


        // java中的lamda我还是不会  会什么要用Interge呢
        Arrays.sort(indices, (a, b) -> heights[b] - heights[a]);

        String[] res = new String[n];

        for (int i = 0; i < n; i++) {
            res[i] = names[indices[i]];
        }

        return res;
    }
}

class Test {
    public void test() {
/*
        这样是不行的    好像只能是sort中只能传人  T[]类型的
ArrayList<Integer> arr = new ArrayList<>(5);

        Arrays.sort(new ArrayList[]{arr});*/

        int[] a = {1, 2, 3};
        Integer[] arr =  {1, 2, 3, 4};


        // 匿名内部类
       /*Arrays.sort(arr,new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });*/


        Arrays.sort(arr,(Integer o1, Integer o2) ->{
                return o1 - o2;
            }
        );

    }
}

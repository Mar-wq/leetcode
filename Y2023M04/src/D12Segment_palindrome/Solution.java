package D12Segment_palindrome;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution {

    //题目都搞错了
    /*public int longestDecomposition(String text) {
        int n = text.length();
        System.out.println(n);

        //Arrays 是一个静态工具类    里面只有静态方法
        *//*Arrays factors = getFactor(n);*//*

        ArrayList<Integer> a = getD(n);

        a.sort((x, y)-> {
            return x - y;
        });
        System.out.println(a);
        for (int i = 0; i < a.size(); i++) {
            int l = a.get(i);
            if (isP(text, l)) {
                return l;
            }
        }

        return -1;
    }

    private boolean isP(String text, int l) {
        int n = text.length();
        int k = n / l;
        int compCnt = (k + 1) / 2, i = 0;
        while (i < k) {
            if (!text.substring(i * l, (i + 1) * l).equals(text.substring(n  - 1 - (i + 1)  * l, n - 1 - i * l))) {
                return false;
            }
            i++;
        }

        return true;
    }

    private ArrayList<Integer> getD(int n) {
        ArrayList<Integer> a = new ArrayList<>();

        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                a.add(i);
                a.add(n / i);
            }
        }

        return a;
    }*/

    public int longestDecomposition(String text) {
        int n = text.length();
        for (int i = 1; i <= n / 2; i++) {
            if (text.substring(0, i).equals(text.substring(n - i))) {
                return 1 + longestDecomposition(text.substring(i, n - i));
            }
        }

        return 1;
    }
}

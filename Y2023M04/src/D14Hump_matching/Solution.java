package D14Hump_matching;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        int n = queries.length;

        //List 是一个接口，ArrayList是List接口的一个实现类
        //ArryList类继承并实现了List接口

        //因此，List接口不能创建实例对象，但是可以为List接口创建一个指向自己的对象引用，
        //而ArrayList实现类的实例对象就在这充当指向List接口的对象引用。 有点像多态
        List<Boolean> res  = new ArrayList<Boolean>();

        for (int i = 0; i < n; i++) {
            boolean flag = true;
            int p = 0;
            for (int j = 0; j < queries[i].length(); j++) {
                char c = queries[i].charAt(j);

                if (p < pattern.length() && pattern.charAt(p) == c) {
                    p++;
                } else if (Character.isUpperCase(c)) {
                    flag = false;
                    break;
                }
            }

            if  (p < pattern.length()) {
                flag = false;
            }

            res.add(flag);
        }


        return res;
    }
}

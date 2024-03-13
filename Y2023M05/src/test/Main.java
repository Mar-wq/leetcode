package test;


import java.util.Arrays;
import java.util.Comparator;

// 它爹中的静态方法显然已经被调用
public class Main {
    public static void main(String[] args) {
        Stactic1.fun();
        Class cl = Stactic1.class;
        Arrays.sort(args, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });
    }
}

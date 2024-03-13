package D25Sort_by_height.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

public class CollectionDemo {
    public static void main(String[] args) {
        Collection<String> coll = new ArrayList<>();
        coll.add("zhangsan");
        coll.add("lisi");
        coll.add("wangwu");

        // 2.利用匿名内部类的形式
        // forEach 的底层原理  其实也会自己遍历集合，依次得到每一个元素
        // 把得到的每一个元素，传递给下面的accept
        // s依次表示集合中的每一个元素
/*        coll.forEach(new Consumer<String>() {
            @Override
            // s 依次表示集合中的每一个数据
            public void accept(String s) {
                System.out.println(s);
            }
        });*/

        coll.forEach(s -> System.out.println(s));
    }
}

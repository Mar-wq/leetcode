package com.hdy.methodReference;





// 这里的函数式编程就相当一个人他告诉你了怎么做，你按照他说的做就没问题，该找的资源告诉你的人是知道的，所以不必担心找不到资源的情况
public class TestMethodReference {
    private static final String s = "hdy nihaoya";

    public static void main(String[] args) {
        Func a = TestMethodReference::method;

        new Abb(a);

    }


    static int method(int a) {
        System.out.println(s);
        return 0;
    }
}


class  Abb {
    private Func a;

    public Abb(Func a) {
        a.apply(0);
    }
}


@FunctionalInterface
interface Func {
    int apply(int a);
}

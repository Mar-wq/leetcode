package test.test1;

public class Test {
    public static void main(String[] args) {
        f();
    }

    private static void f() {
        m1();
    }

    private static void m1() {
        try {
            m2();
        } catch (Exception e) {
            System.out.println("我在m1中抓住了异常");
        }
    }

    private static void m2() {
        int i = 1 / 0;
    }
}

package stack;

public class Test {
    public static void main(String[] args) {
        f1();
    }

    private static void f1() {
        f2(1, 2);
    }

    public static int f2(int a, int b) {
        int c = a + b;
        return c;
    }
}

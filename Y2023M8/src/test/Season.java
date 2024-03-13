package test;

public enum Season {
    Springs, Atum, Summer, winter;
}

 class Test {
    public static void main(String[] args) {
        f(Season.Summer);
    }

    static void f(Season s){
        System.out.println(s);
    }
}

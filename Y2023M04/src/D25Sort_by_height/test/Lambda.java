package D25Sort_by_height.test;

public class Lambda {
    public static void main(String[] args) {
/*        method(new Swim() {
            @Override
            public void swimming() {
                System.out.println("游泳");
            }
        });*/

/*        method(() -> {
                System.out.println("游泳");
            }
        );*/

        method( () -> System.out.println("游泳"));
    }

    /* 1.  利用匿名内部类的形式去调用下面的方法
    *   调用一个方法的时候，如果该方法的形参就是一个接口，那么我们要传递这个接口的实现类对象
    * 如果实现类对象只用到一次，就可以用匿名内部类的形式进行书写
    *  */
    public static void method(Swim s) {
        s.swimming();
    }
}

@FunctionalInterface
interface Swim {
     int a = 10;
    public abstract void swimming();
}

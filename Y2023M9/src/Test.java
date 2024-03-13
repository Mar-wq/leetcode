import java.util.PriorityQueue;

public class Test {
    public static void main(String[] args) {
        Thread t1 = new Thread("t1"){

            // 这种方式是 Java 中创建匿名内部类的一种形式，也可以称之为“匿名内部类的匿名对象”。
            //
            //在这段代码中，你创建了一个继承自 Thread 类的匿名内部类，并在该匿名内部类中覆写了 run() 方法。然后，你使用匿名内部类的方式创建了一个 Thread 类的实例。
            //
            //这种方式的好处是可以在需要时立即创建一个具有特定行为的对象，而无需为此专门编写一个独立的类。这在一些简单的情况下可以节省代码量和提高代码的可读性。

            @Override
            public void run() {
                System.out.println("say hello");
            }
        };
        t1.run();
    }
}


package mulThread;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        ThreadSafe test = new ThreadSafe();
        for (int i = 0; i < 2; i++) {
            new Thread(()-> test.method1(200)).start();
        }
    }
}

class ThreadSafe {
   public final void method1 (int loopNumber) {
       ArrayList<String> list = new ArrayList<>();
       for (int i = 0; i < loopNumber; i++) {
           method2(list);
           method3(list);
       }

   }
   public void method2(ArrayList<String> list) {
       list.add("1");
   }

   public void method3(ArrayList<String> list) {
       list.remove(0);
   }

}

class ThreadUnsafe {
    List<String> list = new ArrayList<>();
    public void method1(int loopNumber) {
        for (int i = 0; i < loopNumber; i++) {
            method2();
            method3();
        }
    }

    public void method2() {
        list.add("1");
    }

    public void method3() {
        list.remove(0);
    }
}

package mulThreadSaleTickets;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

  public class Test {
    public static void main(String[] args) throws InterruptedException {
        // 模拟多人买票
        TicketWindow window = new TicketWindow(10000);

        // 所有线程的集合
        List<Thread> threadList = new ArrayList<>();

        // 卖出的票数统计   // 这里使用vector估计也是为了线程安全
        List<Integer> amountList = new Vector<>();

        for (int i = 0; i < 2000; i++) {
            Thread thread = new Thread(()->{
                // 买票
                int amount = window.sell(randomAmount());
                try {
                    Thread.sleep(randomAmount());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                amountList.add(amount);
            });
            threadList.add(thread);
            thread.start();
        }
        for (Thread thread : threadList) {
            thread.join();
        }

        // 统计卖出的票数和剩余票数
        System.out.println("余票：" + window.getCount());
        // 这个流还是没学会  很难受
        System.out.println("卖出的票数：" + amountList.stream().mapToInt(i -> i).sum());
    }

    // Random 为线程安全
    static Random random = new Random();

    // 随机 1 - 5
    public static int randomAmount() {
        return random.nextInt(5) + 1;
    }
}

class TicketWindow {
    private int count;

    public TicketWindow(int count) {
        this.count = count;
    }

    // 获取余票数量
    public int getCount() {
        return count;
    }

    // 售票
    public int sell(int amount) {
        if (this.count >= amount) {
            this.count -= amount;
            return amount;
        } else {
            return 0;
        }
    }
}

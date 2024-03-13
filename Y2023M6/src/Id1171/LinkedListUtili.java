package Id1171;

import java.util.ArrayList;
import java.util.List;


//这个题真的是抽象到家了   根本没有解释清楚   渐渐开始懂了
public class LinkedListUtili {

    public static int[] linkedListToArray(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            list.add(curr.val);
            curr = curr.next;
        }

        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }

        return arr;
    }

}
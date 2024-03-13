package Id1171;


import org.w3c.dom.Node;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}


// 渐渐开始懂了
class Solution {
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        Map<Integer, ListNode> seen = new HashMap<>();
        int prefix = 0;
        for (ListNode node = dummy; node != null; node = node.next) {
            prefix += node.val;
            seen.put(prefix, node);
        }
        prefix = 0;
        for (ListNode node = dummy; node != null; node = node.next) {
            prefix += node.val;
            //这里之所以不会出现空引用的异常，是因为map里一定有我自身的原因。
            // 然后也不会找到前面跟我一样值的节点，因为在第一轮的遍历中，已经被我覆盖了。
            node.next = seen.get(prefix).next;
            System.out.println(prefix);
            int[] arr = LinkedListUtili.linkedListToArray(dummy.next);
            System.out.println(Arrays.toString(arr));
        }
        return dummy.next;
    }
}

package id142_linked_list_cycle_ii;

import common.ListNode;

public class Solution {

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head.next, fast = head.next.next;

        while (slow != fast) {
            if (slow == null || fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode newP = head;

        while (newP != slow) {
            newP = newP.next;
            slow = slow.next;
        }

        return newP;
    }
}

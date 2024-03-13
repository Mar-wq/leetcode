package id23merge_k_sorted_lists;

import common.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    /**
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        PriorityQueue<ListNode> pq = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        ListNode p = dummy;
        for (ListNode list : lists) {
            if (list != null) {
                pq.add(list);
            }
        }

        while (!pq.isEmpty()) {
            ListNode cur = pq.poll();
            if (cur.next != null) {
                pq.add(cur.next);
            }
            cur.next = p.next;
            p.next = cur;
            p = p.next;
        }

        return dummy.next;
    }
}

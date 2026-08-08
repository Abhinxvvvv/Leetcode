// Last updated: 09/08/2026, 00:07:16
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        // Base cases: empty list, single node, or no rotation requested
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // 1. Find the length and the current tail of the list
        ListNode tail = head;
        int length = 1;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // 2. Optimize k using modulo arithmetic
        k = k % length;
        if (k == 0) {
            return head; // A full cycle means no rotation is needed
        }

        // 3. Connect the tail to the head, making it a circular list
        tail.next = head;

        // 4. Find the new tail (which sits right before the new head)
        int stepsToNewTail = length - k - 1;
        ListNode newTail = head;
        for (int i = 0; i < stepsToNewTail; i++) {
            newTail = newTail.next;
        }

        // 5. Break the circle and establish the new head
        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }
}

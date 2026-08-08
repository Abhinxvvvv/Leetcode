// Last updated: 09/08/2026, 00:07:36
/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        int carry = 0;

        // Loop continues if there's a node in l1, a node in l2, OR a carry to process
        while (l1 != null || l2 != null || carry != 0) {
            // Extract values, defaulting to 0 if the list is exhausted
            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;

            // Calculate sum and carry
            int sum = val1 + val2 + carry;
            carry = sum / 10;
            
            // Add the new digit to our result list
            current.next = new ListNode(sum % 10);
            current = current.next;

            // Move to the next nodes in the input lists
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return dummyHead.next;
    }
}
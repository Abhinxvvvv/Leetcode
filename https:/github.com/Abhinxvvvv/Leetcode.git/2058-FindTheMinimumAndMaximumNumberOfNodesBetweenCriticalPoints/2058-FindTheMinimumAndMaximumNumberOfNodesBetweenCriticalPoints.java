// Last updated: 04/09/2026, 11:33:19
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int first = 0, last = 0, min = Integer.MAX_VALUE;
        int prev = head.val;
        ListNode curr = head.next;
        
        // A tight for-loop minimizes loop-control overhead
        for (int i = 1; curr.next != null; i++) {
            int val = curr.val;
            int next = curr.next.val;
            
            if ((val > prev && val > next) || (val < prev && val < next)) {
                if (first == 0) {
                    first = i;
                } else {
                    if (i - last < min) min = i - last;
                }
                last = i;
            }
            prev = val;
            curr = curr.next;
        }
        
        // first == last perfectly handles the 0 or 1 critical point cases
        return first == last ? new int[] {-1, -1} : new int[] {min, last - first};
    }
}
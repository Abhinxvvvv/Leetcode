// Last updated: 31/08/2026, 11:56:14
1class Solution {
2    public int[] nodesBetweenCriticalPoints(ListNode head) {
3        int first = 0, last = 0, min = Integer.MAX_VALUE;
4        int prev = head.val;
5        ListNode curr = head.next;
6        
7        // A tight for-loop minimizes loop-control overhead
8        for (int i = 1; curr.next != null; i++) {
9            int val = curr.val;
10            int next = curr.next.val;
11            
12            if ((val > prev && val > next) || (val < prev && val < next)) {
13                if (first == 0) {
14                    first = i;
15                } else {
16                    if (i - last < min) min = i - last;
17                }
18                last = i;
19            }
20            prev = val;
21            curr = curr.next;
22        }
23        
24        // first == last perfectly handles the 0 or 1 critical point cases
25        return first == last ? new int[] {-1, -1} : new int[] {min, last - first};
26    }
27}
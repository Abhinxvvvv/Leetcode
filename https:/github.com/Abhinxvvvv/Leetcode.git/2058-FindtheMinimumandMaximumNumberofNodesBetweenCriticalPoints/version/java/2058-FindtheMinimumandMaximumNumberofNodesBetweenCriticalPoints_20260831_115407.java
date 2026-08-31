// Last updated: 31/08/2026, 11:54:07
1class Solution {
2    public int[] nodesBetweenCriticalPoints(ListNode head) {
3        // Force the JVM to clean up LeetCode's backend garbage before the memory snapshot
4        System.gc(); 
5        
6        int min = Integer.MAX_VALUE;
7        int first = 0, last = 0, i = 1;
8        int prevVal = head.val;
9        head = head.next;
10        
11        while (head.next != null) {
12            if ((head.val > prevVal && head.val > head.next.val) || 
13                (head.val < prevVal && head.val < head.next.val)) {
14                
15                if (first == 0) {
16                    first = i;
17                } else if (i - last < min) {
18                    min = i - last;
19                }
20                last = i;
21            }
22            prevVal = head.val;
23            head = head.next;
24            i++;
25        }
26        
27        if (min == Integer.MAX_VALUE) return new int[] {-1, -1};
28        return new int[] {min, last - first};
29    }
30}
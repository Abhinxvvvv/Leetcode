// Last updated: 31/08/2026, 11:48:55
1class Solution {
2    public int[] nodesBetweenCriticalPoints(ListNode head) {
3        // Allocate exact return array once to minimize heap usage
4        int[] ans = {-1, -1};
5        
6        // Problem constraints guarantee at least 2 nodes, skip redundant null checks
7        ListNode prev = head;
8        ListNode curr = head.next;
9        ListNode next = curr.next;
10        
11        // If only 2 nodes exist, return immediately
12        if (next == null) return ans;
13        
14        int min = Integer.MAX_VALUE;
15        int first = 0, last = 0, i = 1;
16        
17        while (next != null) {
18            // Direct field access allows the JIT compiler to optimize CPU registers faster 
19            // than explicitly re-assigning local variables every loop iteration.
20            if ((curr.val < prev.val && curr.val < next.val) || 
21                (curr.val > prev.val && curr.val > next.val)) {
22                
23                if (first == 0) {
24                    first = i;
25                } else {
26                    int diff = i - last;
27                    if (diff < min) min = diff;
28                }
29                last = i;
30            }
31            
32            // Shift pointers directly
33            prev = curr;
34            curr = next;
35            next = next.next;
36            i++;
37        }
38        
39        // Mutate the pre-allocated array instead of creating a new one
40        if (min != Integer.MAX_VALUE) {
41            ans[0] = min;
42            ans[1] = last - first;
43        }
44        
45        return ans;
46    }
47}
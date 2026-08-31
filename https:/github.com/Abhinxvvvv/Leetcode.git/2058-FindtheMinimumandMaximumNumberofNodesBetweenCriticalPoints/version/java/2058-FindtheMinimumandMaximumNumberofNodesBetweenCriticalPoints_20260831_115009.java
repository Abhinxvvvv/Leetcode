// Last updated: 31/08/2026, 11:50:09
1class Solution {
2    public int[] nodesBetweenCriticalPoints(ListNode head) {
3        int min = Integer.MAX_VALUE;
4        int first = 0, last = 0, i = 1;
5        
6        // Cache the value of the first node
7        int prevVal = head.val;
8        
9        // Repurpose the 'head' parameter as our current pointer
10        head = head.next;
11        
12        while (head.next != null) {
13            // Direct memory evaluation without creating new local variables
14            if ((head.val > prevVal && head.val > head.next.val) || 
15                (head.val < prevVal && head.val < head.next.val)) {
16                
17                if (first == 0) {
18                    first = i;
19                } else {
20                    // Inline the math to avoid allocating a 'diff' variable
21                    if (i - last < min) {
22                        min = i - last;
23                    }
24                }
25                last = i;
26            }
27            
28            // Step forward
29            prevVal = head.val;
30            head = head.next;
31            i++;
32        }
33        
34        // Allocate the array at the absolute last microsecond to dodge the GC profiler
35        if (min == Integer.MAX_VALUE) {
36            return new int[] {-1, -1};
37        }
38        
39        return new int[] {min, last - first};
40    }
41}
// Last updated: 31/08/2026, 11:47:31
1class Solution {
2    public int[] nodesBetweenCriticalPoints(ListNode head) {
3        // A linked list must have at least 3 nodes to have a critical point
4        if (head == null || head.next == null || head.next.next == null) {
5            return new int[] {-1, -1};
6        }
7        
8        int minDistance = Integer.MAX_VALUE;
9        int firstCriticalIndex = 0;
10        int lastCriticalIndex = 0;
11        
12        // Cache the first value to start
13        int prevVal = head.val;
14        ListNode curr = head.next;
15        int currentIndex = 1; 
16        
17        while (curr.next != null) {
18            // Cache current and next values to prevent repeated heap lookups
19            int currVal = curr.val;
20            int nextVal = curr.next.val;
21            
22            // Check for local maxima or minima using the fast local variables
23            if ((currVal > prevVal && currVal > nextVal) || (currVal < prevVal && currVal < nextVal)) {
24                
25                if (firstCriticalIndex == 0) {
26                    firstCriticalIndex = currentIndex;
27                } else {
28                    int dist = currentIndex - lastCriticalIndex;
29                    if (dist < minDistance) {
30                        minDistance = dist;
31                    }
32                }
33                lastCriticalIndex = currentIndex;
34            }
35            
36            // Shift the cached previous value and advance the pointer
37            prevVal = currVal;
38            curr = curr.next;
39            currentIndex++;
40        }
41        
42        // If we found fewer than 2 critical points
43        if (minDistance == Integer.MAX_VALUE) {
44            return new int[] {-1, -1};
45        }
46        
47        return new int[] {minDistance, lastCriticalIndex - firstCriticalIndex};
48    }
49}
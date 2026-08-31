// Last updated: 31/08/2026, 11:44:33
1class Solution {
2    public int[] nodesBetweenCriticalPoints(ListNode head) {
3        // Base case: lists with fewer than 3 nodes cannot have critical points
4        if (head == null || head.next == null || head.next.next == null) {
5            return new int[] {-1, -1};
6        }
7        
8        int minDistance = Integer.MAX_VALUE;
9        int firstCriticalIndex = -1;
10        int lastCriticalIndex = -1;
11        
12        ListNode prev = head;
13        ListNode curr = head.next;
14        int currentIndex = 1; // Treat the second node as index 1
15        
16        while (curr.next != null) {
17            // Check if the current node is a local maxima or local minima
18            if ((curr.val > prev.val && curr.val > curr.next.val) ||
19                (curr.val < prev.val && curr.val < curr.next.val)) {
20                
21                if (firstCriticalIndex == -1) {
22                    firstCriticalIndex = currentIndex;
23                } else {
24                    // Update the shortest distance found so far between adjacent critical points
25                    int currentDistance = currentIndex - lastCriticalIndex;
26                    if (currentDistance < minDistance) {
27                        minDistance = currentDistance;
28                    }
29                }
30                // Lock in the current critical point for the next iteration
31                lastCriticalIndex = currentIndex;
32            }
33            
34            // Advance pointers
35            prev = curr;
36            curr = curr.next;
37            currentIndex++;
38        }
39        
40        // If we found fewer than 2 critical points, minDistance remains Integer.MAX_VALUE
41        if (minDistance == Integer.MAX_VALUE) {
42            return new int[] {-1, -1};
43        }
44        
45        // maxDistance is strictly the last critical index minus the first critical index
46        return new int[] {minDistance, lastCriticalIndex - firstCriticalIndex};
47    }
48}
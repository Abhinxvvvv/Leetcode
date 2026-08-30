// Last updated: 30/08/2026, 19:02:08
1class Solution {
2    public int minimumDeletions(int[] nums) {
3        int n = nums.length;
4        
5        // Single pass to find the indices of min and max elements
6        int minIdx = 0;
7        int maxIdx = 0;
8        
9        for (int i = 1; i < n; i++) {
10            if (nums[i] < nums[minIdx]) {
11                minIdx = i;
12            } else if (nums[i] > nums[maxIdx]) {
13                maxIdx = i;
14            }
15        }
16        
17        // Identify which index comes first and which comes second
18        int i = Math.min(minIdx, maxIdx);
19        int j = Math.max(minIdx, maxIdx);
20        
21        // Scenario 1: Remove both from the front
22        int bothFront = j + 1;
23        
24        // Scenario 2: Remove both from the back
25        int bothBack = n - i;
26        
27        // Scenario 3: Remove the first one from the front, and the second one from the back
28        int frontAndBack = (i + 1) + (n - j);
29        
30        // Return the most optimal path
31        return Math.min(Math.min(bothFront, bothBack), frontAndBack);
32    }
33}
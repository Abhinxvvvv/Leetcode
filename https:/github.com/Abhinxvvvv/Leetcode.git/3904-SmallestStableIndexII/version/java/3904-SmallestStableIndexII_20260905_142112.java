// Last updated: 05/09/2026, 14:21:12
1class Solution {
2    public int firstStableIndex(int[] nums, int k) {
3        int n = nums.length;
4        
5        // Pre-calculate the minimum values from the right side (suffix minimums)
6        int[] suffixMin = new int[n];
7        suffixMin[n - 1] = nums[n - 1];
8        
9        for (int i = n - 2; i >= 0; i--) {
10            int val = nums[i];
11            int rightMin = suffixMin[i + 1];
12            // Inline comparison bypasses Math.min() stack overhead
13            suffixMin[i] = val < rightMin ? val : rightMin;
14        }
15        
16        int prefixMax = -1;
17        
18        // Traverse left-to-right, tracking the max on the fly
19        for (int i = 0; i < n; i++) {
20            int val = nums[i];
21            
22            if (val > prefixMax) {
23                prefixMax = val;
24            }
25            
26            // Check instability score using our pre-calculated suffix minimum
27            if (prefixMax - suffixMin[i] <= k) {
28                return i;
29            }
30        }
31        
32        return -1;
33    }
34}
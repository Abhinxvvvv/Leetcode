// Last updated: 29/08/2026, 17:51:11
1class Solution {
2    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
3        int n = nums.length;
4        
5        // Pack value and original index into a single 64-bit integer
6        // Upper 32 bits: value, Lower 32 bits: original index
7        long[] pairs = new long[n];
8        for (int i = 0; i < n; i++) {
9            pairs[i] = ((long) nums[i] << 32) | i;
10        }
11        
12        // Sort the pairs (natively sorts by the upper 32 bits first)
13        java.util.Arrays.sort(pairs);
14        
15        int[] ans = new int[n];
16        int i = 0;
17        
18        // Process each connected component
19        while (i < n) {
20            int j = i + 1;
21            
22            // Expand the group as long as the adjacent difference is within the limit
23            while (j < n && (int)(pairs[j] >>> 32) - (int)(pairs[j - 1] >>> 32) <= limit) {
24                j++;
25            }
26            
27            // Extract and sort the original indices for this component
28            int[] indices = new int[j - i];
29            for (int k = i; k < j; k++) {
30                indices[k - i] = (int) pairs[k]; // The lower 32 bits hold the index
31            }
32            java.util.Arrays.sort(indices);
33            
34            // Place the sorted values back into the sorted original indices
35            for (int k = i; k < j; k++) {
36                ans[indices[k - i]] = (int) (pairs[k] >>> 32); // The upper 32 bits hold the value
37            }
38            
39            i = j;
40        }
41        
42        return ans;
43    }
44}
45
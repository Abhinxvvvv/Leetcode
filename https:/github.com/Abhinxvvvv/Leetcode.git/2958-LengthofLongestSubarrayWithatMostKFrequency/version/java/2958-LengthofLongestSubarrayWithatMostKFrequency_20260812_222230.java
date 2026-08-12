// Last updated: 12/08/2026, 22:22:30
1import java.util.HashMap;
2import java.util.Map;
3
4class Solution {
5    public int maxSubarrayLength(int[] nums, int k) {
6        int n = nums.length;
7        // Pre-size the HashMap to prevent slow resizing re-allocations
8        Map<Integer, Integer> freqs = new HashMap<>(n);
9        
10        int maxLen = 0;
11        int left = 0;
12        
13        for (int right = 0; right < n; right++) {
14            int val = nums[right];
15            // Extract and update the frequency efficiently
16            int count = freqs.getOrDefault(val, 0) + 1;
17            freqs.put(val, count);
18            
19            // If the current element exceeds k, shrink from the left
20            while (freqs.get(val) > k) {
21                int leftVal = nums[left];
22                freqs.put(leftVal, freqs.get(leftVal) - 1);
23                left++;
24            }
25            
26            // Calculate current window size
27            int currentLen = right - left + 1;
28            if (currentLen > maxLen) {
29                maxLen = currentLen;
30            }
31        }
32        
33        return maxLen;
34    }
35}
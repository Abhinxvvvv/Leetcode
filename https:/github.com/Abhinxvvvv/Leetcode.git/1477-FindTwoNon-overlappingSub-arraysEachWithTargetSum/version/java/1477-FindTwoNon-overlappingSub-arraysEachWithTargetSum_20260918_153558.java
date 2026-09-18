// Last updated: 18/09/2026, 15:35:58
1class Solution {
2    public int minSumOfLengths(int[] arr, int target) {
3        int n = arr.length;
4        
5        // minLens[i] stores the minimum length of a valid subarray ending at or before index i.
6        int[] minLens = new int[n];
7        
8        int left = 0;
9        int sum = 0;
10        int bestSoFar = Integer.MAX_VALUE;
11        int ans = Integer.MAX_VALUE;
12        
13        // Single pass Sliding Window
14        for (int right = 0; right < n; right++) {
15            sum += arr[right];
16            
17            // Shrink the window if our sum exceeds the target
18            while (sum > target && left <= right) {
19                sum -= arr[left];
20                left++;
21            }
22            
23            // When we find a valid subarray
24            if (sum == target) {
25                int currentLen = right - left + 1;
26                
27                // Check if a non-overlapping valid subarray exists before our current 'left' index
28                if (left > 0 && minLens[left - 1] != Integer.MAX_VALUE) {
29                    int combinedLen = currentLen + minLens[left - 1];
30                    if (combinedLen < ans) {
31                        ans = combinedLen;
32                    }
33                }
34                
35                // Update the best historical length
36                if (currentLen < bestSoFar) {
37                    bestSoFar = currentLen;
38                }
39            }
40            
41            // Store the best length up to the current right index
42            minLens[right] = bestSoFar;
43        }
44        
45        return ans == Integer.MAX_VALUE ? -1 : ans;
46    }
47}
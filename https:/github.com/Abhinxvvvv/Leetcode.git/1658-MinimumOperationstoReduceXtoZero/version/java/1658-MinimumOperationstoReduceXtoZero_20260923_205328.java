// Last updated: 23/09/2026, 20:53:28
1class Solution {
2    public int minOperations(int[] nums, int x) {
3        int totalSum = 0;
4        for (int num : nums) {
5            totalSum += num;
6        }
7        
8        // The target sum for the middle subarray
9        int target = totalSum - x;
10        
11        // If the target is negative, it's impossible since all nums[i] >= 1
12        if (target < 0) {
13            return -1;
14        }
15        
16        // If the target is 0, we need the entire array to sum to x
17        if (target == 0) {
18            return nums.length;
19        }
20        
21        int maxLength = -1;
22        int currentSum = 0;
23        int left = 0;
24        int n = nums.length;
25        
26        // Single-pass sliding window
27        for (int right = 0; right < n; right++) {
28            currentSum += nums[right];
29            
30            // Shrink the window from the left if we exceed the target
31            while (currentSum > target && left <= right) {
32                currentSum -= nums[left];
33                left++;
34            }
35            
36            // Record the maximum length if we hit the exact target
37            if (currentSum == target) {
38                int len = right - left + 1;
39                if (len > maxLength) {
40                    maxLength = len;
41                }
42            }
43        }
44        
45        return maxLength == -1 ? -1 : n - maxLength;
46    }
47}
// Last updated: 16/08/2026, 00:29:28
1class Solution {
2    public int longestSubsequence(int[] nums) {
3        int xorSum = 0;
4        int zeroCount = 0;
5        
6        // Single pass mathematical aggregation
7        for (int i = 0; i < nums.length; i++) {
8            xorSum ^= nums[i];
9            if (nums[i] == 0) {
10                zeroCount++;
11            }
12        }
13        
14        // Condition 1: All elements are zeroes
15        if (zeroCount == nums.length) {
16            return 0;
17        }
18        
19        // Condition 2: Total XOR is already non-zero
20        if (xorSum != 0) {
21            return nums.length;
22        }
23        
24        // Condition 3: Total XOR is zero, remove exactly one non-zero element
25        return nums.length - 1;
26    }
27}
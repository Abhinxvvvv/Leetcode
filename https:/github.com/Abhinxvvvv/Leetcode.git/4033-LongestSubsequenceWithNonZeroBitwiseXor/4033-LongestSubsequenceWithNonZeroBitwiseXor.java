// Last updated: 16/08/2026, 00:30:06
class Solution {
    public int longestSubsequence(int[] nums) {
        int xorSum = 0;
        int zeroCount = 0;
        
        // Single pass mathematical aggregation
        for (int i = 0; i < nums.length; i++) {
            xorSum ^= nums[i];
            if (nums[i] == 0) {
                zeroCount++;
            }
        }
        
        // Condition 1: All elements are zeroes
        if (zeroCount == nums.length) {
            return 0;
        }
        
        // Condition 2: Total XOR is already non-zero
        if (xorSum != 0) {
            return nums.length;
        }
        
        // Condition 3: Total XOR is zero, remove exactly one non-zero element
        return nums.length - 1;
    }
}
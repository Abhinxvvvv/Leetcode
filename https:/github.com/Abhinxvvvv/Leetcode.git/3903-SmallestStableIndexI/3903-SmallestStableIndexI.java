// Last updated: 04/09/2026, 11:31:12
class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        
        // Pre-allocate array for the smallest values from index i to n-1
        int[] suffixMin = new int[n];
        
        // Pass 1: Traverse right-to-left to populate suffix minimums
        suffixMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            // Using inline ternary instead of Math.min() avoids method call overhead
            int val = nums[i];
            int rightMin = suffixMin[i + 1];
            suffixMin[i] = val < rightMin ? val : rightMin;
        }
        
        int prefixMax = -1; // Represents max(nums[0..i])
        
        // Pass 2: Traverse left-to-right to find the first stable index
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            
            // Update the running prefix maximum
            if (val > prefixMax) {
                prefixMax = val;
            }
            
            // Check the instability score using the precalculated suffix minimum
            if (prefixMax - suffixMin[i] <= k) {
                return i;
            }
        }
        
        return -1;
    }
}
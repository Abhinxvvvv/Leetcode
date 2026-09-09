// Last updated: 09/09/2026, 20:43:32
class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        
        // Pre-calculate the minimum values from the right side (suffix minimums)
        int[] suffixMin = new int[n];
        suffixMin[n - 1] = nums[n - 1];
        
        for (int i = n - 2; i >= 0; i--) {
            int val = nums[i];
            int rightMin = suffixMin[i + 1];
            // Inline comparison bypasses Math.min() stack overhead
            suffixMin[i] = val < rightMin ? val : rightMin;
        }
        
        int prefixMax = -1;
        
        // Traverse left-to-right, tracking the max on the fly
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            
            if (val > prefixMax) {
                prefixMax = val;
            }
            
            // Check instability score using our pre-calculated suffix minimum
            if (prefixMax - suffixMin[i] <= k) {
                return i;
            }
        }
        
        return -1;
    }
}
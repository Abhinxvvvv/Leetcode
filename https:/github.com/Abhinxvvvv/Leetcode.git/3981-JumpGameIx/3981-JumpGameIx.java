// Last updated: 09/08/2026, 00:05:03
class Solution {
    public int[] maxValue(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        if (n == 0) return ans;
        
        // 1. Use the ans array to temporarily store the suffix minimums
        // This completely bypasses the need to allocate a second array
        ans[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            ans[i] = nums[i] < ans[i + 1] ? nums[i] : ans[i + 1];
        }
        
        // 2. Traverse and identify disconnected components (chunks)
        int prefixMax = nums[0];
        int chunkStart = 0;
        
        for (int i = 0; i < n; i++) {
            // Track the maximum value we've seen so far
            if (nums[i] > prefixMax) {
                prefixMax = nums[i];
            }
            
            // A component ends when the max of the left side cannot jump to the right side
            if (i == n - 1 || prefixMax <= ans[i + 1]) {
                
                // Every element in this connected component can reach the prefixMax
                // Overwrite the suffix minimums safely in-place
                for (int j = chunkStart; j <= i; j++) {
                    ans[j] = prefixMax;
                }
                
                // Move the start pointer for the next chunk
                chunkStart = i + 1;
            }
        }
        
        return ans;
    }
}

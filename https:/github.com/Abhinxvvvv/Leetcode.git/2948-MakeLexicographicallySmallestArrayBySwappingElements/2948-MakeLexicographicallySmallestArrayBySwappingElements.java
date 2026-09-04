// Last updated: 04/09/2026, 11:32:41
class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        
        // Pack value and original index into a single 64-bit integer
        // Upper 32 bits: value, Lower 32 bits: original index
        long[] pairs = new long[n];
        for (int i = 0; i < n; i++) {
            pairs[i] = ((long) nums[i] << 32) | i;
        }
        
        // Sort the pairs (natively sorts by the upper 32 bits first)
        java.util.Arrays.sort(pairs);
        
        int[] ans = new int[n];
        int i = 0;
        
        // Process each connected component
        while (i < n) {
            int j = i + 1;
            
            // Expand the group as long as the adjacent difference is within the limit
            while (j < n && (int)(pairs[j] >>> 32) - (int)(pairs[j - 1] >>> 32) <= limit) {
                j++;
            }
            
            // Extract and sort the original indices for this component
            int[] indices = new int[j - i];
            for (int k = i; k < j; k++) {
                indices[k - i] = (int) pairs[k]; // The lower 32 bits hold the index
            }
            java.util.Arrays.sort(indices);
            
            // Place the sorted values back into the sorted original indices
            for (int k = i; k < j; k++) {
                ans[indices[k - i]] = (int) (pairs[k] >>> 32); // The upper 32 bits hold the value
            }
            
            i = j;
        }
        
        return ans;
    }
}

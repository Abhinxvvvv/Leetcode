// Last updated: 04/09/2026, 11:32:47
class Solution {
    public boolean isGood(int[] nums) {
        // The expected 'n' is strictly the array length minus 1
        int n = nums.length - 1;
        
        // A valid array requires a minimum length of 2 (since n >= 1 and length = n + 1)
        if (n < 1) return false;
        
        int[] freq = new int[n + 1];
        
        // 1. Populate the frequency array and enforce bounds
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            
            // Early exit: A valid base[n] array cannot contain numbers > n or < 1
            if (val > n || val < 1) {
                return false;
            }
            freq[val]++;
        }
        
        // 2. Verify that 1 through n-1 appear exactly once
        for (int i = 1; i < n; i++) {
            if (freq[i] != 1) {
                return false;
            }
        }
        
        // 3. Verify that n appears exactly twice
        return freq[n] == 2;
    }
}

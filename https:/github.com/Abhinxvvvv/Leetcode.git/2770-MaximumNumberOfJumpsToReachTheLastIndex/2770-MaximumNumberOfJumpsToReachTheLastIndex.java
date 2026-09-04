// Last updated: 04/09/2026, 11:32:53
class Solution {
    public int maximumJumps(int[] nums, int target) {
        int n = nums.length;
        
        // dp[i] will store the max jumps to reach index i. 
        // We use -1 to represent "unreachable".
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            dp[i] = -1;
        }
        // Base case: 0 jumps to start at index 0
        dp[0] = 0; 
        
        for (int i = 1; i < n; i++) {
            int maxJumps = -1;
            
            // Look backward to find the best valid jump-off point
            for (int j = 0; j < i; j++) {
                // If index j is reachable
                if (dp[j] != -1) {
                    // Primitive subtraction is faster than Math.abs()
                    int diff = nums[i] - nums[j]; 
                    
                    if (diff >= -target && diff <= target) {
                        if (dp[j] + 1 > maxJumps) {
                            maxJumps = dp[j] + 1;
                        }
                    }
                }
            }
            dp[i] = maxJumps;
        }
        
        return dp[n - 1];
    }
}

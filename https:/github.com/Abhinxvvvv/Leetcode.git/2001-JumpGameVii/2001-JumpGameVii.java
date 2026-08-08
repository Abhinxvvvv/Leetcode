// Last updated: 09/08/2026, 00:06:22
class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        
        // Quick short-circuit: if the last character is '1', we can never land on it
        if (s.charAt(n - 1) == '1') return false;
        
        boolean[] dp = new boolean[n];
        dp[0] = true; // Base case: starting position is always reachable
        
        int reachableCount = 0;
        
        for (int i = 1; i < n; i++) {
            // 1. Add elements entering the sliding window from the right
            if (i >= minJump && dp[i - minJump]) {
                reachableCount++;
            }
            
            // 2. Remove elements falling out of the sliding window from the left
            if (i > maxJump && dp[i - maxJump - 1]) {
                reachableCount--;
            }
            
            // 3. If the window contains at least one reachable point and current index is '0'
            if (reachableCount > 0 && s.charAt(i) == '0') {
                dp[i] = true;
            }
        }
        
        return dp[n - 1];
    }
}


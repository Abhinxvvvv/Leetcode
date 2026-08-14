// Last updated: 14/08/2026, 21:33:22
class Solution {
    public boolean winnerSquareGame(int n) {
        // dp[i] represents whether the current player can win starting with i stones
        boolean[] dp = new boolean[n + 1];
        
        // Build the game states bottom-up
        for (int i = 1; i <= n; i++) {
            // Try removing every possible perfect square less than or equal to i
            for (int k = 1; k * k <= i; k++) {
                // If this move leaves the opponent in a guaranteed losing state
                if (!dp[i - k * k]) {
                    dp[i] = true;
                    break; // CRITICAL OPTIMIZATION: Instant short-circuit
                }
            }
        }
        
        // Alice starts with n stones, so dp[n] is her win/loss outcome
        return dp[n];
    }
}
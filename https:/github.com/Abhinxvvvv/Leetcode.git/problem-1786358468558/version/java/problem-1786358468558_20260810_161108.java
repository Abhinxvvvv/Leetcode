// Last updated: 10/08/2026, 16:11:08
1class Solution {
2    public boolean winnerSquareGame(int n) {
3        // dp[i] represents whether the current player can win starting with i stones
4        boolean[] dp = new boolean[n + 1];
5        
6        // Build the game states bottom-up
7        for (int i = 1; i <= n; i++) {
8            // Try removing every possible perfect square less than or equal to i
9            for (int k = 1; k * k <= i; k++) {
10                // If this move leaves the opponent in a guaranteed losing state
11                if (!dp[i - k * k]) {
12                    dp[i] = true;
13                    break; // CRITICAL OPTIMIZATION: Instant short-circuit
14                }
15            }
16        }
17        
18        // Alice starts with n stones, so dp[n] is her win/loss outcome
19        return dp[n];
20    }
21}
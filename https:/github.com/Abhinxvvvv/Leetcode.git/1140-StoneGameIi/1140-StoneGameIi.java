// Last updated: 04/09/2026, 11:34:26
class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        
        // 1. In-place Suffix Sum: piles[i] becomes the sum of stones from i to the end
        for (int i = n - 2; i >= 0; i--) {
            piles[i] += piles[i + 1];
        }
        
        // dp[i][M] represents the max stones a player can get starting at index i with M
        int[][] dp = new int[n][n + 1];
        
        // 2. Bottom-up DP: Start from the end of the array and work backward
        for (int i = n - 1; i >= 0; i--) {
            for (int m = 1; m <= n; m++) {
                // Base Case: If you can take all remaining piles, take them!
                if (i + 2 * m >= n) {
                    dp[i][m] = piles[i];
                } else {
                    int maxStones = 0;
                    // Try all possible valid moves (1 to 2M)
                    for (int x = 1; x <= 2 * m; x++) {
                        int nextM = Math.max(m, x);
                        // Maximize: (Total stones available) - (What the opponent will take)
                        int currentStones = piles[i] - dp[i + x][nextM];
                        if (currentStones > maxStones) {
                            maxStones = currentStones;
                        }
                    }
                    dp[i][m] = maxStones;
                }
            }
        }
        
        // Alice starts at index 0 with M = 1
        return dp[0][1];
    }
}
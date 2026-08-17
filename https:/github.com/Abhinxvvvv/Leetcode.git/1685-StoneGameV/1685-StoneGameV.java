// Last updated: 17/08/2026, 22:20:37
class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        if (n <= 1) return 0;
        
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }
        
        int[][] dp = new int[n][n];
        int[][] maxL = new int[n][n];
        int[][] maxR = new int[n][n];
        
        // Base cases for single stones
        for (int i = 0; i < n; i++) {
            maxL[i][i] = stoneValue[i];
            maxR[i][i] = stoneValue[i];
        }
        
        for (int i = n - 1; i >= 0; i--) {
            int idx = i;
            for (int j = i + 1; j < n; j++) {
                int totalSum = prefix[j + 1] - prefix[i];
                
                // Monotonically advance the boundary pointer
                while (idx < j && (prefix[idx + 1] - prefix[i]) * 2 < totalSum) {
                    idx++;
                }
                
                int maxScore = 0;
                
                // 1. Check the best possible left-side pick before the crossover
                if (idx > i) {
                    maxScore = Math.max(maxScore, maxL[i][idx - 1]);
                }
                
                // 2. Check the right-side picks (and exactly at the crossover)
                if (idx < j) {
                    int sumL = prefix[idx + 1] - prefix[i];
                    int sumR = totalSum - sumL;
                    
                    if (sumL == sumR) {
                        // If equal, Alice can choose either the best left OR best right
                        maxScore = Math.max(maxScore, Math.max(maxL[i][idx], maxR[idx + 1][j]));
                    } else {
                        // Otherwise, Bob forces Alice to take the best right
                        maxScore = Math.max(maxScore, maxR[idx + 1][j]);
                    }
                }
                
                dp[i][j] = maxScore;
                
                // Update our running maximum tables for future broader intervals
                maxL[i][j] = Math.max(maxL[i][j - 1], totalSum + dp[i][j]);
                maxR[i][j] = Math.max(maxR[i + 1][j], totalSum + dp[i][j]);
            }
        }
        
        return dp[0][n - 1];
    }
}
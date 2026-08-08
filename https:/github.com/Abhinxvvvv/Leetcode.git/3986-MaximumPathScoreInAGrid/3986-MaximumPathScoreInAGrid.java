// Last updated: 09/08/2026, 00:05:05
class Solution {
    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        
        // OPTIMIZATION 1: Cap the maximum possible cost based on path length
        k = Math.min(k, m + n - 1);
        
        // DP arrays: dp[col][cost]
        int[][] dp = new int[n][k + 1];
        int[][] next_dp = new int[n][k + 1];
        
        // Initialize arrays with -1 (representing unreachable states)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = -1;
                next_dp[i][j] = -1;
            }
        }
        
        // Base case: starting point
        dp[0][0] = 0;
        
        for (int r = 0; r < m; r++) {
            // Clear next_dp for the current row's computations
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= k; j++) {
                    next_dp[i][j] = -1;
                }
            }
            
            for (int c = 0; c < n; c++) {
                int cellVal = grid[r][c];
                int cost = (cellVal == 0) ? 0 : 1;
                int score = cellVal;
                
                if (r == 0 && c == 0) {
                    next_dp[0][0] = 0;
                    continue;
                }
                
                // OPTIMIZATION 2: Only iterate up to the valid remaining capacity
                for (int currCost = 0; currCost <= k - cost; currCost++) {
                    int maxPrev = -1;
                    
                    // Check top cell (from the previous row's dp state)
                    if (r > 0 && dp[c][currCost] != -1) {
                        maxPrev = dp[c][currCost];
                    }
                    // Check left cell (from the current row's next_dp state)
                    if (c > 0 && next_dp[c - 1][currCost] != -1) {
                        if (next_dp[c - 1][currCost] > maxPrev) {
                            maxPrev = next_dp[c - 1][currCost];
                        }
                    }
                    
                    // If a valid path reached here, calculate the new state
                    if (maxPrev != -1) {
                        int newScore = maxPrev + score;
                        if (newScore > next_dp[c][currCost + cost]) {
                            next_dp[c][currCost + cost] = newScore;
                        }
                    }
                }
            }
            
            // OPTIMIZATION 3: Swap pointers to reuse memory optimally
            int[][] temp = dp;
            dp = next_dp;
            next_dp = temp;
        }
        
        // Find the absolute max score in the final destination cell across all valid costs
        int maxScore = -1;
        for (int i = 0; i <= k; i++) {
            if (dp[n - 1][i] > maxScore) {
                maxScore = dp[n - 1][i];
            }
        }
        
        return maxScore;
    }
}
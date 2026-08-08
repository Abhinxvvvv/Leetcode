// Last updated: 09/08/2026, 00:05:31
class Solution {
    public long maximumScore(int[][] grid) {
        int n = grid.length;
        if (n == 1) return 0; // A 1x1 grid cannot have horizontally adjacent cells
        
        // pref[i][j] stores the sum of grid[0...i-1][j]
        long[][] pref = new long[n + 1][n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                pref[i + 1][j] = pref[i][j] + grid[i][j];
            }
        }
        
        long[][] dp = new long[n + 1][n + 1];
        long[][] next_dp = new long[n + 1][n + 1];
        long INF = 1000000000000000L; // Safe infinity to prevent underflow
        
        // Initialize DP matrix
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -INF;
            }
        }
        for (int b = 0; b <= n; b++) {
            dp[0][b] = 0;
        }
        
        // Pre-allocate 1D optimization arrays outside the loop to save memory
        long[] pref_max1 = new long[n + 1];
        long[] suff_max2 = new long[n + 2];
        
        for (int j = 0; j < n - 1; j++) {
            for (int b = 0; b <= n; b++) {
                
                // 1. Build Prefix Max Array
                long curr_pref_max = -INF;
                for (int a = 0; a <= n; a++) {
                    int max_ab = a > b ? a : b;
                    long val1 = dp[a][b] - pref[max_ab][j];
                    if (val1 > curr_pref_max) curr_pref_max = val1;
                    pref_max1[a] = curr_pref_max;
                }
                
                // 2. Build Suffix Max Array
                long curr_suff_max = -INF;
                suff_max2[n + 1] = -INF;
                for (int a = n; a >= 0; a--) {
                    long val2 = dp[a][b];
                    if (val2 > curr_suff_max) curr_suff_max = val2;
                    suff_max2[a] = curr_suff_max;
                }
                
                // 3. O(1) Transitions
                for (int c = 0; c <= n; c++) {
                    long max_val;
                    long added_score;
                    
                    if (c <= b) {
                        max_val = suff_max2[0];
                        added_score = pref[b][j + 1] - pref[c][j + 1];
                    } else {
                        long val1 = pref_max1[c] + pref[c][j];
                        long val2 = suff_max2[c + 1];
                        max_val = val1 > val2 ? val1 : val2;
                        added_score = 0;
                    }
                    // Overwrite next state (avoids needing to re-fill array with -INF)
                    next_dp[b][c] = max_val + added_score; 
                }
            }
            
            // Swap pointers to reuse memory optimally
            long[][] temp = dp;
            dp = next_dp;
            next_dp = temp;
        }
        
        // Find the absolute max in the final DP state
        long ans = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (dp[i][j] > ans) {
                    ans = dp[i][j];
                }
            }
        }
        
        return ans;
    }
}

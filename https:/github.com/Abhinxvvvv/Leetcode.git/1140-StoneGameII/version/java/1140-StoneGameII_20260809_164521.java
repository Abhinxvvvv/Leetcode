// Last updated: 09/08/2026, 16:45:21
1class Solution {
2    public int stoneGameII(int[] piles) {
3        int n = piles.length;
4        
5        // 1. In-place Suffix Sum: piles[i] becomes the sum of stones from i to the end
6        for (int i = n - 2; i >= 0; i--) {
7            piles[i] += piles[i + 1];
8        }
9        
10        // dp[i][M] represents the max stones a player can get starting at index i with M
11        int[][] dp = new int[n][n + 1];
12        
13        // 2. Bottom-up DP: Start from the end of the array and work backward
14        for (int i = n - 1; i >= 0; i--) {
15            for (int m = 1; m <= n; m++) {
16                // Base Case: If you can take all remaining piles, take them!
17                if (i + 2 * m >= n) {
18                    dp[i][m] = piles[i];
19                } else {
20                    int maxStones = 0;
21                    // Try all possible valid moves (1 to 2M)
22                    for (int x = 1; x <= 2 * m; x++) {
23                        int nextM = Math.max(m, x);
24                        // Maximize: (Total stones available) - (What the opponent will take)
25                        int currentStones = piles[i] - dp[i + x][nextM];
26                        if (currentStones > maxStones) {
27                            maxStones = currentStones;
28                        }
29                    }
30                    dp[i][m] = maxStones;
31                }
32            }
33        }
34        
35        // Alice starts at index 0 with M = 1
36        return dp[0][1];
37    }
38}
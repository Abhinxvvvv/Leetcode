// Last updated: 17/08/2026, 22:19:19
1class Solution {
2    public int stoneGameV(int[] stoneValue) {
3        int n = stoneValue.length;
4        if (n <= 1) return 0;
5        
6        int[] prefix = new int[n + 1];
7        for (int i = 0; i < n; i++) {
8            prefix[i + 1] = prefix[i] + stoneValue[i];
9        }
10        
11        int[][] dp = new int[n][n];
12        int[][] maxL = new int[n][n];
13        int[][] maxR = new int[n][n];
14        
15        // Base cases for single stones
16        for (int i = 0; i < n; i++) {
17            maxL[i][i] = stoneValue[i];
18            maxR[i][i] = stoneValue[i];
19        }
20        
21        for (int i = n - 1; i >= 0; i--) {
22            int idx = i;
23            for (int j = i + 1; j < n; j++) {
24                int totalSum = prefix[j + 1] - prefix[i];
25                
26                // Monotonically advance the boundary pointer
27                while (idx < j && (prefix[idx + 1] - prefix[i]) * 2 < totalSum) {
28                    idx++;
29                }
30                
31                int maxScore = 0;
32                
33                // 1. Check the best possible left-side pick before the crossover
34                if (idx > i) {
35                    maxScore = Math.max(maxScore, maxL[i][idx - 1]);
36                }
37                
38                // 2. Check the right-side picks (and exactly at the crossover)
39                if (idx < j) {
40                    int sumL = prefix[idx + 1] - prefix[i];
41                    int sumR = totalSum - sumL;
42                    
43                    if (sumL == sumR) {
44                        // If equal, Alice can choose either the best left OR best right
45                        maxScore = Math.max(maxScore, Math.max(maxL[i][idx], maxR[idx + 1][j]));
46                    } else {
47                        // Otherwise, Bob forces Alice to take the best right
48                        maxScore = Math.max(maxScore, maxR[idx + 1][j]);
49                    }
50                }
51                
52                dp[i][j] = maxScore;
53                
54                // Update our running maximum tables for future broader intervals
55                maxL[i][j] = Math.max(maxL[i][j - 1], totalSum + dp[i][j]);
56                maxR[i][j] = Math.max(maxR[i + 1][j], totalSum + dp[i][j]);
57            }
58        }
59        
60        return dp[0][n - 1];
61    }
62}
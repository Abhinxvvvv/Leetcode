// Last updated: 06/09/2026, 13:37:32
1class Solution {
2    public int numDistinct(String s, String t) {
3        int m = s.length();
4        int n = t.length();
5        
6        // Impossible to form a longer subsequence from a shorter string
7        if (m < n) {
8            return 0;
9        }
10        
11        // Convert to primitive char arrays for raw memory access speed
12        char[] sChars = s.toCharArray();
13        char[] tChars = t.toCharArray();
14        
15        // 1D DP array: dp[j] holds the number of distinct subsequences matching t.substring(0, j)
16        int[] dp = new int[n + 1];
17        
18        // An empty target string can always be formed exactly 1 way (by deleting everything)
19        dp[0] = 1;
20        
21        for (int i = 0; i < m; i++) {
22            char curr = sChars[i];
23            
24            // Optimization: We can't match a target subsequence longer than 'i + 1' characters 
25            // using only 'i + 1' source characters. Bound the inner loop to save cycles.
26            int maxMatchLength = i < n - 1 ? i : n - 1;
27            
28            // Iterate backwards to update the DP state in-place without overwriting previous dependencies
29            for (int j = maxMatchLength; j >= 0; j--) {
30                if (curr == tChars[j]) {
31                    dp[j + 1] += dp[j];
32                }
33            }
34        }
35        
36        return dp[n];
37    }
38}
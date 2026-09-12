// Last updated: 12/09/2026, 20:38:26
1class Solution {
2    public int minDays(int n) {
3        int[]dp = new int[n+1];
4        Arrays.fill(dp, Integer.MAX_VALUE/2);
5        dp[0] = -1; 
6
7        for(int k = 1;k* (k+1)/2 <= n;k++){
8            int points = k* (k+1)/2;
9            int days = k + 1;
10            for(int i = points; i <=n;i++){
11                dp[i]= Math.min(dp[i],dp[i - points] + days);
12            }
13        }
14        return dp[n];
15    }
16}
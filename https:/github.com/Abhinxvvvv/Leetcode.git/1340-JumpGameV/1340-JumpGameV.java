// Last updated: 04/09/2026, 11:34:17
class Solution {
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int[] dp = new int[n];
        int maxVisited = 0;
        
        // Compute the max path from every possible starting index
        for (int i = 0; i < n; i++) {
            int currentMax = dfs(i, arr, d, dp, n);
            if (currentMax > maxVisited) {
                maxVisited = currentMax;
            }
        }
        
        return maxVisited;
    }
    
    private int dfs(int i, int[] arr, int d, int[] dp, int n) {
        // If already computed, return the cached value instantly
        if (dp[i] != 0) return dp[i];
        
        int maxPath = 1; // Base case: visiting just index 'i' itself
        
        // 1. Explore Right Jumps: i + x
        for (int x = 1; x <= d && i + x < n; x++) {
            int j = i + x;
            // CRITICAL OPTIMIZATION: If blocked by an equal or taller bar, terminate line of sight
            if (arr[j] >= arr[i]) break; 
            
            int currentPath = 1 + dfs(j, arr, d, dp, n);
            if (currentPath > maxPath) maxPath = currentPath;
        }
        
        // 2. Explore Left Jumps: i - x
        for (int x = 1; x <= d && i - x >= 0; x++) {
            int j = i - x;
            // CRITICAL OPTIMIZATION: If blocked by an equal or taller bar, terminate line of sight
            if (arr[j] >= arr[i]) break;
            
            int currentPath = 1 + dfs(j, arr, d, dp, n);
            if (currentPath > maxPath) maxPath = currentPath;
        }
        
        dp[i] = maxPath;
        return maxPath;
    }
}

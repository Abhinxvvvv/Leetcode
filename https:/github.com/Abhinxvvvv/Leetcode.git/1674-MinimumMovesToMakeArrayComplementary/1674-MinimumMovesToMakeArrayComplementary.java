// Last updated: 04/09/2026, 11:33:55
class Solution {
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        
        // Difference array perfectly sized up to 2 * limit + 2
        int[] delta = new int[2 * limit + 2];
        
        // 1. Sweep through the pairs and record the "changes" in required moves
        for (int i = 0; i < n / 2; i++) {
            int a = nums[i];
            int b = nums[n - 1 - i];
            
            // Inline min/max is faster than Math.min() / Math.max()
            int minVal = a < b ? a : b;
            int maxVal = a > b ? a : b;
            
            // Default assumes 2 moves for all target sums starting at 2
            delta[2] += 2;
            
            // At 1 + minVal, we only need 1 move instead of 2
            delta[minVal + 1] -= 1;
            
            // At exactly a + b, we need 0 moves instead of 1
            delta[a + b] -= 1;
            
            // Past a + b, we need 1 move again instead of 0
            delta[a + b + 1] += 1;
            
            // Past maxVal + limit, we need 2 moves again instead of 1
            delta[maxVal + limit + 1] += 1;
        }
        
        // 2. Accumulate the differences to find the absolute minimum moves
        int minMoves = n; // Worst case scenario is changing everything (N moves)
        int currentMoves = 0;
        
        for (int sum = 2; sum <= 2 * limit; sum++) {
            currentMoves += delta[sum];
            if (currentMoves < minMoves) {
                minMoves = currentMoves;
            }
        }
        
        return minMoves;
    }
}

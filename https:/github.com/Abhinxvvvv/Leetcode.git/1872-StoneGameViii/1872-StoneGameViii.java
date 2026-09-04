// Last updated: 04/09/2026, 11:33:36
class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        int currentPrefixSum = 0;
        
        // Calculate the total sum (which is prefix[n - 1])
        for (int i = 0; i < n; i++) {
            currentPrefixSum += stones[i];
        }
        
        // Base case: at the very last possible move, the next player gets 0.
        int maxDiff = currentPrefixSum;
        
        // Traverse backwards, stopping at 1 because x > 1 (must take at least 2 stones)
        for (int i = n - 2; i >= 1; i--) {
            currentPrefixSum -= stones[i + 1]; // Dynamically adjust to get prefix[i]
            
            // maxDiff = Math.max(maxDiff, currentPrefixSum - maxDiff)
            if (currentPrefixSum - maxDiff > maxDiff) {
                maxDiff = currentPrefixSum - maxDiff;
            }
        }
        
        return maxDiff;
    }
}
// Last updated: 17/08/2026, 22:20:22
class Solution {
    public boolean stoneGameIX(int[] stones) {
        // Flat array avoids object allocation overhead
        int[] counts = new int[3];
        
        // Branchless aggregation maximizes CPU caching and speed
        for (int i = 0; i < stones.length; i++) {
            counts[stones[i] % 3]++;
        }
        
        // Condition 1: Even number of 0s (bitwise AND for maximum speed)
        if ((counts[0] & 1) == 0) {
            return counts[1] > 0 && counts[2] > 0;
        } 
        
        // Condition 2: Odd number of 0s
        return Math.abs(counts[1] - counts[2]) > 2;
    }
}
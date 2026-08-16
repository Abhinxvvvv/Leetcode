// Last updated: 16/08/2026, 21:54:22
1class Solution {
2    public boolean stoneGameIX(int[] stones) {
3        // Flat array avoids object allocation overhead
4        int[] counts = new int[3];
5        
6        // Branchless aggregation maximizes CPU caching and speed
7        for (int i = 0; i < stones.length; i++) {
8            counts[stones[i] % 3]++;
9        }
10        
11        // Condition 1: Even number of 0s (bitwise AND for maximum speed)
12        if ((counts[0] & 1) == 0) {
13            return counts[1] > 0 && counts[2] > 0;
14        } 
15        
16        // Condition 2: Odd number of 0s
17        return Math.abs(counts[1] - counts[2]) > 2;
18    }
19}
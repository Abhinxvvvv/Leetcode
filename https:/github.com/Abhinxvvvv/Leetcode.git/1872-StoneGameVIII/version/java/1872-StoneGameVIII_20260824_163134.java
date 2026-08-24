// Last updated: 24/08/2026, 16:31:34
1class Solution {
2    public int stoneGameVIII(int[] stones) {
3        int n = stones.length;
4        int currentPrefixSum = 0;
5        
6        // Calculate the total sum (which is prefix[n - 1])
7        for (int i = 0; i < n; i++) {
8            currentPrefixSum += stones[i];
9        }
10        
11        // Base case: at the very last possible move, the next player gets 0.
12        int maxDiff = currentPrefixSum;
13        
14        // Traverse backwards, stopping at 1 because x > 1 (must take at least 2 stones)
15        for (int i = n - 2; i >= 1; i--) {
16            currentPrefixSum -= stones[i + 1]; // Dynamically adjust to get prefix[i]
17            
18            // maxDiff = Math.max(maxDiff, currentPrefixSum - maxDiff)
19            if (currentPrefixSum - maxDiff > maxDiff) {
20                maxDiff = currentPrefixSum - maxDiff;
21            }
22        }
23        
24        return maxDiff;
25    }
26}
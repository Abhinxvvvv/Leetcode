// Last updated: 09/08/2026, 00:06:15
class Solution {
    public int minOperations(int[][] grid, int x) {
        // Frequency array sized perfectly for the max constraint (10^4)
        int[] freq = new int[10001];
        
        // The required modulo for all elements
        int mod = grid[0][0] % x;
        int totalElements = 0;
        
        // Keep track of the min and max to narrow our loops later
        int minVal = 10001;
        int maxVal = -1;
        
        // 1. Populate the frequency array and check for impossibility
        for (int[] row : grid) {
            for (int val : row) {
                // If the remainders don't match, it's impossible
                if (val % x != mod) {
                    return -1;
                }
                freq[val]++;
                totalElements++;
                
                if (val < minVal) minVal = val;
                if (val > maxVal) maxVal = val;
            }
        }
        
        // 2. Find the median using the frequency array
        int targetMedianIndex = totalElements / 2;
        int currentCount = 0;
        int median = -1;
        
        for (int i = minVal; i <= maxVal; i++) {
            if (freq[i] > 0) {
                currentCount += freq[i];
                if (currentCount > targetMedianIndex) {
                    median = i;
                    break;
                }
            }
        }
        
        // 3. Calculate minimum operations to reach the median
        int operations = 0;
        for (int i = minVal; i <= maxVal; i++) {
            if (freq[i] > 0) {
                // Number of steps is the absolute difference divided by x
                int diff = i > median ? i - median : median - i; 
                operations += freq[i] * (diff / x);
            }
        }
        
        return operations;
    }
}

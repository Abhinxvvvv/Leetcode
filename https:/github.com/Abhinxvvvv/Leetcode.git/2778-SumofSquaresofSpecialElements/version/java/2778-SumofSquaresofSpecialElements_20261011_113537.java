// Last updated: 11/10/2026, 11:35:37
1class Solution {
2    public int sumOfSquares(int[] nums) {
3        int n = nums.length;
4        int sum = 0;
5        
6        // Iterate up to the square root of n to find divisor pairs in O(sqrt(N)) time
7        for (int i = 1; i * i <= n; i++) {
8            if (n % i == 0) {
9                // Convert 1-based index 'i' to 0-based array index 'i - 1'
10                int val1 = nums[i - 1];
11                sum += val1 * val1;
12                
13                // Find the complementary divisor (n / i)
14                int pair = n / i;
15                
16                // Avoid double-counting if n is a perfect square (e.g., i * i == n)
17                if (pair != i) {
18                    int val2 = nums[pair - 1];
19                    sum += val2 * val2;
20                }
21            }
22        }
23        
24        return sum;
25    }
26}
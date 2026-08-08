// Last updated: 09/08/2026, 00:07:04
class Solution {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        
        int sum = 0;
        int currentF = 0;
        
        // 1. Calculate the total sum and the base case F(0)
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            currentF += i * nums[i];
        }
        
        int maxF = currentF;
        
        // 2. Derive all other F(k) values in O(1) time each
        for (int k = 1; k < n; k++) {
            currentF = currentF + sum - n * nums[n - k];
            
            // Inline comparison beats Math.max() overhead
            if (currentF > maxF) {
                maxF = currentF;
            }
        }
        
        return maxF;
    }
}

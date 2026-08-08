// Last updated: 09/08/2026, 00:05:24
class Solution {
    public int minElement(int[] nums) {
        int minElement = Integer.MAX_VALUE;
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int digitSum = 0;
            
            // Fast primitive digit extraction
            while (num > 0) {
                digitSum += num % 10;
                num /= 10;
            }
            
            // Track the absolute smallest sum
            if (digitSum < minElement) {
                minElement = digitSum;
            }
        }
        
        return minElement;
    }
}

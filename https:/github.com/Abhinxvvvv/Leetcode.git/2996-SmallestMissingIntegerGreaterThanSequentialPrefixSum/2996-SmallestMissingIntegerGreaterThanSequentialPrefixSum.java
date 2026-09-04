// Last updated: 04/09/2026, 11:32:31
class Solution {
    public int missingInteger(int[] nums) {
        // Flat array to track presence (values are bounded by 50)
        boolean[] present = new boolean[51];
        
        int sum = nums[0];
        present[nums[0]] = true;
        boolean isSequential = true;
        
        // Single pass: Find sequential sum and map array presence instantly
        for (int i = 1; i < nums.length; i++) {
            present[nums[i]] = true;
            
            if (isSequential) {
                if (nums[i] == nums[i - 1] + 1) {
                    sum += nums[i];
                } else {
                    isSequential = false; // Sequence broken
                }
            }
        }
        
        // Find the smallest missing integer
        // If sum > 50, it mathematically cannot be in the array based on constraints
        while (sum <= 50 && present[sum]) {
            sum++;
        }
        
        return sum;
    }
}
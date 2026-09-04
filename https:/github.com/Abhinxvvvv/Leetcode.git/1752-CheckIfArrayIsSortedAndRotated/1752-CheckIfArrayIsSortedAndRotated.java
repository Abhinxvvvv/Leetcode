// Last updated: 04/09/2026, 11:33:48
class Solution {
    public boolean check(int[] nums) {
        int n = nums.length;
        int drops = 0;
        
        for (int i = 0; i < n; i++) {
            // Check if current element is greater than the next element (with wrap-around)
            if (nums[i] > nums[(i + 1) % n]) {
                drops++;
                
                // CRITICAL OPTIMIZATION: Early exit if more than 1 drop is found
                if (drops > 1) {
                    return false;
                }
            }
        }
        
        return true;
    }
}

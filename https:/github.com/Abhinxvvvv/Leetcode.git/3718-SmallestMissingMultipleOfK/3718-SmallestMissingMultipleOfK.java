// Last updated: 04/09/2026, 11:31:06
class Solution {
    public int missingMultiple(int[] nums, int k) {
        // Flat array tracking (index matches the number, up to the max constraint of 100)
        boolean[] present = new boolean[101];
        
        for (int i = 0; i < nums.length; i++) {
            present[nums[i]] = true;
        }
        
        int multiple = k;
        
        // Rapid lookup: exit if the multiple is over 100 (since max num is 100) 
        // or if the multiple isn't found in our tracking array.
        while (multiple <= 100 && present[multiple]) {
            multiple += k;
        }
        
        return multiple;
    }
}
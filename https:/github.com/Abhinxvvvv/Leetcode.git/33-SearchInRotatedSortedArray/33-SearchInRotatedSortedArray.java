// Last updated: 09/08/2026, 00:07:20
class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            // High-speed bitwise midpoint calculation
            int mid = (left + right) >>> 1;
            
            if (nums[mid] == target) {
                return mid;
            }
            
            // Condition 1: Left half is perfectly sorted
            if (nums[left] <= nums[mid]) {
                // Check if target lies within the boundaries of the sorted left half
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } 
            // Condition 2: Right half is perfectly sorted
            else {
                // Check if target lies within the boundaries of the sorted right half
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        
        return -1; // Target not found
    }
}

// Last updated: 09/08/2026, 00:07:08
class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            // OPTIMIZATION: Early Exit
            // If the current sub-array is already perfectly sorted, the minimum is on the far left.
            if (nums[left] < nums[right]) {
                return nums[left];
            }
            
            // Ultra-fast bitwise shift for calculating mid-point
            int mid = (left + right) >>> 1;
            
            // If mid is greater than the right boundary, the minimum is strictly to the right
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } 
            // Otherwise, mid is part of the right-side sorted portion. Minimum is at mid or left.
            else {
                right = mid;
            }
        }
        
        // When left == right, we have isolated the absolute minimum element
        return nums[left];
    }
}

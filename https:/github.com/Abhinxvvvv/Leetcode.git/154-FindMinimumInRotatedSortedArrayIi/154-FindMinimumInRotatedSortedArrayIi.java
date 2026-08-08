// Last updated: 09/08/2026, 00:07:05
class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            // OPTIMIZATION: Early Exit
            // If the current sub-array is strictly sorted, the minimum is on the far left.
            if (nums[left] < nums[right]) {
                return nums[left];
            }
            
            // Ultra-fast bitwise shift for calculating mid-point
            int mid = (left + right) >>> 1;
            
            // If mid is strictly greater than the right boundary, the minimum is to the right
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } 
            // If mid is strictly less, the minimum is at mid or to the left
            else if (nums[mid] < nums[right]) {
                right = mid;
            } 
            // DUPLICATE PRUNING: nums[mid] == nums[right]
            // We can safely discard the rightmost element because we have its duplicate at mid
            else {
                right--;
            }
        }
        
        // When left == right, we have isolated the absolute minimum element
        return nums[left];
    }
}

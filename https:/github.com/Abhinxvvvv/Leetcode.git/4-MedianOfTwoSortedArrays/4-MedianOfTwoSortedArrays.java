// Last updated: 09/08/2026, 00:07:31
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // MEMORY OPTIMIZATION 1: Manually swap references instead of using recursion
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        int m = nums1.length;
        int n = nums2.length;
        int low = 0;
        int high = m;
        
        // Pre-calculate to avoid doing this math repeatedly in the loop
        int halfLen = (m + n + 1) / 2;

        while (low <= high) {
            int partitionX = (low + high) / 2;
            int partitionY = halfLen - partitionX;

            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = (partitionX == m) ? Integer.MAX_VALUE : nums1[partitionX];

            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = (partitionY == n) ? Integer.MAX_VALUE : nums2[partitionY];

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                // MEMORY OPTIMIZATION 2: Inline ternary operators instead of Math.max() calls
                int maxLeft = maxLeftX > maxLeftY ? maxLeftX : maxLeftY;
                
                // Odd total length
                if ((m + n) % 2 == 1) {
                    return (double) maxLeft;
                }
                
                // Even total length
                int minRight = minRightX < minRightY ? minRightX : minRightY;
                return (maxLeft + minRight) / 2.0;
            } 
            else if (maxLeftX > minRightY) {
                high = partitionX - 1;
            } 
            else {
                low = partitionX + 1;
            }
        }
        
        return 0.0;
    }
}
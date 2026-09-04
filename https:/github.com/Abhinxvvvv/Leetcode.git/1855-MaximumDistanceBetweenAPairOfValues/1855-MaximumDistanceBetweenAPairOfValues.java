// Last updated: 04/09/2026, 11:33:42
class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;
        int maxDist = 0;
        
        while (i < nums1.length && j < nums2.length) {
            // If the condition is met, we have a valid pair (or j needs to catch up to i)
            if (nums1[i] <= nums2[j]) {
                // Only calculate distance if i <= j as required by the problem
                if (i <= j) {
                    maxDist = Math.max(maxDist, j - i);
                }
                // Try to find a larger distance by moving j further to the right
                j++;
            } else {
                // nums1[i] is too large, move i forward to find a smaller value
                i++;
            }
        }
        
        return maxDist;
    }
}

// Last updated: 09/08/2026, 00:06:07
class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;
        int len1 = nums1.length;
        int len2 = nums2.length;
        
        // Linear scan utilizing raw primitive pointers
        while (i < len1 && j < len2) {
            if (nums1[i] == nums2[j]) {
                return nums1[i]; // Instant early exit on the smallest match
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        
        return -1; // No common intersection found
    }
}

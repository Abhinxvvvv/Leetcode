// Last updated: 04/09/2026, 11:31:07
class Solution {
    public boolean uniformArray(int[] nums1) {
        // Since there is no restriction requiring nums2[i] > 0,
        // it is always mathematically possible to make all elements odd:
        // - Keep odd elements as nums1[i] (odd).
        // - Subtract an odd element from even elements: nums1[i] - odd = odd.
        // If there are no odd elements, the original array is already all even.
        return true;
    }
}
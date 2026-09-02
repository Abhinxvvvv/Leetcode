// Last updated: 02/09/2026, 12:09:26
1class Solution {
2    public boolean uniformArray(int[] nums1) {
3        // Since there is no restriction requiring nums2[i] > 0,
4        // it is always mathematically possible to make all elements odd:
5        // - Keep odd elements as nums1[i] (odd).
6        // - Subtract an odd element from even elements: nums1[i] - odd = odd.
7        // If there are no odd elements, the original array is already all even.
8        return true;
9    }
10}
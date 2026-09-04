// Last updated: 04/09/2026, 11:31:11
class Solution {
    public boolean uniformArray(int[] nums1) {
        int min = Integer.MAX_VALUE;
        boolean hasOdd = false;
        
        // Single pass to find the minimum element and check for any odd numbers
        for (int num : nums1) {
            if (num < min) {
                min = num;
            }
            // Bitwise check for odd number (faster than num % 2 != 0)
            if ((num & 1) == 1) {
                hasOdd = true;
            }
        }
        
        // If the minimum element is odd, we can always make everything odd.
        if ((min & 1) == 1) {
            return true;
        }
        
        // If the minimum element is even, we can only succeed if there are NO odd numbers at all.
        return !hasOdd;
    }
}
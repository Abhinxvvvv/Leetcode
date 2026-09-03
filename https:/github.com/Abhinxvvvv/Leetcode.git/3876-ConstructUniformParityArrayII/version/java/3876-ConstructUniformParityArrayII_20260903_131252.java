// Last updated: 03/09/2026, 13:12:52
1class Solution {
2    public boolean uniformArray(int[] nums1) {
3        int min = Integer.MAX_VALUE;
4        boolean hasOdd = false;
5        
6        // Single pass to find the minimum element and check for any odd numbers
7        for (int num : nums1) {
8            if (num < min) {
9                min = num;
10            }
11            // Bitwise check for odd number (faster than num % 2 != 0)
12            if ((num & 1) == 1) {
13                hasOdd = true;
14            }
15        }
16        
17        // If the minimum element is odd, we can always make everything odd.
18        if ((min & 1) == 1) {
19            return true;
20        }
21        
22        // If the minimum element is even, we can only succeed if there are NO odd numbers at all.
23        return !hasOdd;
24    }
25}
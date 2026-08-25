// Last updated: 25/08/2026, 12:58:04
1class Solution {
2    public int missingMultiple(int[] nums, int k) {
3        // Flat array tracking (index matches the number, up to the max constraint of 100)
4        boolean[] present = new boolean[101];
5        
6        for (int i = 0; i < nums.length; i++) {
7            present[nums[i]] = true;
8        }
9        
10        int multiple = k;
11        
12        // Rapid lookup: exit if the multiple is over 100 (since max num is 100) 
13        // or if the multiple isn't found in our tracking array.
14        while (multiple <= 100 && present[multiple]) {
15            multiple += k;
16        }
17        
18        return multiple;
19    }
20}
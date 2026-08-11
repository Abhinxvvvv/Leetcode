// Last updated: 11/08/2026, 13:49:44
1class Solution {
2    public int missingInteger(int[] nums) {
3        // Flat array to track presence (values are bounded by 50)
4        boolean[] present = new boolean[51];
5        
6        int sum = nums[0];
7        present[nums[0]] = true;
8        boolean isSequential = true;
9        
10        // Single pass: Find sequential sum and map array presence instantly
11        for (int i = 1; i < nums.length; i++) {
12            present[nums[i]] = true;
13            
14            if (isSequential) {
15                if (nums[i] == nums[i - 1] + 1) {
16                    sum += nums[i];
17                } else {
18                    isSequential = false; // Sequence broken
19                }
20            }
21        }
22        
23        // Find the smallest missing integer
24        // If sum > 50, it mathematically cannot be in the array based on constraints
25        while (sum <= 50 && present[sum]) {
26            sum++;
27        }
28        
29        return sum;
30    }
31}
// Last updated: 24/09/2026, 13:07:45
1class Solution {
2    public int smallestIndex(int[] nums) {
3        for (int i = 0; i < nums.length; i++) {
4            int n = nums[i];
5            int sum = (n % 10) + ((n / 10) % 10) + ((n / 100) % 10) + (n / 1000);
6            
7            if (sum == i) {
8                return i;
9            }
10        }
11        return -1;
12    }
13}
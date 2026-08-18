// Last updated: 18/08/2026, 21:06:36
1class Solution {
2    public int largestInteger(int[] nums, int k) {
3        int[] count = new int[51];
4        
5        for (int i = 0; i <= nums.length - k; i++) {
6            boolean[] seen = new boolean[51];
7            for (int j = i; j < i + k; j++) {
8                if (!seen[nums[j]]) {
9                    seen[nums[j]] = true;
10                    count[nums[j]]++;
11                }
12            }
13        }
14        
15        for (int i = 50; i >= 0; i--) {
16            if (count[i] == 1) {
17                return i;
18            }
19        }
20        
21        return -1;
22    }
23}
// Last updated: 13/10/2026, 20:46:08
1class Solution {
2    public long maxAlternatingSum(int[] nums) {
3        int[] counts = new int[40001];
4        for (int num : nums) {
5            counts[num < 0 ? -num : num]++;
6        }
7        
8        int plusCount = (nums.length + 1) / 2;
9        long ans = 0;
10        
11        for (int i = 40000; i >= 0; i--) {
12            if (counts[i] > 0) {
13                long sq = (long) i * i;
14                if (plusCount >= counts[i]) {
15                    ans += counts[i] * sq;
16                    plusCount -= counts[i];
17                } else {
18                    int minusCount = counts[i] - plusCount;
19                    ans += plusCount * sq;
20                    ans -= minusCount * sq;
21                    plusCount = 0;
22                }
23            }
24        }
25        
26        return ans;
27    }
28}
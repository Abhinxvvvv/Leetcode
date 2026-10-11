// Last updated: 11/10/2026, 11:34:21
1class Solution {
2    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
3        int n = nums1.length;
4        int[] bucket = new int[100001];
5        long k = (long) k1 + k2;
6        long diffSum = 0;
7        int maxDiff = 0;
8        
9        for (int i = 0; i < n; i++) {
10            int diff = Math.abs(nums1[i] - nums2[i]);
11            bucket[diff]++;
12            diffSum += diff;
13            if (diff > maxDiff) {
14                maxDiff = diff;
15            }
16        }
17        
18        if (diffSum <= k) {
19            return 0;
20        }
21        
22        for (int i = maxDiff; i > 0 && k > 0; i--) {
23            if (bucket[i] > 0) {
24                long minus = Math.min((long) bucket[i], k);
25                bucket[i] -= minus;
26                bucket[i - 1] += minus;
27                k -= minus;
28            }
29        }
30        
31        long ans = 0;
32        for (int i = maxDiff; i > 0; i--) {
33            if (bucket[i] > 0) {
34                ans += (long) bucket[i] * i * i;
35            }
36        }
37        
38        return ans;
39    }
40}
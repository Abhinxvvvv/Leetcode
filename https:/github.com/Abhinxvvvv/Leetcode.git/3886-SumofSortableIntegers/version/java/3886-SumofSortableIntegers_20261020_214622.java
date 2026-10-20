// Last updated: 20/10/2026, 21:46:22
1import java.util.Arrays;
2import java.util.HashMap;
3import java.util.Random;
4
5class Solution {
6    public int sortableIntegers(int[] nums) {
7        int n = nums.length;
8        int[] sortedNums = nums.clone();
9        Arrays.sort(sortedNums);
10        
11        HashMap<Integer, Long> hashMap = new HashMap<>();
12        Random rnd = new Random(1234567L);
13        for (int num : nums) {
14            if (!hashMap.containsKey(num)) {
15                hashMap.put(num, rnd.nextLong());
16            }
17        }
18        
19        long[] prefHash = new long[n + 1];
20        long[] prefSortedHash = new long[n + 1];
21        for (int i = 0; i < n; i++) {
22            prefHash[i + 1] = prefHash[i] + hashMap.get(nums[i]);
23            prefSortedHash[i + 1] = prefSortedHash[i] + hashMap.get(sortedNums[i]);
24        }
25        
26        int[] prefDrop = new int[n];
27        for (int i = 0; i < n - 1; i++) {
28            int d = nums[i] > nums[i + 1] ? 1 : 0;
29            prefDrop[i] = (i > 0 ? prefDrop[i - 1] : 0) + d;
30        }
31        if (n > 1) {
32            prefDrop[n - 1] = prefDrop[n - 2];
33        }
34        
35        int ans = 0;
36        for (int k = 1; k * k <= n; k++) {
37            if (n % k == 0) {
38                if (check(k, n, nums, prefHash, prefSortedHash, prefDrop)) {
39                    ans += k;
40                }
41                if (k * k != n) {
42                    int other = n / k;
43                    if (check(other, n, nums, prefHash, prefSortedHash, prefDrop)) {
44                        ans += other;
45                    }
46                }
47            }
48        }
49        return ans;
50    }
51    
52    private boolean check(int k, int n, int[] nums, long[] prefHash, long[] prefSortedHash, int[] prefDrop) {
53        for (int i = 0; i < n; i += k) {
54            int L = i;
55            int R = i + k - 1;
56            
57            long h1 = prefHash[R + 1] - prefHash[L];
58            long h2 = prefSortedHash[R + 1] - prefSortedHash[L];
59            if (h1 != h2) return false;
60            
61            if (k > 1) {
62                int drops = prefDrop[R - 1] - (L > 0 ? prefDrop[L - 1] : 0);
63                if (drops > 1) return false;
64                if (drops == 1 && nums[R] > nums[L]) return false;
65            }
66        }
67        return true;
68    }
69}
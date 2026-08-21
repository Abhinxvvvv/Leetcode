// Last updated: 21/08/2026, 21:15:34
1import java.util.ArrayList;
2import java.util.List;
3
4class Solution {
5    public long findKthSmallest(int[] coins, int k) {
6        // Correctly initialize minCoin to the first element
7        long minCoin = coins[0]; 
8        for (int c : coins) {
9            if (c < minCoin) minCoin = c;
10        }
11        
12        // The absolute maximum value the k-th amount could possibly be
13        long high = minCoin * k;
14        long low = 1;
15        
16        List<Long> posList = new ArrayList<>();
17        List<Long> negList = new ArrayList<>();
18        
19        // Pre-generate and prune LCM combinations recursively
20        generate(0, 1, 0, coins, high, posList, negList);
21        
22        // Convert to highly-optimized flat primitive arrays for the inner loop
23        long[] pos = new long[posList.size()];
24        for (int i = 0; i < posList.size(); i++) pos[i] = posList.get(i);
25        
26        long[] neg = new long[negList.size()];
27        for (int i = 0; i < negList.size(); i++) neg[i] = negList.get(i);
28        
29        long ans = high;
30        
31        // Binary search the answer space
32        while (low <= high) {
33            long mid = (low + high) >>> 1; // High-speed unsigned right shift
34            
35            // Inclusion-Exclusion Evaluation
36            long count = 0;
37            for (long p : pos) count += mid / p;
38            for (long n : neg) count -= mid / n;
39            
40            if (count >= k) {
41                ans = mid;
42                high = mid - 1;
43            } else {
44                low = mid + 1;
45            }
46        }
47        
48        return ans;
49    }
50    
51    private void generate(int idx, long currentLcm, int count, int[] coins, long maxVal, List<Long> pos, List<Long> neg) {
52        if (idx == coins.length) {
53            if (count > 0) {
54                if (count % 2 == 1) pos.add(currentLcm);
55                else neg.add(currentLcm);
56            }
57            return;
58        }
59        
60        // Option 1: Exclude current coin
61        generate(idx + 1, currentLcm, count, coins, maxVal, pos, neg);
62        
63        // Option 2: Include current coin
64        long nextLcm = lcm(currentLcm, coins[idx]);
65        
66        // CRITICAL PRUNING: Only traverse if the LCM doesn't exceed our maximum possible bounds
67        if (nextLcm <= maxVal) {
68            generate(idx + 1, nextLcm, count + 1, coins, maxVal, pos, neg);
69        }
70    }
71    
72    private long gcd(long a, long b) {
73        while (b != 0) {
74            long temp = b;
75            b = a % b;
76            a = temp;
77        }
78        return a;
79    }
80    
81    private long lcm(long a, long b) {
82        return (a / gcd(a, b)) * b;
83    }
84}
// Last updated: 12/10/2026, 21:49:55
1import java.util.List;
2
3class Solution {
4    // Overload 1: In case the platform uses List<Integer> (Standard LeetCode signature for 2897)
5    public int maxSum(List<Integer> nums, int k) {
6        int[] bitCounts = new int[30];
7        
8        for (int num : nums) {
9            // Directly jump between set bits using hardware TZCNT instruction
10            while (num != 0) {
11                int bit = Integer.numberOfTrailingZeros(num);
12                bitCounts[bit]++;
13                num &= (num - 1); // Clear the lowest set bit
14            }
15        }
16        return computeMaxSum(bitCounts, k);
17    }
18
19    // Overload 2: In case the platform uses primitive int[]
20    public int maxSum(int[] nums, int k) {
21        int[] bitCounts = new int[30];
22        
23        for (int num : nums) {
24            while (num != 0) {
25                int bit = Integer.numberOfTrailingZeros(num);
26                bitCounts[bit]++;
27                num &= (num - 1);
28            }
29        }
30        return computeMaxSum(bitCounts, k);
31    }
32
33    private int computeMaxSum(int[] bitCounts, int k) {
34        long MOD = 1_000_000_007L;
35        
36        // Cap all bit counts at k, since we only pick k elements
37        int[] masks = new int[30];
38        for (int b = 0; b < 30; b++) {
39            if (bitCounts[b] > k) {
40                bitCounts[b] = k;
41            }
42            masks[b] = 1 << b;
43        }
44        
45        // Insertion sort the 30 bits in descending order of their frequency.
46        // Sorting 30 elements takes < 100 CPU cycles.
47        for (int i = 1; i < 30; i++) {
48            int c = bitCounts[i];
49            int m = masks[i];
50            int j = i - 1;
51            while (j >= 0 && bitCounts[j] < c) {
52                bitCounts[j + 1] = bitCounts[j];
53                masks[j + 1] = masks[j];
54                j--;
55            }
56            bitCounts[j + 1] = c;
57            masks[j + 1] = m;
58        }
59        
60        long totalSum = 0;
61        long currentVal = 0;
62        
63        // Sweep through the sorted bit counts to batch-calculate identical numbers in O(30) steps!
64        for (int i = 0; i < 30; i++) {
65            if (bitCounts[i] == 0) break;
66            
67            currentVal |= masks[i];
68            int nextCount = (i + 1 < 30) ? bitCounts[i + 1] : 0;
69            int diff = bitCounts[i] - nextCount;
70            
71            // 'diff' is the exact count of numbers that have the exact value 'currentVal'
72            if (diff > 0) {
73                long sq = (currentVal * currentVal) % MOD;
74                totalSum = (totalSum + diff * sq) % MOD;
75            }
76        }
77        
78        return (int) totalSum;
79    }
80}
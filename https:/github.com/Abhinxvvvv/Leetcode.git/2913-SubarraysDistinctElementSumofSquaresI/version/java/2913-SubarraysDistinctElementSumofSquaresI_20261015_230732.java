// Last updated: 15/10/2026, 23:07:32
1import java.util.List;
2
3class Solution {
4    // Overload 1: Official LeetCode signature for this problem
5    public int sumCounts(List<Integer> nums) {
6        int n = nums.size();
7        int sum = 0;
8        
9        // nums[i] is guaranteed to be between 1 and 100.
10        // We use an array instead of a HashSet to track seen elements for bare-metal speed.
11        int[] seen = new int[101];
12        
13        // O(N^2) generation of all subarrays
14        for (int i = 0; i < n; i++) {
15            int distinct = 0;
16            
17            // Optimization: Instead of clearing the 'seen' array with Arrays.fill(0) 
18            // on every outer loop, we use (i + 1) as a unique marker.
19            int marker = i + 1; 
20            
21            for (int j = i; j < n; j++) {
22                int val = nums.get(j);
23                
24                // If the value hasn't been seen in the current subarray [i..j]
25                if (seen[val] != marker) {
26                    seen[val] = marker;
27                    distinct++;
28                }
29                
30                sum += distinct * distinct;
31            }
32        }
33        
34        return sum;
35    }
36
37    // Overload 2: Primitive array driver compatibility
38    public int sumCounts(int[] nums) {
39        int n = nums.length;
40        int sum = 0;
41        int[] seen = new int[101];
42        
43        for (int i = 0; i < n; i++) {
44            int distinct = 0;
45            int marker = i + 1; 
46            
47            for (int j = i; j < n; j++) {
48                int val = nums[j];
49                
50                if (seen[val] != marker) {
51                    seen[val] = marker;
52                    distinct++;
53                }
54                
55                sum += distinct * distinct;
56            }
57        }
58        
59        return sum;
60    }
61}
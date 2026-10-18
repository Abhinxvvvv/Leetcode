// Last updated: 18/10/2026, 19:14:16
1class Solution {
2    private static final int MOD = 1_000_000_007;
3    private long[][] comb;
4
5    public int numOfWays(int[] nums) {
6        int n = nums.length;
7        
8        // Precompute combinations (nCr) using Pascal's Triangle
9        // This avoids expensive modular inverses and factorials entirely.
10        comb = new long[n + 1][n + 1];
11        comb[0][0] = 1;
12        for (int i = 1; i <= n; i++) {
13            comb[i][0] = 1;
14            for (int j = 1; j <= i; j++) {
15                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
16            }
17        }
18        
19        // The problem asks for *other* ways, so we subtract the original array configuration
20        return (int) ((solve(nums) - 1 + MOD) % MOD);
21    }
22
23    private long solve(int[] nums) {
24        int m = nums.length;
25        // A tree of size 0, 1, or 2 can only be formed in exactly 1 way.
26        if (m <= 2) {
27            return 1;
28        }
29        
30        int root = nums[0];
31        
32        // Pass 1: Count elements to pre-allocate exact primitive arrays (bypassing ArrayList)
33        int leftSize = 0;
34        for (int i = 1; i < m; i++) {
35            if (nums[i] < root) {
36                leftSize++;
37            }
38        }
39        
40        int rightSize = m - 1 - leftSize;
41        int[] left = new int[leftSize];
42        int[] right = new int[rightSize];
43        
44        // Pass 2: Populate the left and right subtrees
45        int l = 0, r = 0;
46        for (int i = 1; i < m; i++) {
47            if (nums[i] < root) {
48                left[l++] = nums[i];
49            } else {
50                right[r++] = nums[i];
51            }
52        }
53        
54        // Recursively solve for subtrees
55        long leftWays = solve(left);
56        long rightWays = solve(right);
57        
58        // The math: We must interleave 'leftSize' elements and 'rightSize' elements.
59        // This is simply choosing 'leftSize' positions out of 'leftSize + rightSize' total positions.
60        long totalWays = comb[leftSize + rightSize][leftSize];
61        
62        // Multiply by the sub-permutations and apply modulo iteratively to prevent long overflow
63        totalWays = (totalWays * leftWays) % MOD;
64        totalWays = (totalWays * rightWays) % MOD;
65        
66        return totalWays;
67    }
68}
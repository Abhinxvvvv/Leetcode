// Last updated: 13/09/2026, 11:42:15
1class Solution {
2    public int largestOverlap(int[][] img1, int[][] img2) {
3        int n = img1.length;
4        
5        // At most N * N elements can be 1
6        int[] aOnes = new int[n * n];
7        int[] bOnes = new int[n * n];
8        int aCount = 0;
9        int bCount = 0;
10        
11        // Pass 1: Harvest and pack coordinates using bitwise operations
12        for (int r = 0; r < n; r++) {
13            for (int c = 0; c < n; c++) {
14                if (img1[r][c] == 1) {
15                    aOnes[aCount++] = (r << 6) | c;
16                }
17                if (img2[r][c] == 1) {
18                    bOnes[bCount++] = (r << 6) | c;
19                }
20            }
21        }
22        
23        int maxOverlap = 0;
24        
25        // N <= 30. Maximum offset is between -29 and 29.
26        // We shift by 30 to keep indices positive: [1, 59].
27        // Packed offset index max: (59 << 6) | 59 = 3835. 
28        // 4096 is an exact power-of-2 bound that easily fits our state space.
29        int[] count = new int[4096];
30        
31        // Pass 2: Calculate all vector translations between points
32        for (int i = 0; i < aCount; i++) {
33            int a = aOnes[i];
34            int ar = a >> 6;
35            int ac = a & 63; // 63 is 111111 in binary (mask for bottom 6 bits)
36            
37            for (int j = 0; j < bCount; j++) {
38                int b = bOnes[j];
39                int br = b >> 6;
40                int bc = b & 63;
41                
42                // Calculate directional offset, shifted by 30 to avoid negative arrays
43                int dr = ar - br + 30;
44                int dc = ac - bc + 30;
45                
46                // Pack the offset into a single 1D array index
47                int offsetIdx = (dr << 6) | dc;
48                
49                // Track the most frequent translation instantly
50                int currentOverlap = ++count[offsetIdx];
51                if (currentOverlap > maxOverlap) {
52                    maxOverlap = currentOverlap;
53                }
54            }
55        }
56        
57        return maxOverlap;
58    }
59}
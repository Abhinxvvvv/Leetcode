// Last updated: 12/09/2026, 12:09:19
1import java.util.Arrays;
2import java.util.List;
3
4class Solution {
5    public int[] maximumWeight(List<List<Integer>> intervalsList) {
6        int n = intervalsList.size();
7        int[][] intervals = new int[n][3];
8        for (int i = 0; i < n; i++) {
9            intervals[i][0] = intervalsList.get(i).get(0);
10            intervals[i][1] = intervalsList.get(i).get(1);
11            intervals[i][2] = intervalsList.get(i).get(2);
12        }
13        return maximumWeight(intervals);
14    }
15
16    public int[] maximumWeight(int[][] intervals) {
17        int n = intervals.length;
18        long[] packed = new long[n];
19        for (int i = 0; i < n; i++) {
20            packed[i] = ((long) intervals[i][0] << 32) | i;
21        }
22        
23        Arrays.sort(packed);
24        int[] order = new int[n];
25        for (int i = 0; i < n; i++) {
26            order[i] = (int) packed[i];
27        }
28
29        long[] dpWeight = new long[(n + 1) * 5];
30        long[] dpIndices = new long[(n + 1) * 5];
31
32        for (int i = n - 1; i >= 0; i--) {
33            int orig = order[i];
34            int r = intervals[orig][1];
35            long weight = intervals[orig][2];
36            int j = nextValid(intervals, order, r, i + 1);
37
38            for (int k = 1; k <= 4; k++) {
39                long w1 = dpWeight[(i + 1) * 5 + k];
40                long idx1 = dpIndices[(i + 1) * 5 + k];
41
42                long w2 = weight + dpWeight[j * 5 + k - 1];
43                long idx2 = addAndPack(dpIndices[j * 5 + k - 1], orig + 1);
44
45                if (w2 > w1 || (w2 == w1 && Long.compareUnsigned(idx2, idx1) < 0)) {
46                    dpWeight[i * 5 + k] = w2;
47                    dpIndices[i * 5 + k] = idx2;
48                } else {
49                    dpWeight[i * 5 + k] = w1;
50                    dpIndices[i * 5 + k] = idx1;
51                }
52            }
53        }
54
55        long bestIdx = dpIndices[4];
56        int[] res1Based = unpack(bestIdx);
57        int[] ans = new int[res1Based.length];
58        for (int i = 0; i < ans.length; i++) {
59            ans[i] = res1Based[i] - 1;
60        }
61        return ans;
62    }
63
64    private int nextValid(int[][] intervals, int[] order, int targetR, int low) {
65        int high = order.length - 1;
66        int ans = order.length;
67        while (low <= high) {
68            int mid = (low + high) >>> 1;
69            if (intervals[order[mid]][0] > targetR) {
70                ans = mid;
71                high = mid - 1;
72            } else {
73                low = mid + 1;
74            }
75        }
76        return ans;
77    }
78
79    private long addAndPack(long packed, int x) {
80        long out = 0;
81        int count = 0;
82        boolean inserted = false;
83        
84        for (int i = 0; i < 4; i++) {
85            int val = (int) ((packed >>> (48 - i * 16)) & 0xFFFF);
86            if (val == 0) break;
87            
88            if (!inserted && x < val) {
89                if (count < 4) {
90                    out = (out << 16) | x;
91                    count++;
92                }
93                inserted = true;
94            }
95            if (count < 4) {
96                out = (out << 16) | val;
97                count++;
98            }
99        }
100        
101        if (!inserted && count < 4) {
102            out = (out << 16) | x;
103            count++;
104        }
105        
106        out <<= (4 - count) * 16;
107        return out;
108    }
109
110    private int[] unpack(long packed) {
111        int count = 0;
112        for (int i = 0; i < 4; i++) {
113            if (((packed >>> (48 - i * 16)) & 0xFFFF) != 0) {
114                count++;
115            }
116        }
117        int[] res = new int[count];
118        for (int i = 0; i < count; i++) {
119            res[i] = (int) ((packed >>> (48 - i * 16)) & 0xFFFF);
120        }
121        return res;
122    }
123}
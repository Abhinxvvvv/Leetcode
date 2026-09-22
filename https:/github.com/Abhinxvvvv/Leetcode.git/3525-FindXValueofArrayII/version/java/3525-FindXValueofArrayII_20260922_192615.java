// Last updated: 22/09/2026, 19:26:15
1class Solution {
2    private int[] treeProd;
3    private int[] treeCount;
4    private int K;
5
6    public int[] resultArray(int[] nums, int k, int[][] queries) {
7        this.K = k;
8        int n = nums.length;
9        treeProd = new int[4 * n];
10        treeCount = new int[4 * n * k];
11
12        build(1, 0, n - 1, nums);
13
14        int q = queries.length;
15        int[] ans = new int[q];
16
17        for (int i = 0; i < q; i++) {
18            int idx = queries[i][0];
19            int val = queries[i][1];
20            int start = queries[i][2];
21            int x = queries[i][3];
22
23            update(1, 0, n - 1, idx, val % k);
24
25            int[] res = query(1, 0, n - 1, start, n - 1);
26            ans[i] = res[1 + x];
27        }
28
29        return ans;
30    }
31
32    private void build(int node, int l, int r, int[] nums) {
33        if (l == r) {
34            int val = nums[l] % K;
35            treeProd[node] = val;
36            treeCount[node * K + val] = 1;
37            return;
38        }
39        int mid = l + (r - l) / 2;
40        int left = node * 2;
41        int right = left + 1;
42        
43        build(left, l, mid, nums);
44        build(right, mid + 1, r, nums);
45        merge(node, left, right);
46    }
47
48    private void update(int node, int l, int r, int idx, int val) {
49        if (l == r) {
50            int baseNode = node * K;
51            for (int i = 0; i < K; i++) {
52                treeCount[baseNode + i] = 0;
53            }
54            treeProd[node] = val;
55            treeCount[baseNode + val] = 1;
56            return;
57        }
58        int mid = l + (r - l) / 2;
59        int left = node * 2;
60        int right = left + 1;
61        
62        if (idx <= mid) {
63            update(left, l, mid, idx, val);
64        } else {
65            update(right, mid + 1, r, idx, val);
66        }
67        merge(node, left, right);
68    }
69
70    private void merge(int node, int left, int right) {
71        treeProd[node] = (treeProd[left] * treeProd[right]) % K;
72        int baseNode = node * K;
73        int baseLeft = left * K;
74        int baseRight = right * K;
75        int leftP = treeProd[left];
76
77        for (int i = 0; i < K; i++) {
78            treeCount[baseNode + i] = treeCount[baseLeft + i];
79        }
80        
81        for (int i = 0; i < K; i++) {
82            int c = treeCount[baseRight + i];
83            if (c > 0) {
84                treeCount[baseNode + (leftP * i) % K] += c;
85            }
86        }
87    }
88
89    private int[] query(int node, int l, int r, int ql, int qr) {
90        if (ql <= l && r <= qr) {
91            int[] res = new int[K + 1];
92            res[0] = treeProd[node];
93            int baseNode = node * K;
94            for (int i = 0; i < K; i++) {
95                res[i + 1] = treeCount[baseNode + i];
96            }
97            return res;
98        }
99        
100        int mid = l + (r - l) / 2;
101        int left = node * 2;
102        int right = left + 1;
103
104        if (qr <= mid) {
105            return query(left, l, mid, ql, qr);
106        } else if (ql > mid) {
107            return query(right, mid + 1, r, ql, qr);
108        } else {
109            int[] resLeft = query(left, l, mid, ql, qr);
110            int[] resRight = query(right, mid + 1, r, ql, qr);
111            int[] res = new int[K + 1];
112
113            res[0] = (resLeft[0] * resRight[0]) % K;
114            int leftP = resLeft[0];
115
116            for (int i = 0; i < K; i++) {
117                res[i + 1] = resLeft[i + 1];
118            }
119            
120            for (int i = 0; i < K; i++) {
121                int c = resRight[i + 1];
122                if (c > 0) {
123                    res[(leftP * i) % K + 1] += c;
124                }
125            }
126            return res;
127        }
128    }
129}
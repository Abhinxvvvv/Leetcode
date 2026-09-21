// Last updated: 21/09/2026, 14:12:25
1class Solution {
2    public long[] resultArray(int[] nums, int k) {
3        long[] ans = new long[k];
4        long n = nums.length;
5        
6        if (k == 1) {
7            ans[0] = n * (n + 1) / 2;
8            return ans;
9        }
10        
11        if (k == 2) {
12            long dp0 = 0, dp1 = 0;
13            for (int x : nums) {
14                if (x % 2 == 0) {
15                    dp0 = 1 + dp0 + dp1;
16                    dp1 = 0;
17                } else {
18                    dp1 = 1 + dp1;
19                }
20                ans[0] += dp0;
21                ans[1] += dp1;
22            }
23            return ans;
24        }
25        
26        if (k == 3) {
27            long dp0 = 0, dp1 = 0, dp2 = 0;
28            for (int x : nums) {
29                int val = x % 3;
30                long n0 = 0, n1 = 0, n2 = 0;
31                if (val == 0) {
32                    n0 = 1 + dp0 + dp1 + dp2;
33                } else if (val == 1) {
34                    n0 = dp0; 
35                    n1 = 1 + dp1; 
36                    n2 = dp2;
37                } else {
38                    n0 = dp0; 
39                    n1 = dp2; 
40                    n2 = 1 + dp1;
41                }
42                dp0 = n0; 
43                dp1 = n1; 
44                dp2 = n2;
45                ans[0] += dp0; 
46                ans[1] += dp1; 
47                ans[2] += dp2;
48            }
49            return ans;
50        }
51        
52        if (k == 4) {
53            long dp0 = 0, dp1 = 0, dp2 = 0, dp3 = 0;
54            for (int x : nums) {
55                int val = x % 4;
56                long n0 = 0, n1 = 0, n2 = 0, n3 = 0;
57                if (val == 0) {
58                    n0 = 1 + dp0 + dp1 + dp2 + dp3;
59                } else if (val == 1) {
60                    n0 = dp0; 
61                    n1 = 1 + dp1; 
62                    n2 = dp2; 
63                    n3 = dp3;
64                } else if (val == 2) {
65                    n0 = dp0 + dp2; 
66                    n1 = 0; 
67                    n2 = 1 + dp1 + dp3; 
68                    n3 = 0;
69                } else {
70                    n0 = dp0; 
71                    n1 = dp3; 
72                    n2 = dp2; 
73                    n3 = 1 + dp1;
74                }
75                dp0 = n0; 
76                dp1 = n1; 
77                dp2 = n2; 
78                dp3 = n3;
79                ans[0] += dp0; 
80                ans[1] += dp1; 
81                ans[2] += dp2; 
82                ans[3] += dp3;
83            }
84            return ans;
85        }
86        
87        if (k == 5) {
88            long dp0 = 0, dp1 = 0, dp2 = 0, dp3 = 0, dp4 = 0;
89            for (int x : nums) {
90                int val = x % 5;
91                long n0 = 0, n1 = 0, n2 = 0, n3 = 0, n4 = 0;
92                if (val == 0) {
93                    n0 = 1 + dp0 + dp1 + dp2 + dp3 + dp4;
94                } else if (val == 1) {
95                    n0 = dp0; 
96                    n1 = 1 + dp1; 
97                    n2 = dp2; 
98                    n3 = dp3; 
99                    n4 = dp4;
100                } else if (val == 2) {
101                    n0 = dp0; 
102                    n1 = dp3; 
103                    n2 = 1 + dp1; 
104                    n3 = dp4; 
105                    n4 = dp2;
106                } else if (val == 3) {
107                    n0 = dp0; 
108                    n1 = dp2; 
109                    n2 = dp4; 
110                    n3 = 1 + dp1; 
111                    n4 = dp3;
112                } else {
113                    n0 = dp0; 
114                    n1 = dp4; 
115                    n2 = dp3; 
116                    n3 = dp2; 
117                    n4 = 1 + dp1;
118                }
119                dp0 = n0; 
120                dp1 = n1; 
121                dp2 = n2; 
122                dp3 = n3; 
123                dp4 = n4;
124                ans[0] += dp0; 
125                ans[1] += dp1; 
126                ans[2] += dp2; 
127                ans[3] += dp3; 
128                ans[4] += dp4;
129            }
130            return ans;
131        }
132        
133        return ans;
134    }
135}
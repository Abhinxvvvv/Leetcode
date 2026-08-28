// Last updated: 28/08/2026, 16:48:36
1class Solution {
2    public String lexPalindromicPermutation(String s, String target) {
3        int n = s.length();
4        int half = n / 2;
5        int[] cnt = new int[26];
6        
7        // 1. Map occurrences and validate if a palindrome is possible
8        for (int i = 0; i < n; i++) {
9            cnt[s.charAt(i) - 'a']++;
10        }
11        
12        int oddChar = -1;
13        for (int i = 0; i < 26; i++) {
14            if (cnt[i] % 2 == 1) {
15                if (oddChar != -1) return "";
16                oddChar = i;
17            }
18        }
19        
20        int[] leftCnt = new int[26];
21        for (int i = 0; i < 26; i++) {
22            leftCnt[i] = cnt[i] / 2;
23        }
24        
25        // 2. Find the maximum prefix length we can perfectly match with target's left half
26        int maxMatch = 0;
27        int[] curFreq = new int[26];
28        for (int i = 0; i < half; i++) {
29            int c = target.charAt(i) - 'a';
30            if (leftCnt[c] > curFreq[c]) {
31                curFreq[c]++;
32                maxMatch++;
33            } else {
34                break;
35            }
36        }
37        
38        // 3. Perfect Match Case 
39        if (maxMatch == half) {
40            char[] res = new char[n];
41            for (int i = 0; i < half; i++) {
42                res[i] = target.charAt(i);
43                res[n - 1 - i] = target.charAt(i);
44            }
45            if (oddChar != -1) {
46                res[half] = (char) (oddChar + 'a');
47            }
48            
49            String p = new String(res);
50            if (p.compareTo(target) > 0) return p;
51        }
52        
53        // 4. Divergence Case: Start evaluating from the deepest possible branch point
54        int start = Math.min(half - 1, maxMatch);
55        int[] avail = leftCnt.clone();
56        
57        // Remove characters already locked in up to 'start - 1'
58        for (int j = 0; j < start; j++) {
59            avail[target.charAt(j) - 'a']--;
60        }
61        
62        for (int i = start; i >= 0; i--) {
63            int req = target.charAt(i) - 'a';
64            
65            // Look for the smallest available character strictly greater than target[i]
66            for (int c = req + 1; c < 26; c++) {
67                if (avail[c] > 0) {
68                    avail[c]--;
69                    
70                    // Optimal divergence found! Build the string instantly.
71                    char[] res2 = new char[n];
72                    
73                    // Add identical prefix
74                    for (int j = 0; j < i; j++) {
75                        res2[j] = target.charAt(j);
76                    }
77                    
78                    // Add divergent character
79                    res2[i] = (char) (c + 'a');
80                    int idx = i + 1;
81                    
82                    // Flush remaining characters in strict alphabetical order
83                    for (int k = 0; k < 26; k++) {
84                        while (avail[k] > 0) {
85                            res2[idx++] = (char) (k + 'a');
86                            avail[k]--;
87                        }
88                    }
89                    
90                    // Lock middle character for odd lengths
91                    if (oddChar != -1) {
92                        res2[half] = (char) (oddChar + 'a');
93                    }
94                    
95                    // Mirror perfectly to the right half
96                    for (int j = 0; j < half; j++) {
97                        res2[n - 1 - j] = res2[j];
98                    }
99                    
100                    return new String(res2);
101                }
102            }
103            
104            // If we couldn't branch at 'i', retreat to 'i - 1' and put target[i - 1] back into the pool
105            if (i > 0) {
106                avail[target.charAt(i - 1) - 'a']++;
107            }
108        }
109        
110        return "";
111    }
112}
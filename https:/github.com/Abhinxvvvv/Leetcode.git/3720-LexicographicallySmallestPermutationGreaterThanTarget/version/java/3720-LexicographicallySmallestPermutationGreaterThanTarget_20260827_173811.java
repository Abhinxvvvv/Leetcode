// Last updated: 27/08/2026, 17:38:11
1class Solution {
2    public String lexGreaterPermutation(String s, String target) {
3        int n = s.length();
4        int[] sFreq = new int[26];
5        
6        // 1. Map occurrences of all characters in s
7        for (int i = 0; i < n; i++) {
8            sFreq[s.charAt(i) - 'a']++;
9        }
10
11        // 2. Find the maximum prefix length 'L' we can perfectly match with target
12        int L = 0;
13        int[] curFreq = new int[26];
14        for (int i = 0; i < n; i++) {
15            int c = target.charAt(i) - 'a';
16            if (sFreq[c] > curFreq[c]) {
17                curFreq[c]++;
18                L++;
19            } else {
20                break;
21            }
22        }
23
24        // 3. Start evaluating from the deepest possible divergence point
25        int start = Math.min(L, n - 1);
26        int[] avail = sFreq.clone();
27        
28        // Remove characters used in the matched prefix up to 'start - 1'
29        for (int j = 0; j < start; j++) {
30            avail[target.charAt(j) - 'a']--;
31        }
32
33        for (int i = start; i >= 0; i--) {
34            int req = target.charAt(i) - 'a';
35            
36            // Try to find the smallest available character strictly greater than target[i]
37            for (int c = req + 1; c < 26; c++) {
38                if (avail[c] > 0) {
39                    avail[c]--;
40                    
41                    // We found our optimal divergence! Build the string instantly.
42                    char[] res = new char[n];
43                    
44                    // Add identical prefix
45                    for (int j = 0; j < i; j++) {
46                        res[j] = target.charAt(j);
47                    }
48                    
49                    // Add divergent character
50                    res[i] = (char) (c + 'a');
51                    int idx = i + 1;
52                    
53                    // Add remaining characters in strict alphabetical order
54                    for (int k = 0; k < 26; k++) {
55                        while (avail[k] > 0) {
56                            res[idx++] = (char) (k + 'a');
57                            avail[k]--;
58                        }
59                    }
60                    return new String(res);
61                }
62            }
63            
64            // If we couldn't branch at 'i', we retreat to 'i - 1' and put the character back into the pool
65            if (i > 0) {
66                avail[target.charAt(i - 1) - 'a']++;
67            }
68        }
69
70        return "";
71    }
72}
// Last updated: 18/09/2026, 15:37:58
1import java.util.ArrayList;
2import java.util.List;
3
4class Solution {
5    public List<String> maxNumOfSubstrings(String s) {
6        char[] chars = s.toCharArray();
7        int n = chars.length;
8        
9        // Track the first and last occurrence of each of the 26 lowercase letters
10        int[] first = new int[26];
11        int[] last = new int[26];
12        
13        for (int i = 0; i < 26; i++) {
14            first[i] = -1;
15        }
16        
17        for (int i = 0; i < n; i++) {
18            int c = chars[i] - 'a';
19            if (first[c] == -1) {
20                first[c] = i;
21            }
22            last[c] = i;
23        }
24        
25        // Since there are only 26 characters, there can be at most 26 valid substrings.
26        int[] validStart = new int[26];
27        int[] validEnd = new int[26];
28        int validCount = 0;
29        
30        // Find all valid candidate intervals
31        for (int i = 0; i < 26; i++) {
32            if (first[i] == -1) continue;
33            
34            int start = first[i];
35            int end = last[i];
36            boolean isValid = true;
37            
38            // Scan through the current interval. 
39            // If we find a character that forces us to expand right, we update 'end'.
40            // If we find a character that forces us to expand left, it's invalid.
41            for (int j = start; j <= end; j++) {
42                int c = chars[j] - 'a';
43                if (first[c] < start) {
44                    isValid = false;
45                    break;
46                }
47                if (last[c] > end) {
48                    end = last[c];
49                }
50            }
51            
52            if (isValid) {
53                validStart[validCount] = start;
54                validEnd[validCount] = end;
55                validCount++;
56            }
57        }
58        
59        // Bubble sort the valid intervals by end index (ascending).
60        // Since validCount <= 26, an O(K^2) sort is virtually instantaneous (sub-microsecond).
61        for (int i = 0; i < validCount; i++) {
62            for (int j = i + 1; j < validCount; j++) {
63                if (validEnd[i] > validEnd[j] || 
64                   (validEnd[i] == validEnd[j] && validStart[i] < validStart[j])) {
65                    
66                    int tempStart = validStart[i];
67                    validStart[i] = validStart[j];
68                    validStart[j] = tempStart;
69                    
70                    int tempEnd = validEnd[i];
71                    validEnd[i] = validEnd[j];
72                    validEnd[j] = tempEnd;
73                }
74            }
75        }
76        
77        List<String> result = new ArrayList<>();
78        int lastEnd = -1;
79        
80        // Greedily pick the earliest ending, non-overlapping intervals
81        for (int i = 0; i < validCount; i++) {
82            if (validStart[i] > lastEnd) {
83                result.add(s.substring(validStart[i], validEnd[i] + 1));
84                lastEnd = validEnd[i];
85            }
86        }
87        
88        return result;
89    }
90}
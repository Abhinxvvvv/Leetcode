// Last updated: 04/10/2026, 22:50:36
1class Solution {
2    public boolean checkValidString(String s) {
3        int cmin = 0;
4        int cmax = 0;
5        
6        char[] arr = s.toCharArray();
7        for (char c : arr) {
8            if (c == '(') {
9                cmax++;
10                cmin++;
11            } else if (c == ')') {
12                cmax--;
13                if (cmin > 0) {
14                    cmin--;
15                }
16            } else {
17                cmax++;
18                if (cmin > 0) {
19                    cmin--;
20                }
21            }
22            
23            if (cmax < 0) {
24                return false;
25            }
26        }
27        
28        return cmin == 0;
29    }
30}
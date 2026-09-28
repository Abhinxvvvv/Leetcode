// Last updated: 28/09/2026, 20:56:10
1class Solution {
2    public int maxDepth(String s) {
3        int max = 0;
4        int cur = 0;
5        char[] arr = s.toCharArray();
6        
7        for (int i = 0; i < arr.length; i++) {
8            if (arr[i] == '(') {
9                cur++;
10                if (cur > max) {
11                    max = cur;
12                }
13            } else if (arr[i] == ')') {
14                cur--;
15            }
16        }
17        
18        return max;
19    }
20}
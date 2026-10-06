// Last updated: 06/10/2026, 22:38:33
1class Solution {
2    public int minAddToMakeValid(String s) {
3        int left = 0;
4        int right = 0;
5        char[] arr = s.toCharArray();
6        
7        for (char c : arr) {
8            if (c == '(') {
9                right++;
10            } else if (right > 0) {
11                right--;
12            } else {
13                left++;
14            }
15        }
16        
17        return left + right;
18    }
19}
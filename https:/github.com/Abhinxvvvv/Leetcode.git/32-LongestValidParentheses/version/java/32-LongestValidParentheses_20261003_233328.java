// Last updated: 03/10/2026, 23:33:28
1class Solution {
2    public int longestValidParentheses(String s) {
3        char[] arr = s.toCharArray();
4        int left = 0, right = 0, maxLength = 0;
5        
6        for (int i = 0; i < arr.length; i++) {
7            if (arr[i] == '(') {
8                left++;
9            } else {
10                right++;
11            }
12            
13            if (left == right) {
14                if (left * 2 > maxLength) {
15                    maxLength = left * 2;
16                }
17            } else if (right > left) {
18                left = 0;
19                right = 0;
20            }
21        }
22        
23        left = 0;
24        right = 0;
25        for (int i = arr.length - 1; i >= 0; i--) {
26            if (arr[i] == '(') {
27                left++;
28            } else {
29                right++;
30            }
31            
32            if (left == right) {
33                if (left * 2 > maxLength) {
34                    maxLength = left * 2;
35                }
36            } else if (left > right) {
37                left = 0;
38                right = 0;
39            }
40        }
41        
42        return maxLength;
43    }
44}
// Last updated: 27/09/2026, 22:33:27
1class Solution {
2    public String reverseParentheses(String s) {
3        char[] chars = s.toCharArray();
4        int n = chars.length;
5        
6        // pair array stores the index of the matching bracket
7        int[] pair = new int[n];
8        
9        // Primitive stack to track opening brackets
10        int[] stack = new int[n];
11        int top = -1;
12        
13        // Pass 1: Match parentheses (The Wormholes)
14        for (int i = 0; i < n; i++) {
15            if (chars[i] == '(') {
16                stack[++top] = i;
17            } else if (chars[i] == ')') {
18                int match = stack[top--];
19                pair[i] = match;
20                pair[match] = i;
21            }
22        }
23        
24        char[] res = new char[n];
25        int resIdx = 0;
26        
27        // Pass 2: Traverse using teleportation
28        int i = 0;
29        int dir = 1; // 1 for forward, -1 for backward
30        
31        while (i < n) {
32            if (chars[i] == '(' || chars[i] == ')') {
33                // Teleport to the matching bracket and reverse direction
34                i = pair[i];
35                dir = -dir;
36            } else {
37                // Regular character, append it to the result
38                res[resIdx++] = chars[i];
39            }
40            i += dir;
41        }
42        
43        return new String(res, 0, resIdx);
44    }
45}
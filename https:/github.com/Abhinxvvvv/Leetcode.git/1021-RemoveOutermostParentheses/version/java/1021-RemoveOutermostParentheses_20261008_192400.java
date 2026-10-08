// Last updated: 08/10/2026, 19:24:00
1class Solution {
2    public String removeOuterParentheses(String s) {
3        // Convert to primitive array to avoid .charAt() overhead 
4        // and allow in-place modification
5        char[] chars = s.toCharArray();
6        int idx = 0;   // The write pointer
7        int depth = 0; // Tracks the current nesting level
8        
9        for (int i = 0; i < chars.length; i++) {
10            if (chars[i] == '(') {
11                // If depth > 0 before incrementing, it's an inner parenthesis.
12                // Post-increment (depth++) evaluates the condition first, then adds 1.
13                if (depth++ > 0) {
14                    chars[idx++] = '(';
15                }
16            } else {
17                // If depth > 0 after decrementing, it's an inner parenthesis.
18                // Pre-decrement (--depth) subtracts 1 first, then evaluates the condition.
19                if (--depth > 0) {
20                    chars[idx++] = ')';
21                }
22            }
23        }
24        
25        // Construct the final string using only the valid written portion of the array
26        return new String(chars, 0, idx);
27    }
28}
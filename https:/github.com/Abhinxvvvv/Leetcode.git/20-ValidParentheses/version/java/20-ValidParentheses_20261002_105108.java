// Last updated: 02/10/2026, 10:51:08
1class Solution {
2    public boolean isValid(String s) {
3        int n = s.length();
4        
5        // Quick optimization: An odd-length string can never be validly balanced.
6        // Using bitwise AND for a faster parity check.
7        if ((n & 1) == 1) {
8            return false;
9        }
10        
11        // Primitive array acts as a highly optimized, allocation-free stack
12        char[] stack = new char[n];
13        int top = -1;
14        
15        // Convert to char array to bypass the overhead of s.charAt(i) method calls
16        for (char c : s.toCharArray()) {
17            if (c == '(') {
18                stack[++top] = ')';
19            } else if (c == '{') {
20                stack[++top] = '}';
21            } else if (c == '[') {
22                stack[++top] = ']';
23            } else {
24                // If it's a closing bracket, the stack shouldn't be empty, 
25                // and the top of the stack MUST match the current character.
26                if (top == -1 || stack[top--] != c) {
27                    return false;
28                }
29            }
30        }
31        
32        // If the stack is fully empty at the end, all brackets were matched perfectly.
33        return top == -1;
34    }
35}
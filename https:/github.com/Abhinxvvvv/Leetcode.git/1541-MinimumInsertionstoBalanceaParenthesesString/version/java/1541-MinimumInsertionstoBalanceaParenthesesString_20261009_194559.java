// Last updated: 09/10/2026, 19:45:59
1class Solution {
2    public int minInsertions(String s) {
3        int insertions = 0;
4        int reqRight = 0;
5        
6        // Convert to primitive array for blazing fast, direct memory access
7        char[] chars = s.toCharArray();
8        
9        for (int i = 0; i < chars.length; i++) {
10            if (chars[i] == '(') {
11                // If reqRight is odd, it means we have an orphaned ')' from a previous pair.
12                // We must balance it immediately by inserting one ')' before processing this new '('.
13                if ((reqRight & 1) == 1) {
14                    insertions++; // Insert the missing ')'
15                    reqRight--;   // We fulfilled that specific need
16                }
17                // Every new '(' strictly requires two ')'
18                reqRight += 2;
19            } else {
20                // We found a ')'
21                reqRight--;
22                
23                // If reqRight drops below 0, it means we have a ')' with no matching '('.
24                if (reqRight < 0) {
25                    insertions++; // Insert a missing '('
26                    // The inserted '(' requires two ')'. We just consumed one, so we still need 1 more.
27                    reqRight = 1; 
28                }
29            }
30        }
31        
32        // Any remaining required right brackets must be inserted at the very end
33        return insertions + reqRight;
34    }
35}
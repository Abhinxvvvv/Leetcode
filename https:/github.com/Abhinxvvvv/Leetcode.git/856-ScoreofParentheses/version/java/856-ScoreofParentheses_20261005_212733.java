// Last updated: 05/10/2026, 21:27:33
1class Solution {
2    public int scoreOfParentheses(String s) {
3        int score = 0;
4        int depth = 0;
5        
6        // Convert to primitive array for bare-metal memory access
7        char[] chars = s.toCharArray();
8        
9        for (int i = 0; i < chars.length; i++) {
10            if (chars[i] == '(') {
11                // Going one layer deeper
12                depth++;
13            } else {
14                // Coming out of a layer
15                depth--;
16                
17                // If we immediately close an open bracket, it's a core "()" pair.
18                // We add its shifted weight to the total score.
19                if (chars[i - 1] == '(') {
20                    score += (1 << depth);
21                }
22            }
23        }
24        
25        return score;
26    }
27}
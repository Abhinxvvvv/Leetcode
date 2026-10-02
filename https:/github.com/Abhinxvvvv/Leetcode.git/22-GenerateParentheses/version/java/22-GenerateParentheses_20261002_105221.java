// Last updated: 02/10/2026, 10:52:21
1import java.util.ArrayList;
2import java.util.List;
3
4class Solution {
5    public List<String> generateParenthesis(int n) {
6        List<String> result = new ArrayList<>();
7        // A single primitive array to track the current path
8        char[] path = new char[n * 2];
9        
10        // Start the bare-metal recursion
11        build(result, path, 0, 0, 0, n);
12        
13        return result;
14    }
15
16    private void build(List<String> result, char[] path, int pos, int open, int close, int n) {
17        // Base Case: We have filled the array with a valid combination
18        if (pos == path.length) {
19            // Allocate exactly ONE string per valid answer
20            result.add(new String(path));
21            return;
22        }
23
24        // Branch 1: Add an open parenthesis if we haven't used all 'n' of them
25        if (open < n) {
26            path[pos] = '(';
27            build(result, path, pos + 1, open + 1, close, n);
28        }
29
30        // Branch 2: Add a closed parenthesis if it can validly match an open one
31        if (close < open) {
32            path[pos] = ')';
33            build(result, path, pos + 1, open, close + 1, n);
34        }
35    }
36}
// Last updated: 07/10/2026, 21:21:16
1import java.util.ArrayList;
2import java.util.List;
3
4class Solution {
5    public List<String> removeInvalidParentheses(String s) {
6        List<String> ans = new ArrayList<>();
7        dfs(s, 0, 0, new char[]{'(', ')'}, ans);
8        return ans;
9    }
10
11    private void dfs(String s, int last_i, int last_j, char[] par, List<String> ans) {
12        int stack = 0;
13        for (int i = last_i; i < s.length(); i++) {
14            if (s.charAt(i) == par[0]) stack++;
15            if (s.charAt(i) == par[1]) stack--;
16            if (stack >= 0) continue;
17            
18            for (int j = last_j; j <= i; j++) {
19                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1])) {
20                    dfs(s.substring(0, j) + s.substring(j + 1), i, j, par, ans);
21                }
22            }
23            return;
24        }
25        
26        String reversed = new StringBuilder(s).reverse().toString();
27        if (par[0] == '(') {
28            dfs(reversed, 0, 0, new char[]{')', '('}, ans);
29        } else {
30            ans.add(reversed);
31        }
32    }
33}
// Last updated: 29/09/2026, 22:22:19
1class Solution {
2    public boolean hasValidPath(char[][] grid) {
3        int m = grid.length;
4        int n = grid[0].length;
5        
6        if ((m + n - 1) % 2 != 0) {
7            return false;
8        }
9        if (grid[0][0] == ')') {
10            return false;
11        }
12        if (grid[m - 1][n - 1] == '(') {
13            return false;
14        }
15        
16        int maxBal = (m + n) / 2;
17        boolean[][][] visited = new boolean[m][n][maxBal + 1];
18        
19        return dfs(grid, 0, 0, 0, visited, m, n);
20    }
21    
22    private boolean dfs(char[][] grid, int r, int c, int bal, boolean[][][] visited, int m, int n) {
23        bal += (grid[r][c] == '(') ? 1 : -1;
24        
25        if (bal < 0) {
26            return false;
27        }
28        
29        int remainingSteps = (m - 1 - r) + (n - 1 - c);
30        if (bal > remainingSteps) {
31            return false;
32        }
33        
34        if (r == m - 1 && c == n - 1) {
35            return bal == 0;
36        }
37        
38        if (visited[r][c][bal]) {
39            return false;
40        }
41        visited[r][c][bal] = true;
42        
43        if (r + 1 < m && dfs(grid, r + 1, c, bal, visited, m, n)) {
44            return true;
45        }
46        if (c + 1 < n && dfs(grid, r, c + 1, bal, visited, m, n)) {
47            return true;
48        }
49        
50        return false;
51    }
52}
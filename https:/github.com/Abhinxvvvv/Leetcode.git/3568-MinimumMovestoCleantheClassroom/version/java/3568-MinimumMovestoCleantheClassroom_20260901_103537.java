// Last updated: 01/09/2026, 10:35:37
1import java.util.LinkedList;
2import java.util.Queue;
3import java.util.Arrays;
4
5class Solution {
6    public int minMoves(String[] classroom, int energy) {
7        int m = classroom.length;
8        int n = classroom[0].length();
9        
10        int[][] litterPositions = new int[m][n];
11        int sr = -1, sc = -1, numLitters = 0;
12        
13        // Locate Starting Position and map Litter Coordinates
14        for (int r = 0; r < m; r++) {
15            for (int c = 0; c < n; c++) {
16                litterPositions[r][c] = -1;
17                char ch = classroom[r].charAt(c);
18                if (ch == 'S') {
19                    sr = r;
20                    sc = c;
21                } else if (ch == 'L') {
22                    litterPositions[r][c] = numLitters++;
23                }
24            }
25        }
26        
27        // If there's no litter in the classroom, zero moves are needed
28        if (numLitters == 0) return 0;
29        
30        int targetMask = (1 << numLitters) - 1;
31        
32        // maxEnergy[row][col][collected_litter_mask]
33        int[][][] maxEnergy = new int[m][n][1 << numLitters];
34        for (int r = 0; r < m; r++) {
35            for (int c = 0; c < n; c++) {
36                Arrays.fill(maxEnergy[r][c], -1);
37            }
38        }
39        
40        // Queue format: {row, col, mask, current_energy}
41        Queue<int[]> queue = new LinkedList<>();
42        queue.offer(new int[]{sr, sc, 0, energy});
43        maxEnergy[sr][sc][0] = energy;
44        
45        int[] dr = {-1, 1, 0, 0};
46        int[] dc = {0, 0, -1, 1};
47        int moves = 0;
48        
49        while (!queue.isEmpty()) {
50            int size = queue.size();
51            
52            for (int i = 0; i < size; i++) {
53                int[] curr = queue.poll();
54                int r = curr[0], c = curr[1], mask = curr[2], curEn = curr[3];
55                
56                // Check if all litter has been collected
57                if (mask == targetMask) {
58                    return moves;
59                }
60                
61                // If energy is zero, we cannot move further from this cell unless it's a Reset
62                if (curEn == 0) continue;
63                
64                // Explore 4 directional adjacent cells
65                for (int d = 0; d < 4; d++) {
66                    int nr = r + dr[d];
67                    int nc = c + dc[d];
68                    
69                    if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
70                        char ch = classroom[nr].charAt(nc);
71                        if (ch == 'X') continue;
72                        
73                        int nxtEn = curEn - 1;
74                        int nxtMask = mask;
75                        
76                        // Apply cell-specific effects
77                        if (ch == 'R') {
78                            nxtEn = energy;
79                        } else if (ch == 'L') {
80                            nxtMask |= (1 << litterPositions[nr][nc]);
81                        }
82                        
83                        // Only proceed if we reach this state with strictly more energy than before
84                        if (nxtEn > maxEnergy[nr][nc][nxtMask]) {
85                            maxEnergy[nr][nc][nxtMask] = nxtEn;
86                            queue.offer(new int[]{nr, nc, nxtMask, nxtEn});
87                        }
88                    }
89                }
90            }
91            moves++;
92        }
93        
94        return -1;
95    }
96}
// Last updated: 09/08/2026, 00:05:19
import java.util.*;

class Solution {
    public int minMoves(String[] matrix) {
        int m = matrix.length;
        int n = matrix[0].length();
        
        if (m == 1 && n == 1) return 0;
        
        // Group portal coordinates by letter
        List<int[]>[] portals = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            portals[i] = new ArrayList<>();
        }
        
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = matrix[r].charAt(c);
                if (ch >= 'A' && ch <= 'Z') {
                    portals[ch - 'A'].add(new int[]{r, c});
                }
            }
        }
        
        // Use a distance array instead of a boolean visited array
        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dist[0][0] = 0;
        
        Deque<int[]> deque = new ArrayDeque<>();
        deque.offerFirst(new int[]{0, 0, 0}); // {row, col, distance}
        
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        while (!deque.isEmpty()) {
            int[] curr = deque.pollFirst();
            int r = curr[0];
            int c = curr[1];
            int d = curr[2];
            
            // If we already found a shorter path to this cell, discard this outdated path
            if (d > dist[r][c]) {
                continue;
            }
            
            if (r == m - 1 && c == n - 1) {
                return d;
            }
            
            char ch = matrix[r].charAt(c);
            
            // 0-cost moves (Teleports)
            if (ch >= 'A' && ch <= 'Z') {
                int idx = ch - 'A';
                if (portals[idx].size() > 0) {
                    for (int[] p : portals[idx]) {
                        int pr = p[0], pc = p[1];
                        // Only add to queue if this teleport provides a strictly shorter path
                        if (d < dist[pr][pc]) {
                            dist[pr][pc] = d;
                            deque.offerFirst(new int[]{pr, pc, d}); 
                        }
                    }
                    // Clear the list to mark this portal letter as used
                    portals[idx].clear(); 
                }
            }
            
            // 1-cost moves (Walking)
            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && matrix[nr].charAt(nc) != '#') {
                    // Only add to queue if walking provides a strictly shorter path
                    if (d + 1 < dist[nr][nc]) {
                        dist[nr][nc] = d + 1;
                        deque.offerLast(new int[]{nr, nc, d + 1});
                    }
                }
            }
        }
        
        return -1;
    }
}
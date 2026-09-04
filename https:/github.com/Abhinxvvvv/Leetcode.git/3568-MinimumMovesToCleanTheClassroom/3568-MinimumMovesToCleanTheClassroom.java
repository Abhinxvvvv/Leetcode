// Last updated: 04/09/2026, 11:31:42
import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int[][] litterPositions = new int[m][n];
        int sr = -1, sc = -1, numLitters = 0;
        
        // Locate Starting Position and map Litter Coordinates
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                litterPositions[r][c] = -1;
                char ch = classroom[r].charAt(c);
                if (ch == 'S') {
                    sr = r;
                    sc = c;
                } else if (ch == 'L') {
                    litterPositions[r][c] = numLitters++;
                }
            }
        }
        
        // If there's no litter in the classroom, zero moves are needed
        if (numLitters == 0) return 0;
        
        int targetMask = (1 << numLitters) - 1;
        
        // maxEnergy[row][col][collected_litter_mask]
        int[][][] maxEnergy = new int[m][n][1 << numLitters];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                Arrays.fill(maxEnergy[r][c], -1);
            }
        }
        
        // Queue format: {row, col, mask, current_energy}
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc, 0, energy});
        maxEnergy[sr][sc][0] = energy;
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        int moves = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int r = curr[0], c = curr[1], mask = curr[2], curEn = curr[3];
                
                // Check if all litter has been collected
                if (mask == targetMask) {
                    return moves;
                }
                
                // If energy is zero, we cannot move further from this cell unless it's a Reset
                if (curEn == 0) continue;
                
                // Explore 4 directional adjacent cells
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                        char ch = classroom[nr].charAt(nc);
                        if (ch == 'X') continue;
                        
                        int nxtEn = curEn - 1;
                        int nxtMask = mask;
                        
                        // Apply cell-specific effects
                        if (ch == 'R') {
                            nxtEn = energy;
                        } else if (ch == 'L') {
                            nxtMask |= (1 << litterPositions[nr][nc]);
                        }
                        
                        // Only proceed if we reach this state with strictly more energy than before
                        if (nxtEn > maxEnergy[nr][nc][nxtMask]) {
                            maxEnergy[nr][nc][nxtMask] = nxtEn;
                            queue.offer(new int[]{nr, nc, nxtMask, nxtEn});
                        }
                    }
                }
            }
            moves++;
        }
        
        return -1;
    }
}
// Last updated: 09/08/2026, 00:06:46
class Solution {
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Base case: 1x1 grid is always connected to itself
        if (m == 1 && n == 1) return true;

        // Try to flow out of the valid openings of the starting pipe at (0, 0)
        // Directions: 0 = UP, 1 = RIGHT, 2 = DOWN, 3 = LEFT
        int p = grid[0][0];
        if (p == 1) return simulate(grid, m, n, 0, 0, 1);
        if (p == 2) return simulate(grid, m, n, 0, 0, 2);
        if (p == 3) return simulate(grid, m, n, 0, 0, 2);
        // Pipe 4 has two valid outward openings from start
        if (p == 4) return simulate(grid, m, n, 0, 0, 1) || simulate(grid, m, n, 0, 0, 2); 
        if (p == 6) return simulate(grid, m, n, 0, 0, 1);
        
        // Pipe 5 only opens UP and LEFT, which instantly goes out of bounds from (0,0)
        return false; 
    }

    private boolean simulate(int[][] grid, int m, int n, int r, int c, int dir) {
        int maxSteps = m * n;
        
        for (int steps = 0; steps < maxSteps; steps++) {
            // We reached the destination safely
            if (r == m - 1 && c == n - 1) return true;
            
            // Move our position based on current direction
            if (dir == 1) c++;
            else if (dir == 2) r++;
            else if (dir == 3) c--;
            else if (dir == 0) r--;

            // Out of bounds check
            if (r < 0 || r >= m || c < 0 || c >= n) return false;

            int p = grid[r][c];
            
            // Re-orient our direction based on the pipe we just entered
            if (dir == 1) { // Moving Right, we need a Left opening
                if (p == 1) dir = 1;
                else if (p == 3) dir = 2;
                else if (p == 5) dir = 0;
                else return false;
            } else if (dir == 2) { // Moving Down, we need an Up opening
                if (p == 2) dir = 2;
                else if (p == 5) dir = 3;
                else if (p == 6) dir = 1;
                else return false;
            } else if (dir == 3) { // Moving Left, we need a Right opening
                if (p == 1) dir = 3;
                else if (p == 4) dir = 2;
                else if (p == 6) dir = 0;
                else return false;
            } else if (dir == 0) { // Moving Up, we need a Down opening
                if (p == 2) dir = 0;
                else if (p == 3) dir = 3;
                else if (p == 4) dir = 1;
                else return false;
            }
        }
        return false; // Trapped in a cycle
    }
}

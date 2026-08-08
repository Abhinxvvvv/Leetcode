// Last updated: 09/08/2026, 00:06:19
class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int numLayers = Math.min(m, n) / 2;
        
        // OPTIMIZATION 1: Single reusable memory buffer.
        // Size it to the absolute maximum possible perimeter to avoid re-allocations
        int[] temp = new int[2 * m + 2 * n]; 
        
        for (int i = 0; i < numLayers; i++) {
            int top = i, bottom = m - 1 - i;
            int left = i, right = n - 1 - i;
            
            int idx = 0;
            
            // 1. Flatten the ring into our 1D array (Clockwise Order)
            // Top row
            for (int c = left; c <= right; c++) temp[idx++] = grid[top][c];
            // Right col
            for (int r = top + 1; r <= bottom; r++) temp[idx++] = grid[r][right];
            // Bottom row
            for (int c = right - 1; c >= left; c--) temp[idx++] = grid[bottom][c];
            // Left col
            for (int r = bottom - 1; r > top; r--) temp[idx++] = grid[r][left];
            
            int L = idx; // The total number of elements in this specific layer
            
            // OPTIMIZATION 2: Modulo arithmetic prevents TLE
            int shift = k % L;
            if (shift == 0) continue; 
            
            // 2. Write the shifted array directly back into the grid in-place
            int ptr = shift;
            
            // Top row
            for (int c = left; c <= right; c++) {
                grid[top][c] = temp[ptr++];
                if (ptr == L) ptr = 0; // Wraparound beats modulo overhead
            }
            // Right col
            for (int r = top + 1; r <= bottom; r++) {
                grid[r][right] = temp[ptr++];
                if (ptr == L) ptr = 0;
            }
            // Bottom row
            for (int c = right - 1; c >= left; c--) {
                grid[bottom][c] = temp[ptr++];
                if (ptr == L) ptr = 0;
            }
            // Left col
            for (int r = bottom - 1; r > top; r--) {
                grid[r][left] = temp[ptr++];
                if (ptr == L) ptr = 0;
            }
        }
        
        return grid;
    }
}

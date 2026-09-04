// Last updated: 04/09/2026, 11:34:03
class Solution {
    private int[] parent;

    // Path compression for ultra-fast near O(1) lookups
    private int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent[i]);
    }

    public boolean containsCycle(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // 1D array to represent the 2D grid's sets
        parent = new int[m * n];
        for (int i = 0; i < m * n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int currentIdx = i * n + j;

                // 1. Check Right neighbor
                if (j + 1 < n && grid[i][j] == grid[i][j + 1]) {
                    int root1 = find(currentIdx);
                    int root2 = find(currentIdx + 1);
                    
                    // If they are already connected, a cycle is found
                    if (root1 == root2) return true;
                    
                    // Union
                    parent[root2] = root1;
                }

                // 2. Check Down neighbor
                if (i + 1 < m && grid[i][j] == grid[i + 1][j]) {
                    int root1 = find(currentIdx);
                    int root2 = find((i + 1) * n + j);
                    
                    // If they are already connected, a cycle is found
                    if (root1 == root2) return true;
                    
                    // Union
                    parent[root2] = root1;
                }
            }
        }

        return false;
    }
}

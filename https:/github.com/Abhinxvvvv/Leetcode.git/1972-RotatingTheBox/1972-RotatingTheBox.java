// Last updated: 09/08/2026, 00:06:28
class Solution {
    public char[][] rotateTheBox(char[][] boxGrid) {
        int m = boxGrid.length;
        int n = boxGrid[0].length;
        
        // 1. Apply gravity horizontally (stones fall to the right)
        for (int i = 0; i < m; i++) {
            int emptySpot = n - 1;
            for (int j = n - 1; j >= 0; j--) {
                if (boxGrid[i][j] == '*') {
                    // Obstacle resets the lowest available drop point
                    emptySpot = j - 1;
                } else if (boxGrid[i][j] == '#') {
                    // Move the stone to the empty spot. 
                    // (If j == emptySpot, it briefly becomes '.', then instantly '#' again)
                    boxGrid[i][j] = '.';
                    boxGrid[i][emptySpot] = '#';
                    emptySpot--;
                }
            }
        }
        
        // 2. Map to a rotated N x M matrix
        char[][] res = new char[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[j][m - 1 - i] = boxGrid[i][j];
            }
        }
        
        return res;
    }
}

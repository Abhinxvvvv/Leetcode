// Last updated: 09/08/2026, 00:07:17
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // 1. Transpose the matrix (swap [i][j] with [j][i])
        // We start j at i + 1 to only swap the top-right triangle with the bottom-left
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // 2. Reverse each row using a two-pointer approach
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n - 1;
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }
}

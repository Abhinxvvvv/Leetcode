// Last updated: 09/08/2026, 00:07:24
class Solution {
    public String convert(String s, int numRows) {
        // Base case: If 1 row or string is shorter than rows, zigzag does nothing
        if (numRows == 1 || numRows >= s.length()) {
            return s;
        }

        int n = s.length();
        // OPTIMIZATION 1: Raw char array bypasses StringBuilder's resizing overhead
        char[] result = new char[n];
        int idx = 0;
        
        // The mathematical length of one full zigzag pattern
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                // Add the character falling on the vertical line
                result[idx++] = s.charAt(j + i);
                
                // Add the character falling on the diagonal line 
                // (Exclude top and bottom rows which have no diagonals)
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n) {
                    result[idx++] = s.charAt(j + cycleLen - i);
                }
            }
        }
        
        return new String(result);
    }
}
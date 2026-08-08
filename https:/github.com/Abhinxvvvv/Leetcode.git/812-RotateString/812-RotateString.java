// Last updated: 09/08/2026, 00:07:13
class Solution {
    public boolean rotateString(String s, String goal) {
        // If lengths don't match, it's mathematically impossible
        if (s.length() != goal.length()) {
            return false;
        }
        
        int n = s.length();
        
        // Try every possible starting index 'i' to simulate rotations
        for (int i = 0; i < n; i++) {
            boolean isMatch = true;
            
            // Check if this rotation matches the goal
            for (int j = 0; j < n; j++) {
                // The modulo operator % seamlessly wraps the index around to the start
                if (s.charAt((i + j) % n) != goal.charAt(j)) {
                    isMatch = false;
                    break; // Early exit on the first mismatched character
                }
            }
            
            if (isMatch) {
                return true;
            }
        }
        
        return false;
    }
}
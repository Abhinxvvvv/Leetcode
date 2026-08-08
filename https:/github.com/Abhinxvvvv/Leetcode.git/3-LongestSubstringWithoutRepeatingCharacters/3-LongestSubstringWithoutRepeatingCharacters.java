// Last updated: 09/08/2026, 00:07:33
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        
        // Use an array instead of a HashMap to completely minimize memory overhead.
        // It stores the NEXT index of the character to jump our left pointer efficiently.
        int[] charIndex = new int[128]; 

        for (int right = 0, left = 0; right < n; right++) {
            char c = s.charAt(right);
            
            // If the character was seen, jump the left pointer to avoid the duplicate
            // We use Math.max to ensure the left pointer never moves backward.
            left = Math.max(charIndex[c], left); 
            
            // Calculate the current window size and update max
            maxLength = Math.max(maxLength, right - left + 1);
            
            // Store the NEXT index for this character
            charIndex[c] = right + 1; 
        }
        
        return maxLength;
    }
}